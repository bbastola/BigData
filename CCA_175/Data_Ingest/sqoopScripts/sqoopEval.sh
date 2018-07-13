#! /usr/bin/bash

export CONNECT=jdbc:mysql://ms.itversity.com/retail_db
export USER=retail_user
export PWD=itversity

sqoop eval \ 
--connect $CONNECT 
--username $USER 
--password $PWD
--query "select count(1) from orders"