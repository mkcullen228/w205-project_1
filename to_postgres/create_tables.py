#!/usr/bin/python
#
#   the script file reads the Hive metadata store to figure out the
#   schema of managed tables in Hive and creates tables in a Postgres
#   database.
#
#   We presume Hive has been set up to use Postgres database with
#   the name metastore.
#

sd_id, tbl_name = subprocess.check_output(['psql', '-U metastore -e "select "SD_ID","TBL_NAME" from "TBLS" where "TBL_TYPE" = \'MANAGED_TABLE\';"'])
print sd_id, tbl_name
