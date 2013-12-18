set JNDI_RESOURCE_DIR=resources
set JBOSS_DIST=d:\JBoss-2.4.4
set CLIENT_CLASS_DIR=d:\Source\jmxbook\src\build
set JAVA_HOME=c:\jdk1.3

REM Required libs to run JMS client
set CLASSPATH=%JBOSS_DIST%\client\jbossmq-client.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\client\jnp-client.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\client\jta-spec1_0_1.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\client\jboss-j2ee.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\lib\ext\oswego-concurrent.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\client\log4j.jar
set CLASSPATH=%CLASSPATH%;%JMX_HOME%\jmx\lib\jmxri.jar
set CLASSPATH=%CLASSPATH%;%JMX_HOME%\jmx\lib\jmxtools.jar
set CLASSPATH=%CLASSPATH%;%JMX_HOME%\contrib\remoting\jar\jmx_remoting.jar
set CLASSPATH=%CLASSPATH%;%JBOSS_DIST%\lib\ext\jboss-j2ee.jar
set CLASSPATH=%CLASSPATH%;%CLIENT_CLASS_DIR%

REM Aggregated classpath
set CLASSPATH=%CLASSPATH%;%CLIENT_CLASS_DIR%;%JNDI_RESOURCE_DIR%

echo "Running with classpath %CLASSPATH%"
%JAVA_HOME%\bin\java -classpath %CLASSPATH% jmxbook.ch3.JMXBookAgent
