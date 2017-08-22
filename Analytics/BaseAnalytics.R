df = read.csv("C:/Users/mfraz/Desktop/Berkeley MIDS/MIDS/W205/Project 1/model_summary.csv")# read csv file
df = subset(df, df$atc_rate<=1 & df$status=='Active')

cor(df$atc_rate,as.numeric(df$image_count))
cor(df$atc_rate,as.numeric(df$feature_count))
cor(df$atc_rate,as.numeric(df$attribute_count))
cor(df$atc_rate,as.numeric(df$facet_count))

df_backpacks = subset(df, df$master_category == 'Backpacks')
df_luggage = subset(df, df$master_category == 'Luggage')
df_handbags = subset(df, df$master_category == 'Handbags')

cor(df_backpacks$atc_rate,as.numeric(df_backpacks$image_count))
cor(df_backpacks$atc_rate,as.numeric(df_backpacks$feature_count))
cor(df_backpacks$atc_rate,as.numeric(df_backpacks$attribute_count))
cor(df_backpacks$atc_rate,as.numeric(df_backpacks$facet_count))

cor(df_luggage$atc_rate,as.numeric(df_luggage$image_count))
cor(df_luggage$atc_rate,as.numeric(df_luggage$feature_count))
cor(df_luggage$atc_rate,as.numeric(df_luggage$attribute_count))
cor(df_luggage$atc_rate,as.numeric(df_luggage$facet_count))

cor(df_handbags$atc_rate,as.numeric(df_handbags$image_count))
cor(df_handbags$atc_rate,as.numeric(df_handbags$feature_count))
cor(df_handbags$atc_rate,as.numeric(df_handbags$attribute_count))
cor(df_handbags$atc_rate,as.numeric(df_handbags$facet_count))
