#!/usr/bin/python
#
#   the script file reads the Hive metadata store to figure out the
#   schema of managed tables in Hive and creates tables in a Postgres
#   database.
#
#   We presume Hive has been set up to use Postgres database with
#   the name metastore.
#
import subprocess
import sys
import os

p_id = os.getpid()
os.chdir('/tmp')
f_name = 'eBags_tables_' + str(p_id) + '.out'

p = subprocess.check_call(['psql', '-t', '-o', f_name, '-U', 'hiveuser', 'metastore', '-c', 
                      'select "SD_ID","TBL_NAME" from "TBLS" where "TBL_TYPE" = \'MANAGED_TABLE\';'])
#
#  read the file back in
#
tables = open(f_name, 'r').readlines()

table_dict = {}
for lines in tables:
    if lines.strip():
        table_dict.update({lines.split('|')[1].strip() : lines.split('|')[0].strip()})

p = os.remove(f_name)

#

f_name = "eBags_columns_" + str(p_id)
f_tablenames = []
for keys in table_dict.keys():
    f_name_table = f_name + table_dict[keys] + ".out"
    f_tablenames.append(f_name_table)
    select_stmt = 'select c2."CD_ID", c2."COLUMN_NAME", c2."TYPE_NAME", tbls."TBL_NAME", tbls."LOCATION" \
                   FROM "COLUMNS_V2" AS c2 \
                   JOIN (SELECT * FROM "TBLS" JOIN "SDS" ON "TBLS"."SD_ID" = "SDS"."SD_ID") AS tbls \
                   ON c2."CD_ID" = tbls."CD_ID" WHERE tbls."CD_ID" = ' + table_dict[keys] + ';'
    p = subprocess.check_call(['psql', '-t', '-o', f_name_table, '-U', 'hiveuser', 'metastore', '-c', 
                   select_stmt], stdout=subprocess.PIPE)
    #
    #   Now build the tables in the default database, ebags_weblog, using the information stored in the file
    #
    col = open(f_name_table, 'r').readlines()
    
    col_dict = {}
    for lines in col:
        if lines.strip():
            col_dict.update({lines.split('|')[1].strip() : lines.split('|')[2].strip()})

    print keys
    print col_dict

