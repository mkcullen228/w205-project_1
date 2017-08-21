-- #For Amazon S3 library access (as user w205)
--once as w205: $export HADOOP_CLASSPATH=/usr/lib/hadoop/client
--run hive as w205:
SET fs.s3.impl=org.apache.hadoop.fs.s3native.NativeS3FileSystem;
SET fs.s3.awsSecretAccessKey=INSERT SECRET ACCESS KEY FROM AWS ACCOUNT HERE;
SET fs.s3.awsAccessKeyId=INSERT ACCESS KEY FROM AWS ACCOUNT HERE;- #For XML classes (as root):

-- #For XML classes:
--once- cd /usr/lib/hadoop-0.20-mapreduce/lib
--once as root: $wget -O hivexmlserde-1.0.5.3.jar http://search.maven.org/remotecontent?filepath=com/ibm/spss/hive/serde2/xml/hivexmlserde/1.0.5.3/hivexmlserde-1.0.5.3.jar
--once as root: $chmod 777 hivexmlserde-1.0.5.3.jar
add jar /usr/lib/hadoop-0.20-mapreduce/lib/hivexmlserde-1.0.5.3.jar;

--
-- #For user-agents.org data
--once as w205: $cd /home/w205/project_1
--once as w205: $wget http://www.user-agents.org/allagents.xml
--once as w205: $hdfs dfs -mkdir /user/w205/project_1
--once as w205: $hdfs dfs -mkdir /user/w205/project_1/user-agents
--once as w205: $hdfs dfs -put "/home/w205/project_1/allagents.xml" /user/w205/project_1/user-agents/allagents.xml


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
--OK
--7790143

SELECT cs_user_agent, count(*) as ct
FROM weblog
GROUP BY cs_user_agent
ORDER BY ct DESC;

SELECT count(*) from weblog where lower(cs_user_agent) not like '%bot%';
--OK
--6074339

--
--  Bot Table from User-Agents.org XML
--
DROP TABLE IF EXISTS USER_AGENTS;
CREATE EXTERNAL TABLE USER_AGENTS (ID STRING, Signature STRING, Description STRING, Type STRING)
 ROW FORMAT SERDE 'com.ibm.spss.hive.serde2.xml.XmlSerDe'
 WITH SERDEPROPERTIES (
 "column.xpath.ID"="/user-agent/ID/text()",
 "column.xpath.Signature"="/user-agent/String/text()",
 "column.xpath.Description"="/user-agent/Description/text()",
 "column.xpath.Type"="/user-agent/Type/text()"
 )
 STORED AS INPUTFORMAT 'com.ibm.spss.hive.serde2.xml.XmlInputFormat'
 OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.IgnoreKeyTextOutputFormat'
 LOCATION '/user/w205/project_1/user-agents/'
 TBLPROPERTIES ("xmlinput.start"="<user-agent>","xmlinput.end"= "</user-agent>");
 
 SELECT count(*) FROM USER_AGENTS;
 --OK
 --2459


--
-- Filtering R = (Robot, crawler, spider); S = (Spam or bad bot) User-Agents from weblogs (see User-Agents.org)
--
DROP TABLE IF EXISTS weblog_user_agents;
CREATE TABLE weblog_user_agents AS
SELECT distinct cs_user_agent from weblog;

SELECT count(*) from weblog_user_agents;
--OK
--from: 7790143
--to: 39136

DROP TABLE IF EXISTS user_agent_bots_spiders;
CREATE TABLE user_agent_bots_spiders AS
SELECT * FROM USER_AGENTS WHERE (Type LIKE '%R%' OR Type LIKE '%S%')
  AND lower(Signature) <> 'mozilla/4.0'
  AND lower(Signature) <> 'mozilla/4.7'
  AND lower(Signature) <> 'mozilla/5.0';
--  AND length(Signature) > 19; --Added to eliminate generic 'mozilla/4.0' generic signatures listed as R or S

SELECT count(*) from user_agent_bots_spiders;
--OK
--from: 2459
--to: 1527

DROP TABLE IF EXISTS user_agent_exclusion;
CREATE TABLE user_agent_exclusion AS
SELECT lower(wua.cs_user_agent) user_agent FROM weblog_user_agents wua CROSS JOIN user_agent_bots_spiders uabs WHERE lower(wua.cs_user_agent) LIKE CONCAT('%',lower(uabs.Signature),'%');

SELECT count(*) from user_agent_exclusion;
--OK
--16

--
DROP TABLE IF EXISTS weblog_filtered;
CREATE TABLE weblog_filtered AS
SELECT * FROM weblog w
LEFT OUTER JOIN user_agent_exclusion uae
ON lower(uae.user_agent) = lower(w.cs_user_agent)
WHERE uae.user_agent IS NULL;

