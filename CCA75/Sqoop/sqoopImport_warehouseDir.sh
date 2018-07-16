#! /usr/bin/bash

export CONNECT=jdbc:mysql://ms.itversity.com/retail_db
export USER=retail_user
export PWD=itversity
export TBL=order_items
export WAREHOUSEDIR=/user/bbastola/sqoop_import/

sqoop import \
--connect $CONNECT \
--username $USER \
--password $PWD \
--table $TBL \
--warehouse-dir $WAREHOUSEDIR