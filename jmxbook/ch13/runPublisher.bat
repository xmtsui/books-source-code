@echo OFF


REM JMSPublisher class
set CLIENT_CLASS_DIR=d:\Source\jmxbook\src\build

REM Directory where jndi.properties is located
set JNDI_RESOURCE_DIR=resources

set JBOSS_DIST=d:\JBoss-2.4.4

REM Required libs to run client
set CLASSPATH=%JBOSS_DIST%\client\jbossmq-client.jar;%JBOSS_DIST%\client\jnp-client.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\client\jta-spec1_0_1.jar;%JBOSS_DIST%\client\jboss-j2ee.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\lib\ext\oswego-concurrent.jar;%JBOSS_DIST%\client\log4j.jar

REM Agregated classpath
set CLASSPATH=%CLASSPATH%;%CLIENT_CLASS_DIR%;%JNDI_RESOURCE_DIR%

echo "Running with classpath %CLASSPATH%"
%JAVA_HOME%\bin\java -classpath %CLASSPATH% jmxbook.ch13.JMSPublisher
