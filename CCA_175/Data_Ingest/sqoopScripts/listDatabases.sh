#! /usr/bin/bash

export CONNECT=jdbc:mysql://ms.itversity.com
export USER=retail_user
export PWD=itversity

sqoop list-databases --connect $CONNECT --username $USER --password $PWD







