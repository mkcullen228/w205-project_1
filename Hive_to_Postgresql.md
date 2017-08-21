# MIDS W205 Project: Data Transformations

## Introduction

The architecture for the weblog analysis calls for the transformed data to be stored in a relational database.
This allows the flexibility for data scientist to use standard ODBC type tools to interface with the database
to extract the new data and perform data analysis. In this document we will present the steps that we took to
implement the proposed architecture using a Postgresql database.

## Configuring Hive to Utilize Postgresql to Store Metadata 

By default, Hive uses derby to store the metadata about the schemas, databases and tables it creates. It also
stores the data in flat files. In order to easily insert the analyzed weblog data back into Postgres, we 
reconfigured Hive "metastore" to use a Postgresql database. The Postgresql software is installed in the AMI image that was provided as part of the class. Here are the steps required
to achieve this:

1. We first ensure that the property "listen\_address" was set to \*, and "standard\_conforming\_strings" property is set to off in the postgresql.conf file. This can be see in the code snippet, executed as "root", shown below:
#
```
$ cat /var/lib/pgsql/data/postgresql.conf  | grep -e listen -e standard_conforming_strings
listen_addresses = '*'
standard_conforming_strings = off
```

2. Next, we install "postgresql-jdbc" package and create symbolic link to the /usr/lib/hive/lib/ directory as shown below:
#
```
$ sudo yum install postgresql-jdbc
$ ln -s /usr/share/java/postgresql-jdbc.jar /usr/lib/hive/lib/postgresql-jdbc.jar
```

3. Creating the metastore database and user account is the next step. This is shown in the code. We used a password different than the one included in the code. Note that for the version of Hive that is provided in the AMI image (version 1.1.0-cdh5.4.5), the correct schema version is 1.1.0.
#
```
$ sudo -u postgres psql
postgres=# CREATE USER hiveuser WITH PASSWORD 'mypassword';
postgres=# CREATE DATABASE metastore;
postgres=# \c metastore;
You are now connected to database 'metastore'.
postgres=# \i /usr/lib/hive/scripts/metastore/upgrade/postgres/hive-schema-1.1.0.postgres.sql
```

4. Once we have the database created and the correct schema has been applied, next we need to provide the appropriate permissions for the user, "hiveuser", to access the database. This is shown in the code:
#
```
bash# sudo -u postgres psql
metastore=# \c metastore
metastore=# \pset tuples_only on
metastore=# \o /tmp/grant-privs
metastore=#   SELECT 'GRANT SELECT,INSERT,UPDATE,DELETE ON "'  || schemaname || '". "' ||tablename ||'" TO hiveuser ;'
metastore-#   FROM pg_tables
metastore-#   WHERE tableowner = CURRENT_USER and schemaname = 'public';
metastore=# \o
metastore=# \pset tuples_only off
metastore=# \i /tmp/grant-privs
```

5. We need to configure Hive to use the postgresql database that we have created. This is achieved by modifying the file, /usr/lib/hive/conf/hive-site.xml. Specifically, the changes shown below have to be made for Hive to start using the postgres database.
#
```
<property>
  <name>javax.jdo.option.ConnectionURL</name>
  <value>jdbc:postgresql://localhost/metastore</value>
</property> 
<property>
  <name>javax.jdo.option.ConnectionDriverName</name>
  <value>org.postgresql.Driver</value>
</property>
<property>
  <name>javax.jdo.option.ConnectionUserName</name>
  <value>hiveuser</value>
</property>
<property>
  <name>javax.jdo.option.ConnectionPassword</name>
  <value>mypassword</value>
</property>
<property>
  <name>datanucleus.autoCreateSchema</name>
  <value>false</value>
</property>
<property>
<name>hive.metastore.schema.verification</name>
<value>true</value>
</property>
```

Once these steps were performed, the configuration can be tested by executing the command ```hive -e "show tables;"```. A succesful completion of the test is the precursor to running our weblog processing pipeline to utilize the Postgres database.

## Creating a Postgresql Database to Store Processed Weblog Data

As mentioned earlier

