-- #For XML classes:
--once- cd /usr/lib/hadoop/lib
--once- wget -o hivexmlserde-1.0.5.3.jar http://search.maven.org/remotecontent?filepath=com/ibm/spss/hive/serde2/xml/hivexmlserde/1.0.5.3/hivexmlserde-1.0.5.3.jar
--once- chmod 777 hivexmlserde-1.0.5.3.jar
add jar /usr/lib/hadoop-0.20-mapreduce/lib/hivexmlserde-1.0.5.3.jar;
--
--
-- #For Product Catalog File
--once- hdfs dfs -mkdir /user/w205/project_1/product_catalog
--once- hdfs dfs -put "/home/w205/project_1/eBags_ChannelAdvisor_CSEAndKeywordsProducts_20170705_115248.xml" /user/w205/project_1/product_catalog/eBags_ChannelAdvisor_CSEAndKeywordsProducts_20170705_115248.xml
--


--
--  Product Catalog
--
DROP TABLE IF EXISTS model_level_info_raw;
CREATE EXTERNAL TABLE model_level_info_raw 
(model_id STRING, status STRING, brand STRING, description STRING, images STRING, features STRING, attributes STRING, facets STRING, product_rating STRING, master_category STRING, sub_category STRING,
  model_url STRING, review_count STRING, color_count STRING)
 ROW FORMAT SERDE 'com.ibm.spss.hive.serde2.xml.XmlSerDe'
 WITH SERDEPROPERTIES (
  "column.xpath.model_id"="/ProductCatalogItem/ProductId/text()",
  "column.xpath.status"="/ProductCatalogItem/ProductStatusCode/text()",
  "column.xpath.brand"="/ProductCatalogItem/BrandName/text()",
  "column.xpath.description"="/ProductCatalogItem/Description/text()",
  "column.xpath.images"="/ProductCatalogItem/Image/*",
  "column.xpath.features"="/ProductCatalogItem/ProductFeature/*",
  "column.xpath.attributes"="/ProductCatalogItem/Attribute/*",
  "column.xpath.facets"="/ProductCatalogItem/ProductAttribute/*",
  "column.xpath.product_rating"="/ProductCatalogItem/ProductRating/text()",
  "column.xpath.master_category"="/ProductCatalogItem/MasterCategory/text()",
  "column.xpath.sub_category"="/ProductCatalogItem/FinancialCategory/text()",
  "column.xpath.model_url"="/ProductCatalogItem/ActionURL/text()",
  "column.xpath.review_count"="/ProductCatalogItem/TestimonialCount/text()", 
  "column.xpath.color_count"="/ProductCatalogItem/NumberOfColors/text()"
 )
 STORED AS INPUTFORMAT 'com.ibm.spss.hive.serde2.xml.XmlInputFormat'
 OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.IgnoreKeyTextOutputFormat'
  LOCATION '/user/w205/project_1/product_catalog/'
  TBLPROPERTIES ("xmlinput.start"="<ProductCatalogItem>","xmlinput.end"= "</ProductCatalogItem>");
  
   
DROP TABLE IF EXISTS model_images;
CREATE TABLE model_images AS
SELECT regexp_extract(model_id,'(\\d+)_\\d+') as model_id, regexp_extract(model_id,'\\d+_(\\d+)') as model_price_group,
  a.model_image_url as model_image_url
FROM model_level_info_raw 
LATERAL VIEW explode(xpath(images, 'string/ImageUrl/text()')) a as model_image_url
WHERE images is not NULL;

DROP TABLE IF EXISTS model_images_grouped;
CREATE TABLE model_images_grouped AS
SELECT cast(model_id as int) as model_id, cast(min(model_price_group) as int) as default_price_group, model_image_url as model_image_url
FROM model_images
GROUP BY model_id, model_price_group, model_image_url;
DROP TABLE model_images;


DROP TABLE IF EXISTS model_features;
CREATE TABLE model_features AS
SELECT regexp_extract(model_id,'(\\d+)_\\d+') as model_id, regexp_extract(model_id,'\\d+_(\\d+)') as model_price_group, a.model_feature as model_feature
FROM model_level_info_raw
LATERAL VIEW explode(xpath(features, 'string/DescriptionText/text()')) a as model_feature
WHERE features is not NULL;

DROP TABLE IF EXISTS model_features_grouped;
CREATE TABLE model_features_grouped AS
SELECT cast(model_id as int) as model_id, cast(min(model_price_group) as int) as default_price_group, model_feature as model_feature 
FROM model_features
GROUP BY model_id, model_price_group, model_feature;
DROP TABLE model_features;


