#! /usr/bin/bash

export CONNECT=jdbc:mysql://ms.itversity.com/retail_db
export USER=retail_user
export PWD=itversity
export TBL=order_items
export TARGETDIR=/user/bbastola/sqoop_import/order_items

sqoop import \
--connect $CONNECT \
--username $USER \
--password $PWD \
--table $TBL \
--target-dir $TARGETDIR

