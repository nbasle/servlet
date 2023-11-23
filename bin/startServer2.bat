@echo off

set JAVA=%JAVA_HOME%\bin\java
set RMIREGISTRY=%JAVA_HOME%\bin\rmiregistry

set DEPLOY_DIR=..\build
set LIB_DIR=..\lib
set LIB_TOMCAT=C:\Progam Files\Apache Software Foundation\Tomcat 5.0
set LIB_CUST=%LIB_TOMCAT%\webapps

set CLASSPATH=%LIB_CUST%\server.jar;%LIB_CUST%\common.jar;%LIB_CUST%\mysql-connector.jar

start %RMIREGISTRY%

%JAVA% -cp %CLASSPATH% com.yaps.petstore.server.RegisterServices

pause