DROP TABLE IF EXISTS model_attributes;
CREATE TABLE model_attributes AS
SELECT regexp_extract(model_id,'(\\d+)_\\d+') as model_id, regexp_extract(model_id,'\\d+_(\\d+)') as model_price_group, a.description as description, b.measure_value as measure_value, c.uom as uom, d.group_code as group_code, e.measure_type as measure_type
FROM model_level_info_raw
  LATERAL VIEW posexplode(xpath(attributes,'string/Description/text()')) a as pos_a, description
  LATERAL VIEW posexplode(xpath(attributes,'string/MeasureValue/text()')) b as pos_b, measure_value
  LATERAL VIEW posexplode(xpath(attributes,'string/UnitOfMeasureCode/text()')) c as pos_c, uom
  LATERAL VIEW posexplode(xpath(attributes,'string/AttributeGroupCode/text()')) d as pos_d, group_code 
  LATERAL VIEW posexplode(xpath(attributes,'string/MeasureTypeCode/text()')) e as pos_e, measure_type 
WHERE attributes is not NULL
AND pos_a = pos_b and pos_b = pos_c AND pos_c = pos_d AND pos_d = pos_e;

DROP TABLE IF EXISTS model_attributes_grouped;
CREATE TABLE model_attributes_grouped AS
SELECT cast(model_id as int) as model_id, cast(min(model_price_group) as int) as default_price_group,
  description as description, measure_value as measure_value, uom as uom, group_code as group_code, measure_type as measure_type 
FROM model_attributes
GROUP BY model_id, model_price_group, description, measure_value, uom, group_code, measure_type;
DROP TABLE model_features;


DROP TABLE IF EXISTS model_facets;
CREATE TABLE model_facets AS
SELECT regexp_extract(model_id,'(\\d+)_\\d+') as model_id, regexp_extract(model_id,'\\d+_(\\d+)') as model_price_group, a.name as facet_name, b.value as facet_value
FROM model_level_info_raw
                  LATERAL VIEW posexplode(xpath(facets,'string/AttributeName/text()')) a as pos_a, name
                  LATERAL VIEW posexplode(xpath(facets,'string/AttributeValue/text()')) b as pos_b, value
WHERE facets is not NULL
AND pos_a = pos_b;

DROP TABLE IF EXISTS model_facets_grouped;
CREATE TABLE model_facets_grouped AS
SELECT cast(model_id as int) as model_id, cast(min(model_price_group) as int) as default_price_group,
  facet_name as facet_name, facet_value as facet_value
FROM model_facets
GROUP BY model_id, model_price_group, facet_name, facet_value;
DROP TABLE model_facets;
-->
DROP TABLE IF EXISTS model_grouped;
CREATE TABLE model_grouped AS
SELECT cast(regexp_extract(model_id,'(\\d+)_\\d+') as int) as model_id, 
       cast(min(regexp_extract(model_id,'\\d+_(\\d+)')) as int) as default_price_group, 
       cast(count(distinct regexp_extract(model_id,'\\d+_(\\d+)')) as int) as price_group_count,
  min(status) as status, min(brand) as brand, min(description) as description, min(product_rating) as product_rating,
  min(master_category) as master_category, min(sub_category) as sub_category, min(model_url) as model_url,
  cast(min(review_count) as int) as review_count, cast(min(color_count) as int) as color_count
FROM model_level_info_raw
GROUP BY
  model_id;

DROP TABLE IF EXISTS model;
CREATE TABLE model AS
SELECT m.model_id as model_id, m.default_price_group as default_price_group, m.price_group_count as price_group_count,
  m.status, m.brand, m.description, m.product_rating, m.master_category, m.sub_category, m.model_url, 
  cast(min(review_count) as int) as review_count, cast(min(color_count) as int) as color_count,
  cast(count(distinct mi.model_image_url) as int) as image_count, cast(count(distinct mft.model_feature) as int) as feature_count, 
  cast(count(distinct ma.description) as int) as attribute_count, cast(count(distinct mfa.facet_name) as int) as facet_count
FROM model_grouped m
LEFT OUTER JOIN model_images_grouped mi ON (m.model_id = mi.model_id AND m.default_price_group = mi.default_price_group)
LEFT OUTER JOIN model_features_grouped mft ON (m.model_id = mft.model_id AND m.default_price_group = mft.default_price_group)
LEFT OUTER JOIN model_attributes_grouped ma ON (m.model_id = ma.model_id AND m.default_price_group = ma.default_price_group)
LEFT OUTER JOIN model_facets_grouped mfa ON (m.model_id = mfa.model_id AND m.default_price_group = mfa.default_price_group)
GROUP BY m.model_id, m.default_price_group, m.price_group_count, m.status, m.brand, m.description, m.product_rating, m.master_category, m.sub_category, 
  m.model_url, m.review_count, m.color_count;
DROP TABLE model_grouped;

--Validating the Tables:

SELECT * FROM model
WHERE model_id=1;

SELECT * FROM model_images
WHERE model_id='1' AND model_price_group = '1';

SELECT * FROM model_features
WHERE model_id='1' AND model_price_group = '1';

SELECT * FROM model_attributes
WHERE model_id='1' AND model_price_group = '1';

SELECT * FROM model_facets
WHERE model_id='1' AND model_price_group = '1';


