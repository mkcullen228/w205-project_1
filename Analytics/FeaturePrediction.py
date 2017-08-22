import pandas as pd # dataframe package
import numpy as np # vector math
import re # regular expressions for working with item description text data
from math import exp # for scoring percent
from sklearn.feature_extraction.text import CountVectorizer # make bag of words
from sklearn.feature_extraction.text import TfidfTransformer # highlight important words from bag of words
from sklearn.svm import LinearSVC # classification model (optimal model for this)
from elasticsearch import Elasticsearch # used to read from elastic search
from webbrowser import open_new_tab # package to open all of the links in a browser
# from stemming.porter2 import stem # for getting stems of words (optional)

# ****************************************************************************************************************************
# Machine Learning Feature Prediction:
# This script sorts the entire product catalog and ranks all items by what has the most potential to add or remove features
# How this is accomplished is with a Linear SVC machine learning model used to classify text.
# (http://scikit-learn.org/stable/modules/generated/sklearn.svm.LinearSVC.html) 
# For each item the name, brand, feature text, and short description are combined in order to predict features.
# The text is turned into a bag of words (https://en.wikipedia.org/wiki/Bag-of-words_model) with a tf-idf adjustment
# The catalog is divided up by category and a model is made for every possible feature in that category. Everywhere the model
# differs from the database is recorded and used to create a score assigned to each item. At the end the catalog is sorted
# and the top (worst) 20 items are printed out along with the features suggested to add and remove and the confidence score.
# 
# For speed issues, use python LinearSVC which implements liblinear. Don't use libsvm
# ****************************************************************************************************************************

import time
now = time.time()

client = Elasticsearch(hosts='10.157.2.21', port='9232') # set up client at the appropriate ip address and port

# find the size of the catalog in order to query all.
res = client.search(index="ebagsus-catalog", body = {
        "query": {"match_all": {}},
        "_source": []
    })
size = res['hits']['total']

# query all items from elastic search, taking only necessary attributes
res = client.search(index="ebagsus-catalog", body = {
        "from": 0, "size": size,
        "query": {"match_all": {}},
        "_source": ["id","features.slug","name","description","classification.classificationName","links","brand.name"]
    })

# for each json-like item return one row containing all of the necessary data for an item. Features are listed out as 
# text separated by spaces. All of the classification is done by the combination of the title of the item, short description,
# features, and brand.
def returnRow(item):
    features = ''
    for feature in item['_source']['features']:
        features += feature['slug'] + " "
    return [item['_source']['id'], 
            item['_source']['name'] + ' ' + ' '.join(item['_source']['description']['features']) +
                ' ' + item['_source']['brand']['name'] +
                (' ' + item['_source']['description']['shortDescription'] if 
                'shortDescription' in item['_source']['description'] else ''), 
            features,
            item['_source']['classification']['classificationName'],
            "http://www.ebags.com" + item['_source']['links']['detailPage']]

# create a table with each row being as item
items = pd.DataFrame([returnRow(item) for item in res['hits']['hits']],
                     columns=["id","text","features","group","link"]).set_index("id")

# in order to complete the machine learning model, each feature must be its own boolean column

# get set of every type of feature
s = set()
for l in items['features'].str.split():
    for item in l:
        s.add(item)

# create a column for each feature
for feature in s:
    items[feature] = [feature in f.split() for f in items.features]

del items['features'] # remove the temporary list of features for each item

items['text'] = [re.compile("[^\w ']").sub(' ', t).lower() for t in items['text']] # keep just letters and lower case

# removed because this didn't help model much, and when in doubt keep it simple. It would have replaced each word with
# the root of the word which sometimes helps for text classification
# items['text'] = [' '.join([stem(word) for word in single_text.split()]) for single_text in items['text']]

# Quick and dirty fix to the problem of a lot of luggage being either spinner or two-wheeled and not rolling (around 100 bags)
items['rolling'] = items['rolling'] | items['spinner'] | items['two-wheeled']

items['score'] = 0 # column to represent the overall score of feature assignment for that item. Higher = inaccurate features
items['changes'] = '' # text column of features that model would suggest to add
items['wrong'] = '' # text column of features that model would suggest to remove

