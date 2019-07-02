#!/bin/bash
if [ -z $JAVA_HOME ]; then
  echo Please set the JAVA_HOME environment varible
  exit 1
fi

scriptdir=`dirname $0`
here=`pwd`

nohup $JAVA_HOME/bin/java -cp target/hazelcast-hot-restart-example-1.0-SNAPSHOT.jar com.hazelcast.demo.Server > server1.log 2>&1 &
nohup $JAVA_HOME/bin/java -cp target/hazelcast-hot-restart-example-1.0-SNAPSHOT.jar com.hazelcast.demo.Server > server2.log 2>&1 &

echo A local cluster launched.  Log files are in "server1.log" and "server2.log"

cd $here
