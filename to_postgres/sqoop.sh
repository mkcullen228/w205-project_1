#!/bin/bash
#
#  This script executes a number of sqoop commands to import data from HDFS into Postgres database.
#
#  It assumes
#
#   1. The database, ebags_weblog, has been created a priori
#   2. ANT is installed on the UCB provided AMI
#   3. Packages, asciidoc and xmlto, has been installed on the image
#   4. Sqoop version 1.5.0 has been downloaded from Apache github repository and compiled.
#   5. Hive has been set up to use Postgres and the the weblog pipelining job has been executed.
#
#   Import the weblog data from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
       --username hiveuser --input-fields-terminated-by "\t" --export-dir=hdfs://localhost:8020/user/hive/warehouse/weblog/ --table=weblog
#
#   Import weblog user agent information from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N'  --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\t" --export-dir=hdfs://localhost:8020/user/hive/warehouse/weblog_user_agents/ \
    --table=weblog_user_agents
#
#   Import weblog bots and spiders information from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\t" --export-dir=hdfs://localhost:8020/user/hive/warehouse/user_agent_bots_spiders/ \
    --table=user_agent_bots_spiders
#
#   Import user agent exclusion information from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\t" --export-dir=hdfs://localhost:8020/user/hive/warehouse/user_agent_exclusion/ \
    --table=user_agent_exclusion
#
#   Import filtered weblog data from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\001" --export-dir=hdfs://localhost:8020/user/hive/warehouse/weblog_filtered/ \
    --table=weblog_filtered
#
#    Import visitor information from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\001" --export-dir=hdfs://localhost:8020/user/hive/warehouse/visitor_model_view/ \
    --table=visitor_model_view
#
#    Import visitor information with models from HDFS
#
sqoop export --input-null-string '\\N' --input-null-non-string '\\N' --connect jdbc:postgresql://localhost/ebags_weblog \
    --username hiveuser --input-fields-terminated-by "\001" --export-dir=hdfs://localhost:8020/user/hive/warehouse/visitor_model_atc/ \
    --table=visitor_model_atc