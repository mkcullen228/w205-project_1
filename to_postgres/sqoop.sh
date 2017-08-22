#!/bin/bash
#
#  This script executes a number of sqoop commands to import data from HDFS into Postgres database.
#
#  It assumes
#
#   1. The database, prod_cat_stats, has been created a priori
#   2. ANT is installed on the UCB provided AMI
#   3. Packages, asciidoc and xmlto, has been installed on the image
#   4. Sqoop version 1.5.0 has been downloaded from Apache github repository and compiled.
#   5. Hive has been set up to use Postgres and the the weblog pipelining job has been executed.
#
#   Set the location of the Hive "warehouse" in HDFS.
#
#   **** THIS HAS TO BE CHANGED IF HIVE STORES ITS DATA IN A DIFFERENT LOCATION (OR MACHINE) ***
#
HiveHDFS = hdfs://localhost:8020/user/hive/warehouse
#
#   Next import weblog data from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
       --username hiveuser --input-fields-terminated-by "\t" --export-dir=${HiveHDFS}/weblog/ --table=weblog
#
#   Import weblog user agent information from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N'  --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\t" --export-dir=${HiveHDFS}/weblog_user_agents/ \
    --table=weblog_user_agents
#
#   Import weblog bots and spiders information from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\t" --export-dir=${HiveHDFS}/user_agent_bots_spiders/ \
    --table=user_agent_bots_spiders
#
#   Import user agent exclusion information from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\t" --export-dir=${HiveHDFS}/user_agent_exclusion/ \
    --table=user_agent_exclusion
#
#   Import filtered weblog data from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\001" --export-dir=${HiveHDFS}/weblog_filtered/ \
    --table=weblog_filtered
#
#    Import visitor information from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\001" --export-dir=${HiveHDFS}/visitor_model_view/ \
    --table=visitor_model_view
#
#    Import visitor information with models from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\001" --export-dir=${HiveHDFS}/visitor_model_atc/ \
    --table=visitor_model_atc