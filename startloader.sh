#!/bin/bash
if [ -z $JAVA_HOME ]; then
  echo Please set the JAVA_HOME environment varible
  exit 1
fi

scriptdir=`dirname $0`
here=`pwd`

$JAVA_HOME/bin/java -cp target/hazelcast-hot-restart-example-1.0-SNAPSHOT.jar com.hazelcast.demo.Loader

cd $here
