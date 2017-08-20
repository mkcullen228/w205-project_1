--
-- First create the database that will store all the processed weblog data
--
-- It is presumed the user is starting the script logged on as user "postgres"
-- We will create the database and transfer the ownership to user "hiveuser"
-- 
CREATE DATABASE ebags_weblog
       WITH OWNER = hiveuser;
--
-- Create tables in preparation for importing data from HDFS into postgres
--
-- The tables are created in the database we just created
--
\c ebags_weblog
--
--  The weblog table
--
CREATE TABLE  weblog
 (
  utc_datetime TIMESTAMP,
  mt_datetime TIMESTAMP,
  utc_time_ms INT,
  s_ip VARCHAR,
  cs_method VARCHAR,
  s_sitename VARCHAR,
  cs_uri_stem VARCHAR,
  s_computername VARCHAR,
  cs_uri_query VARCHAR,
  cs_user_agent VARCHAR,
  s_port VARCHAR,
  cs_referer VARCHAR,
  cs_host VARCHAR,
  sc_status VARCHAR,
  sc_win32_status VARCHAR,
  sc_bytes BIGINT,
  cs_bytes BIGINT,
  time_taken_ms BIGINT,
  visitor_id_hash VARCHAR
 );

ALTER TABLE weblog OWNER TO hiveuser;
--
-- Filtering R = (Robot, crawler, spider); S = (Spam or bad bot) User-Agents from weblogs (see User-Agents.org)
--
CREATE TABLE  weblog_user_agents
 (     
  cs_user_agent VARCHAR
 );
ALTER TABLE weblog_user_agents OWNER TO hiveuser;


CREATE TABLE  user_agent_bots_spiders
 (
  id VARCHAR,
  signature VARCHAR,
  description VARCHAR,
  type VARCHAR
 );
ALTER TABLE user_agent_bots_spiders OWNER TO hiveuser;


CREATE TABLE  user_agent_exclusion
 (
   user_agent VARCHAR
 );
ALTER TABLE user_agent_exclusion OWNER TO hiveuser;


CREATE TABLE  weblog_filtered
 (
  utc_datetime TIMESTAMP,
  mt_datetime TIMESTAMP,
  utc_time_ms INT,
  s_ip VARCHAR,
  cs_method VARCHAR,
  s_sitename VARCHAR,
  cs_uri_stem VARCHAR,
  s_computername VARCHAR,
  cs_uri_query VARCHAR,
  cs_user_agent VARCHAR,
  s_port VARCHAR,
  cs_refere VARCHAR,
  cs_host VARCHAR,
  sc_status VARCHAR,
  sc_win32_status VARCHAR,
  sc_bytes BIGINT,
  cs_bytes BIGINT,
  time_taken_ms BIGINT,
  visitor_id_hash VARCHAR,
  user_agent VARCHAR
 );

ALTER TABLE weblog_filtered OWNER TO hiveuser;

--
--Create Table for Model Page Views
--
CREATE TABLE  visitor_model_view
 (
  mt_date DATE,
  visitor_id_hash VARCHAR,
  model_id VARCHAR,
  model_views BIGINT
 );
ALTER TABLE visitor_model_view OWNER TO hiveuser;

--
--Create Table to Track Add to Carts (atc)
--
CREATE TABLE  visitor_model_atc
 (
  mt_date DATE,
  visitor_id_hash VARCHAR,
  model_id VARCHAR,
  product_id VARCHAR,
  model_atc BIGINT
 );
ALTER TABLE visitor_model_atc OWNER TO hiveuser;