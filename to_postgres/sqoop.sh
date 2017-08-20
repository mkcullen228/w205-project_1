sqoop export --connect jdbc:postgresql://localhost/ebags_weblog --username hiveuser --password w205  --input-fields-terminated-by "\t" --export-dir=hdfs://localhost:8020/user/hive/warehouse/weblog/ --table=weblog

sqoop export --connect jdbc:postgresql://localhost/ebags_weblog --username hiveuser --password w205  --input-fields-terminated-by "\t" --export-dir=hdfs://localhost:8020/user/hive/warehouse/weblog_user_agents/ --table=weblog_user_agents

sqoop export --connect jdbc:postgresql://localhost/ebags_weblog --username hiveuser --password w205  --input-fields-terminated-by "\t" --export-dir=hdfs://localhost:8020/user/hive/warehouse/user_agent_bots_spiders/ --table=user_agent_bots_spiders

sqoop export --connect jdbc:postgresql://localhost/ebags_weblog --username hiveuser --password w205  --input-fields-terminated-by "\t" --export-dir=hdfs://localhost:8020/user/hive/warehouse/user_agent_exclusion/ --table=user_agent_exclusion

sqoop export --connect jdbc:postgresql://localhost/ebags_weblog --username hiveuser --password w205 --input-fields-terminated-by "\001" --export-dir=hdfs://localhost:8020/user/hive/warehouse/weblog_filtered/ --table=weblog_filtered

sqoop export --connect jdbc:postgresql://localhost/ebags_weblog --username hiveuser --password w205 --input-fields-terminated-by "\001" --export-dir=hdfs://localhost:8020/user/hive/warehouse/visitor_model_view/ --table=visitor_model_view

sqoop export --connect jdbc:postgresql://localhost/ebags_weblog --username hiveuser --password w205 --input-fields-terminated-by "\001" --export-dir=hdfs://localhost:8020/user/hive/warehouse/visitor_model_atc/ --table=visitor_model_atc