library("tidyverse")
library("corrplot")

plot_all = TRUE;
save_plots = TRUE;
imagepath = "/Users/mcullen/MyFolder/Documents/UCB_MIDS/W205/w205-project_1/images"

# Make folder for images if director does not alreay exist
if (dir.exists(imagepath) == FALSE) {
  dir.create(imagepath)
  library('webshot')}

# Load Data -----------------------
# df = read.csv("/Users/mauracullen/Documents/UCB_MIDS/W205_StoringRetrievingData/w205-project_1/model_summary.csv")
df = read.csv("/Users/mcullen/MyFolder/Documents/UCB_MIDS/W205/w205-project_1/model_summary.csv")
df = as_tibble(df)
df = subset(df, df$atc_rate<=1 & df$status=='Active')
df = select(df, -model_url)

# Create numberic labels for category columns --------------------- 
# dfa <- mutate(df,master_category = ifelse(master_category  == "\\N","Uncategorized", master_category))
# df$master_category[which(df$master_category == "\\N")]
df$master_category_code = rep(NaN, length(df$master_category))
class_labels = levels(df$master_category)
idx = 1;
for(level in class_labels){
  df$master_category_code[df$master_category == level] = idx
  idx = idx + 1
}
rm(idx)
df$master_category_code = as.numeric(df$master_category_code)
df$product_rating_code = as.numeric(df$product_rating)
df$brand_code = as.numeric(df$brand)
df$sub_category = as.numeric(df$sub_category)
df$product_rating <- as.numeric(df$product_rating)
df$image_count <- as.numeric(df$image_count)

# Dataframe summary stats ---------------------
summary(df)

df1 = select(df, -status, -description, 
             -sub_category, -brand, -master_category)
corr_M = cor(df1, use = "na.or.complete")
corrplot(corr_M, method = "circle", type = "lower", diag = FALSE)

# View all Correlation Plots with atc_rate -----------------------
df1 = select(df, -status, -description, -brand)
df1$master_category_code = as.factor(df1$master_category_code)
skip_cols = c("model_id", "atc_rate", "master_category") 
for(col in colnames(df1)){
  cat("\n", col)
  if(col %in% skip_cols){
    next
  }
  
  p = ggplot(df1, aes(x = atc_rate, y = df1[[col]])) + 
    geom_point(mapping = aes(color = master_category_code), alpha = 1/3) + 
    ggtitle(paste0("atc_rate vs ", col, " Corr: ", round(cor(df1$atc_rate, as.numeric(df1[[col]])),3))) + 
    labs(x = "add to cart rate", y = paste0(col)) 
  print(p)
  if(save_plots == TRUE){
    ggsave(filename = paste0(imagepath,'/atcRate_', col, '_scatter.png'), plot = p)
  }
}
rm(p)

# Histogram plots --------------------------
p1 <- ggplot(df, aes(x=atc_rate)) + 
  geom_histogram(binwidth=0.05) + ggtitle("Add to Cart Rate")

p2 <-ggplot(df1, aes(x=image_count)) + 
  geom_histogram(binwidth = 1) + ggtitle("Image Count")

p3 <-ggplot(df1, aes(x=feature_count)) + 
  geom_histogram(binwidth=1) + ggtitle("Feature Count")

p4 <-ggplot(df1, aes(x=facet_count)) + 
  geom_histogram(binwidth=1) + ggtitle("Facet Count")

p5 <-ggplot(df1, aes(x=product_rating)) + 
  geom_histogram(bins=20) + ggtitle("Product Rating")

if(save_plots == TRUE){
  ggsave(filename = paste0(imagepath,'/atc_rate_hist.png'), plot = p1)
  ggsave(filename = paste0(imagepath,'/image_count_hist.png'), plot = p2)
  ggsave(filename = paste0(imagepath,'/feature_count_hist.png'), plot = p3)
  ggsave(filename = paste0(imagepath,'/facet_count_hist.png'), plot = p4)
  ggsave(filename = paste0(imagepath,'/product_rating_hist.png'), plot = p5)
}
rm(p1, p2, p3, p4)

# Average pictures and add to cart by brand

for(code in unique(df$master_category_code)){
  dfi = filter(df,master_category_code == code)
  cat_name = unique(dfi$master_category)
  dfi = select(dfi, -status, -description, -master_category_code,
               -sub_category, -master_category, -brand)
  
  dfi$product_rating <- as.numeric(dfi$product_rating)
  dfi$image_count <- as.numeric(dfi$image_count)
  dfi$attribute_count <- as.numeric(dfi$attribute_count)
  corr_M = cor(dfi, use = "complete.obs")
  corrplot(round(corr_M,2), method = "circle", type = "lower", diag = FALSE, 
           title = paste0('Correlation for Master Category ', cat_name))
}


