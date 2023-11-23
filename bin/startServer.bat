@echo off

set JAVA=%JAVA_HOME%\bin\java
set RMIREGISTRY=%JAVA_HOME%\bin\rmiregistry

set DEPLOY_DIR=..\build
set LIB_DIR=..\lib

set CLASSPATH=%DEPLOY_DIR%\server.jar;%DEPLOY_DIR%\common.jar;%LIB_DIR%\mysql-connector.jar

start %RMIREGISTRY%

%JAVA% -cp %CLASSPATH% com.yaps.petstore.server.RegisterServices

pause