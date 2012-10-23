#!/bin/bash

set -e
set -u

SECURE_LOCATION="$HOME/SECURE_LOCATION"
BASE_DIR="$HOME/flowfx-db-migrations"

JVM_PARAMETERS="
	-Djavax.net.ssl.keyStore=$SECURE_LOCATION/flowfx-keystore.jks
	-Djavax.net.ssl.keyStorePassword=flowfx
	-Djavax.net.ssl.trustStore=$SECURE_LOCATION/flowfx-truststore.jks
	-Djavax.net.ssl.trustStorePassword=flowfx
    -Dlogback.configurationFile=etc/logback-db-migrations.groovy
"

MAIN_CLASS=com.anz.flowfx.db.DbConnectionChecker
CLASSPATH=`echo $BASE_DIR/lib/*.jar | tr ' ' ':'`
CLASSPATH=$CLASSPATH:$BASE_DIR

cd $BASE_DIR &&
$JAVA_HOME/bin/java \
	-cp $CLASSPATH \
	$JVM_PARAMETERS \
	$MAIN_CLASS