# loop through all of the groups, each group will be fit with a different model to reduce innacurate feature assigning
for group in items.group.unique():
    # create boolean mask of all of the important columns for machine learning
    colMask = (items[items['group'] == group].sum() != 0).values
    colMask[-3] = True

    # all of the items and columns necessary for one category
    itemGroup = items.ix[items.group == group, colMask]

    # prepare bag of words with bigrams and remove unnecessary words
    vectorizer = CountVectorizer(stop_words='english', ngram_range=(1,2))

    # create training X matrix of bag of words with tfidf adjustment
    X = TfidfTransformer().fit_transform(vectorizer.fit_transform(itemGroup['text']))

    # I have tried many other types of models and the LinearSVC has done by far the best as a combination of speed and accuracy,
    # not only has it been the most accurate, it is one of the fastest models as well
    svc = LinearSVC()

    # In order to reduce overfitting, the model is trained with only half of the data, a random subsample picked by index here:
    train = np.random.choice(len(itemGroup), int(len(itemGroup) / 2))

    # Loop through all of the different features which are the columns indexed third to second from last
    for col in itemGroup.ix[:, 3:-3]:
        y = itemGroup[col] # target is the feature column of this iteration

        # The model can only be fit if there is at least one bag in the training set that contains the feature and one that doesn't
        # if not, that feature will be skipped
        try:
            svc.fit(X[train], y[train]) # train the model with the proper subset of data

            # The decision function is the distance the item is away from the hyperplane, use this to calculate confidence
            scores = svc.decision_function(X)

            # Rule 1: if the model predicts a feature and original database listing of the item doesn't contain that feature
            # then the score is increased by a logistic scaling of the distance the item is from the hyperplane of the model.
            # When the model differs from the database, the feature it differs on is recorded in the change column along with 
            # the rough percent confidence of the model
            items.ix[items.group == group,'score'] += [1/(exp(-1*score) + 1) - .5 if ((score > 0) & ~feat) else 0
                                                       for score, feat in zip(scores, y)]
            items.ix[items.group == group,'changes'] += [col + ": " + str(round(1/(exp(-1*score)+1),2)) + " " if
                                                        ((score > 0) & ~feat) else '' for score, feat in zip(scores, y)]

            # Rule 2: if the model predicts an item to not have the feature and the database says the item has the feature then
            # the score is increased by .001 * percent liklihood (reasoning being it is extremely common for the model to mess 
            # up and say the item doesn't have a feature when it does so impact is drastically decreased but still observed).
            # This is recorded as well in the wrong column along with the rough confidence of the model
            items.ix[items.group == group,'score'] += [((1/(exp(score) + 1)) - .5) * 2 * .001 if ((score < 0) & feat) else 0
                                                       for score, feat in zip(scores, y)]
            items.ix[items.group == group,'wrong'] += [col + ": " + str(round(1/(exp(score)+1),2)) + " " if 
                                                       ((score < 0) & feat) else '' for score, feat in zip(scores, y)]
        except ValueError:
            pass

# Sort bags that the model found might be missing or wrong features decreasing according to score
scoredItems = items[items.score != 0].sort_values('score', ascending=False)

# Everything below is to write the information to a file and open links in a web browser
f = open("output.txt","w")

f.write("Machine Learning Feature Prediction" + 
        "\n************************************************************************************************************************************************************************" + 
        "\nThis is a list of items in the eBags catalog that a machine learning model thinks should have modified features. These are the items with the most room for improvement." + 
        "\nEach row is formatted as follows:" +
        "\n\n<#>. <category>: <feature score>" + 
        "\n<item url>" +
        "\nadd    | [<feature that should be added>: <percent confidence of model> [...]]" + 
        "\nremove | [<feature that should be removed>: <percent confidence of model> [...]]" +
        "\n************************************************************************************************************************************************************************")

# Print out top 10 bags according to score. Theoretically these bags have the greatest chance of being the worst labeled items.
for i in range(10):
    f.write( "\n\n" + str(i+1) + ". " + scoredItems.ix[i,'group'] + ": " + str(scoredItems.ix[i,'score']) + '\n' + 
            scoredItems.ix[i,'link'] + '\n' + 
            "add    | " + scoredItems.ix[i,'changes'] + '\n' + 
            "remove | " + scoredItems.ix[i,'wrong'])

    open_new_tab(scoredItems.ix[i,'link']) # open the link in a web browser

print("Total running time:", round(time.time() - now, 2), "seconds")
