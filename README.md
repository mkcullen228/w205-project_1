# w205-project_1

Operating Instructions:  
1.)  Create and/or launch MIDS 205 AMI instance on instance type m3.Large or better
(Detailed Instructions in Lab 1 Here: https://github.com/UC-Berkeley-I-School/w205-summer-17-labs-exercises/blob/master/lab_1/Lab1-w205.md)

2.)  Setup block storage and Hadoop
(Detailed Instructions in Lab 2 Here: https://github.com/UC-Berkeley-I-School/w205-summer-17-labs-exercises/blob/master/lab_2/Lab2.md)

3.)  as root,
--once- cd /usr/lib/hadoop-0.20-mapreduce/lib
--once- wget -O hivexmlserde-1.0.5.3.jar http://search.maven.org/remotecontent?filepath=com/ibm/spss/hive/serde2/xml/hivexmlserde/1.0.5.3/hivexmlserde-1.0.5.3.jar
--once- chmod 777 hivexmlserde-1.0.5.3.jar

4.)  $su w205,
      $export HADOOP_CLASSPATH=/usr/lib/hadoop/client

5.)  as w205
-- #For user-agents.org data
--once- cd /home/w205/
--once- mkdir project_1
--once- cd project_1
--once- wget http://www.user-agents.org/allagents.xml
--once- hdfs dfs -mkdir /user/w205/project_1
--once- hdfs dfs -mkdir /user/w205/project_1/user-agents
--once- hdfs dfs -put "/home/w205/project_1/allagents.xml" /user/w205/project_1/user-agents/allagents.xml

6.)  hive (as w205)
SET fs.s3.impl=org.apache.hadoop.fs.s3native.NativeS3FileSystem;
SET fs.s3.awsSecretAccessKey=INSERT SECRET ACCESS KEY FROM AWS ACCOUNT HERE;
SET fs.s3.awsAccessKeyId=INSERT ACCESS KEY FROM AWS ACCOUNT HERE;

add jar /usr/lib/hadoop-0.20-mapreduce/lib/hivexmlserde-1.0.5.3.jar;

run weblog_pipeline_hive.sql
run product_catalog_pipeline_hive.sql 