# Advanced Analytics ---------------------------
# Split by type or brand 
# Combined rate for description and pictures 

cor(df$model_views, df$model_atc)

# Split data -------------------------

# Create 70:30 Train/Test Split by master category for linear modeling 
rm(test_df, train_df)
for(code in unique(df1$master_category_code)){
  dfi = filter(df1,master_category_code == code)
  dp = nrow(dfi)
  train_idx = sample(as.numeric(seq(dp)),dp*0.70)
  traini <- dfi[train_idx,]
  testi <- dfi[-train_idx, ]
  
  if(exists("train_df") == FALSE){
    train_df = traini
    test_df = testi
  }else{
    train_df = rbind(train_df, traini)
    test_df = rbind(test_df, testi)
  }
}


# Train Linear Model --------------------
prev_error = 0
train_df = select(train_df, -master_category)
form = as.formula(paste("atc_rate", paste(colnames(train_df)[which(colnames(train_df) != "atc_rate")], 
                                          sep = "", collapse = " + "), sep = " ~ "))
t_df = train_df
t_df$master_category_code = as.numeric(t_df$master_category_code)
models = list()
plot_all = TRUE
for(i in seq(length(colnames(t_df)))){
  curr_m <- lm(form, t_df)
  models[[i]] <-c(models, curr_m$coefficients)
  
  if(plot_all == TRUE){
    test_df$master_category_code = as.numeric(test_df$master_category_code)
    y_preds <- predict(curr_m, test_df)
    y_preds[y_preds > 1] = 1
    y_preds[y_preds < 0] = 0
    test_df$y_preds = y_preds
    test_df$master_category_code = as.factor(test_df$master_category_code)
    title_str = paste0("Actual vs. Predicted atc_rate. \nCorrelation ", 
                       round(cor(test_df$y_preds, test_df$atc_rate),3), 
                       ' Num Coeff: ', length(curr_m$coefficients))
    p <- ggplot(data = test_df, mapping = aes(x = atc_rate, y = y_preds)) + 
      geom_point(mapping =  aes(color = master_category), alpha = 1/3) + 
      geom_smooth(method = "lm") + labs(x = "atc_rate", y = "predicted atc_rate") + 
      ggtitle(title_str)
    print(p)
    ggsave(filename= paste0(imagepath, '/lm_scatter_', length(curr_m$coefficients), '.png'), plot = p)
  }
  
  # train again with one less var
  prev_model = curr_m;
  prev_error = summary(curr_m)$adj.r.squared
  
  coeff = as.matrix(summary(curr_m)$coefficients)
  coeff = coeff[which(rownames(coeff) != "(Intercept)"),]
  drop_col = rownames(coeff)[[which.max(coeff[2:length(coeff[,1]),4])]]
  
  t_df = t_df[,which(colnames(t_df) != drop_col)]
  form = as.formula(paste("atc_rate", paste(colnames(t_df)[which(colnames(t_df) != "atc_rate")], 
                                            sep = "", collapse = " + "), sep = " ~ "))
  
  cat('\nModel Adusted ',summary(curr_m)$adj.r.squared)
  cat('\nRemoving ', drop_col)
  
  if(length(colnames(t_df)) < 2){
    best_model = prev_model;
    prev_error = summary(prev_model)$adj.r.squared
    break
  }
  
}


# Refit Best Model -----------------------------
t_df = train_df
t_df$master_category_code = as.numeric(t_df$master_category_code)

form = as.formula("atc_rate ~ brand_code + product_rating_code + master_category_code + 
                  model_atc + model_views + facet_count + feature_count")
best_model <- lm(form, t_df)

# On Test Set 
test_df$master_category_code = as.numeric(test_df$master_category_code)
y_preds <- predict(best_model, test_df)
y_preds[y_preds > 1] = 1
y_preds[y_preds < 0] = 0
test_df$y_preds = y_preds
test_df$master_category_code = as.factor(test_df$master_category_code)
title_str = paste0("Actual vs. Predicted Add To Cart Rate \nCorrelation ", 
                   round(cor(test_df$y_preds, test_df$atc_rate),3))

p <- ggplot(data = test_df, mapping = aes(x = atc_rate, y = y_preds2)) + 
  geom_point(mapping =  aes(color = master_category), alpha = 1/3) + 
  geom_smooth(method = "lm") + labs(x = "atc_rate", y = "predicted atc_rate") + 
  ggtitle(title_str)
print(p)
ggsave(filename= paste0(imagepath, '/lm_scatter_best_model', length(curr_m$coefficients), '2.png'), plot = p)



