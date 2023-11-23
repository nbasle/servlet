@echo off

set JAVA=%JAVA_HOME%\bin\java
set RMIREGISTRY=%JAVA_HOME%\bin\rmiregistry

set DEPLOY_DIR=..\build
set LIB_DIR=..\lib

SET CLASSPATH2=D:\PROJET\CNAM\TP06\servlet\webapps\petstore\WEB-INF\classes;D:\PROJET\CNAM\TP06\servlet\classes
set CLASSPATH=%CLASSPATH2%;%DEPLOY_DIR%\server.jar;%DEPLOY_DIR%\common.jar;%LIB_DIR%\mysql-connector.jar

start %RMIREGISTRY%

%JAVA% -cp %CLASSPATH% com.yaps.petstore.server.RegisterServices

pause