#!/bin/bash

for i in ../lib/*;
	do CLASSPATH=$i:"$CLASSPATH";
done
 	CLASSPATH="$base/conf:$CLASSPATH";

JVM_HEAP_SIZE_MB=512
JVM_HEAP_SIZE_MB=512

JAVA_OPTS="${JAVA_OPTS} -server -Xms${JVM_HEAP_SIZE_MB}m -Xmx${JVM_HEAP_SIZE_MB}m -Xss1m"
JAVA_OPTS="${JAVA_OPTS} -XX:+UseParallelGC"
JAVA_OPTS="${JAVA_OPTS} -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=2 -XX:NewRatio=1 -XX:ParallelGCThreads=6"
JAVA_OPTS="${JAVA_OPTS} -XX:-OmitStackTraceInFastThrow"

JAVA_OPTS="${JAVA_OPTS} -XX:+PrintGCDetails"
JAVA_OPTS="${JAVA_OPTS} -XX:+DisableExplicitGC"
#JAVA_OPTS="${JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${HEAP_FILE_DIR}"
JAVA_OPTS="${JAVA_OPTS} -Xloggc:${LOG_PATH}/gc.log"
JAVA_OPTS="${JAVA_OPTS} -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"
JAVA_OPTS="${JAVA_OPTS} -XX:MetaspaceSize=64m"

MAINCLASS=com.kaimingwan.core.MainSync
echo "java $JAVA_OPTS -classpath .:$CLASSPATH $MAINCLASS 1>>/dev/null 2>&1 &"

java -classpath .:$CLASSPATH  $MAINCLASS 1>>/dev/null 2>&1 &
