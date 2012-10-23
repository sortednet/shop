#!/bin/bash

set -e
set -u

usage() {
	echo "Usage: `basename $0` migrate|reset|migrateAll|resetAll"
	exit 1
}

if [ $# -ne 1 ]; then
	usage
fi

CMD=$1

if [ $CMD != "migrate" -a $CMD != "reset" -a $CMD != migrateAll -a $CMD != resetAll ]; then
	usage
fi

SECURE_LOCATION="$HOME/SECURE_LOCATION"
BASE_DIR="$HOME/flowfx-db-migrations"

PROCESS_ID=flowfx-db-migrations
PROCESS_NAME="FlowFX migrate DB"

JVM_PARAMETERS="
	-Djavax.net.ssl.keyStore=$SECURE_LOCATION/flowfx-keystore.jks
	-Djavax.net.ssl.keyStorePassword=flowfx
	-Djavax.net.ssl.trustStore=$SECURE_LOCATION/flowfx-truststore.jks
	-Djavax.net.ssl.trustStorePassword=flowfx
	-Dlogback.configurationFile=etc/logback-db-migrations.groovy
"

MAIN_CLASS=com.anz.flowfx.db.Main
CLASSPATH=`echo $BASE_DIR/lib/*.jar | tr ' ' ':'`
CLASSPATH=$CLASSPATH:$BASE_DIR

CONSOLE_LOG_FILE=$HOME/log/$PROCESS_ID-console.log

cd $BASE_DIR

echo "Executing $PROCESS_NAME with '$CMD'"
if $JAVA_HOME/bin/java \
		-DPROCESS_ID=$PROCESS_ID \
		-cp $CLASSPATH \
		$JVM_PARAMETERS \
		$MAIN_CLASS \
		$CMD \
		&> $CONSOLE_LOG_FILE; then
	echo "$PROCESS_NAME with '$CMD' has been completed successfully"
	exit 0
else
	echo "$PROCESS_NAME with '$CMD' has failed"
	exit 1
fi