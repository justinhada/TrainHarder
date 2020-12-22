#!/bin/bash

echo "=> Initialisiere JBoss"
JBOSS_HOME="$1"
BATCH_FILE="$2"

JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE="standalone"
JBOSS_CONFIG="standalone.xml"

function wait_for_server() {
 until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do
   sleep 1
 done
}

echo "=> Starte JBoss Server"

echo "JBOSS_HOME  : " $JBOSS_HOME
echo "JBOSS_CLI   : " $JBOSS_CLI
echo "JBOSS_MODE  : " $JBOSS_MODE
echo "JBOSS_CONFIG: " $JBOSS_CONFIG
echo "BATCH_FILE  : " $BATCH_FILE

echo $JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG &
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG &

echo "=> Warte auf Start des Servers"
wait_for_server

echo "=> Konfiguriere JBoss"
$JBOSS_CLI -c --file=$BATCH_FILE
