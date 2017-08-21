-- Set HADOOP_CLASSPATH=/usr/lib/hadoop/client (export HADOOP_CLASSPATH=/usr/lib/hadoop/client)
SET fs.s3.impl=org.apache.hadoop.fs.s3native.NativeS3FileSystem;

-- Find the following in your AWS account under Security Credentials, Access Keys (you may need to generate one)

SET fs.s3.awsSecretAccessKey=INSERT YOUR OWN SECRET KEY HERE;
SET fs.s3.awsAccessKeyId=INSERT YOUR OWN ACCESS KEY HERE;

--Create base schema

DROP TABLE IF EXISTS weblog_base_schema;
DROP TABLE IF EXISTS weblog;

CREATE EXTERNAL TABLE weblog_base_schema(
  utc_datetime STRING,
  mt_datetime STRING,
  utc_time_ms STRING,
  s_ip STRING,
  cs_method STRING,
  s_sitename STRING,
  cs_uri_stem STRING,
  s_computername STRING,
  cs_uri_query STRING,
  cs_user_agent STRING,
  s_port STRING,
  cs_referer STRING,
  cs_host STRING,
  sc_status STRING,
  sc_win32_status STRING,
  sc_bytes STRING,
  cs_bytes STRING,
  time_taken_ms STRING,
  visitor_id_hash STRING
 )
ROW FORMAT delimited fields terminated by "\t" stored as textfile
LOCATION 's3://midsw205project1/weblogs';

CREATE TABLE IF NOT EXISTS weblog
 (
  utc_datetime TIMESTAMP,
  mt_datetime TIMESTAMP,
  utc_time_ms INT,
  s_ip STRING,
  cs_method STRING,
  s_sitename STRING,
  cs_uri_stem STRING,
  s_computername STRING,
  cs_uri_query STRING,
  cs_user_agent STRING,
  s_port STRING,
  cs_referer STRING,
  cs_host STRING,
  sc_status STRING,
  sc_win32_status STRING,
  sc_bytes BIGINT,
  cs_bytes BIGINT,
  time_taken_ms BIGINT,
  visitor_id_hash STRING
 )
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY "\t" ;

--
--  Weblog table with transformations
--

INSERT OVERWRITE TABLE weblog
SELECT
  utc_datetime,
  mt_datetime,
  CAST(utc_time_ms AS INT),
  s_ip STRING,
  cs_method STRING,
  s_sitename STRING,
  cs_uri_stem STRING,
  s_computername STRING,
  cs_uri_query STRING,
  cs_user_agent STRING,
  s_port STRING,
  cs_referer STRING,
  cs_host STRING,
  sc_status STRING,
  sc_win32_status STRING,
  CAST(sc_bytes AS BIGINT),
  CAST(cs_bytes AS BIGINT),
  CAST(time_taken_ms AS BIGINT),
  visitor_id_hash STRING
FROM
  weblog_base_schema
;

--weblog Validations and explorations
SELECT count(*) from weblog;

SELECT cs_user_agent, count(*) as ct
FROM weblog
GROUP BY cs_user_agent
ORDER BY ct DESC;

SELECT count(*) from weblog where lower(cs_user_agent) not like '%bot%';

--
--Create Table for Model Page Views
--

DROP TABLE IF EXISTS visitor_model_view;

CREATE TABLE visitor_model_view AS SELECT
    cast(to_date(mt_datetime) as date) as mt_date,
    visitor_id_hash,
    cast(regexp_extract(lower(cs_uri_stem),'^/[h]?product/.*/([\\d]+)') as int) as model_id,
    count(*) as model_views
  FROM weblog
  WHERE (lower(cs_uri_stem) like '/product/%' or lower(cs_uri_stem) like '/hproduct/%')
    AND lower(cs_user_agent) not like '%bot%'
  GROUP BY to_date(mt_datetime), visitor_id_hash, cast(regexp_extract(lower(cs_uri_stem),'^/[h]?product/.*/([\\d]+)') as int);

--visitor_model_ view Validations and explorations
SELECT count(*) FROM weblog WHERE (lower(cs_uri_stem) like '/product/%' or lower(cs_uri_stem) like '/hproduct/%') and lower(cs_user_agent) not like '%bot%';

SELECT sum(model_views) FROM visitor_model_view;

SELECT sum(model_views) FROM visitor_model_view where model_id is NULL;

SELECT model_id, sum(model_views) as ct
FROM visitor_model_view 
GROUP BY model_id
ORDER BY ct DESC
LIMIT 100;

SELECT model_id, count(*) as ct
FROM visitor_model_view 
GROUP BY model_id
ORDER BY ct DESC
LIMIT 100;


--
--Create Table to Track Add to Carts (atc)
--

DROP TABLE IF EXISTS visitor_model_atc;

CREATE TABLE visitor_model_atc AS SELECT
    cast(to_date(mt_datetime) as date) as mt_date,
    visitor_id_hash,
    cast(regexp_extract(lower(cs_referer),'^.*/[h]?product/.*/([\\d]+)\\?.*') as int) as model_id,
    cast(regexp_extract(lower(cs_referer),'^.*/[h]?product/.*/[\\d]+\\?.*productid=([\\d]+).*') as int) as product_id,
    count(*) as model_atc
  FROM weblog
  WHERE (lower(cs_referer) like '%/product/%' or lower(cs_referer) like '%/hproduct/%')
    AND lower(cs_user_agent) not like '%bot%'
    AND (lower(cs_uri_stem) = '/api/cart/product/add' or lower(cs_uri_stem) = '/cart/add')
  GROUP BY to_date(mt_datetime), visitor_id_hash, cast(regexp_extract(lower(cs_referer),'^.*/[h]?product/.*/([\\d]+)\\?.*') as int),cast(regexp_extract(lower(cs_referer),'^.*/[h]?product/.*/[\\d]+\\?.*productid=([\\d]+).*') as int);

--visitor_model_atc Validations and explorations
SELECT count(*) FROM weblog WHERE (lower(cs_uri_stem) = '/api/cart/product/add' or lower(cs_uri_stem) = '/cart/add') and lower(cs_user_agent) not like '%bot%';

SELECT sum(model_atc) FROM visitor_model_atc;

SELECT sum(model_atc) FROM visitor_model_atc where model_id is NULL;

SELECT model_id, sum(model_atc) as ct
FROM visitor_model_atc 
GROUP BY model_id
ORDER BY ct DESC
LIMIT 100;

SELECT model_id, count(*) as ct
FROM visitor_model_atc
GROUP BY model_id
ORDER BY ct DESC
LIMIT 100;
