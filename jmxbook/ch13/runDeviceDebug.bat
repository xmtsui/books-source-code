@echo OFF

set CLIENT_CLASS_DIR=D:\Source\JMXbook\src\build

REM Directory where jndi.properties is located
set JNDI_RESOURCE_DIR=resources

set JBOSS_DIST=d:\JBoss-2.4.4

REM Required libs to run client
set CLASSPATH=%JBOSS_DIST%\client\jbossmqclient.jar;%JBOSS_DIST%\client\jnp-client.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\client\jtaspec1_0_1.jar;%JBOSS_DIST%\client\jboss-j2ee.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\lib\ext\oswegoconcurrent.jar;%JBOSS_DIST%\client\log4j.jar

REM Aggregated classpath
set CLASSPATH=%CLASSPATH%;%CLIENT_CLASS_DIR%;%JNDI_RESOURCE_DIR%

echo "Running with classpath %CLASSPATH%"
%JAVA_HOME%\bin\java -classpath %CLASSPATH% jmxbook.ch13.DebugSubscriber