SELECT count(*) from weblog where cs_user_agent is null;
--OK
--0

SELECT count(*) from weblog_filtered where cs_user_agent is null;
--OK
--0

SELECT count(*) from weblog_filtered;
--OK
--from: 7790143
--to: 7738972

SELECT count(*) from weblog_filtered 
where lower(cs_user_agent) not like '%bot%'
and lower(cs_user_agent) not like '%spider%'
and lower(cs_user_agent) not like '%crawl%';
--OK
--from: 6074339
--to: 6009542

SELECT cs_user_agent, count(*) as ct
FROM weblog
where lower(cs_user_agent) not like '%bot%'
and lower(cs_user_agent) not like '%spider%'
and lower(cs_user_agent) not like '%crawl%'
GROUP BY cs_user_agent
ORDER BY ct DESC
LIMIT 100;

--
--Create Table for Model Page Views
--

DROP TABLE IF EXISTS visitor_model_view;

CREATE TABLE visitor_model_view AS SELECT
    cast(to_date(mt_datetime) as date) as mt_date,
    visitor_id_hash,
    cast(regexp_extract(lower(cs_uri_stem),'^/[h]?product/.*/([\\d]+)') as int) as model_id,
    count(*) as model_views
  FROM weblog_filtered
  WHERE (lower(cs_uri_stem) like '/product/%' or lower(cs_uri_stem) like '/hproduct/%')
    AND lower(cs_user_agent) not like '%bot%' AND lower(cs_user_agent) not like '%spider%' AND lower(cs_user_agent) not like '%crawl%'
  GROUP BY to_date(mt_datetime), visitor_id_hash, cast(regexp_extract(lower(cs_uri_stem),'^/[h]?product/.*/([\\d]+)') as int);

--visitor_model_ view Validations and explorations
SELECT count(*) FROM weblog_filtered WHERE (lower(cs_uri_stem) like '/product/%' or lower(cs_uri_stem) like '/hproduct/%') 
  AND lower(cs_user_agent) not like '%bot%' AND lower(cs_user_agent) not like '%spider%' AND lower(cs_user_agent) not like '%crawl%';
--OK
--3849096

SELECT sum(model_views) FROM visitor_model_view;
--OK
--3849096

SELECT sum(model_views) FROM visitor_model_view where model_id is NULL;
--OK
--3555

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
  FROM weblog_filtered
  WHERE (lower(cs_referer) like '%/product/%' or lower(cs_referer) like '%/hproduct/%')
    AND lower(cs_user_agent) not like '%bot%' AND lower(cs_user_agent) not like '%spider%' AND lower(cs_user_agent) not like '%crawl%'
    AND (lower(cs_uri_stem) = '/api/cart/product/add' or lower(cs_uri_stem) = '/cart/add')
  GROUP BY to_date(mt_datetime), visitor_id_hash, cast(regexp_extract(lower(cs_referer),'^.*/[h]?product/.*/([\\d]+)\\?.*') as int),cast(regexp_extract(lower(cs_referer),'^.*/[h]?product/.*/[\\d]+\\?.*productid=([\\d]+).*') as int);

--visitor_model_atc Validations and explorations
SELECT count(*) FROM weblog_filtered WHERE (lower(cs_uri_stem) = '/api/cart/product/add' or lower(cs_uri_stem) = '/cart/add') and lower(cs_user_agent) not like '%bot%';
--OK
--352935

SELECT sum(model_atc) FROM visitor_model_atc;
--OK
--349876

SELECT sum(model_atc) FROM visitor_model_atc where model_id is NULL;
--OK
--3405

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

--Exploratory Analysis
--https://stackoverflow.com/questions/17837871/how-to-copy-file-from-hdfs-to-the-local-file-system
--  hdfs dfs -copyToLocal <hdfs_input_file_path> <output_path>  (hdfs dfs -copyToLocal /user/w205/project_1/000000_0 /home/w205/000000_0)
--  Win SCP to pull off from image to local
DROP TABLE IF EXISTS model_views_atc_report;
CREATE EXTERNAL TABLE IF NOT EXISTS model_views_atc_report
(
  model_id int,
  model_views bigint,
  model_atc bigint
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t' ESCAPED BY '"' LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/w205/project_1/';

INSERT OVERWRITE TABLE model_views_atc_report
SELECT v.model_id, m_v, m_atc
FROM
(SELECT model_id, count(model_views) as m_v
 FROM visitor_model_view 
 GROUP BY model_id) v,
(SELECT model_id, count(model_atc) as m_atc
 FROM visitor_model_atc 
 GROUP BY model_id) a
WHERE v.model_id=a.model_id
ORDER by m_v DESC;

