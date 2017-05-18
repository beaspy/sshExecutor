@echo off

chcp 65001

set JAVA_HOME=%JAVA_HOME%
set JAVA_OPTS=-Xms256m -Xmx512m

setlocal enabledelayedexpansion

for %%c in ("*.jar") do set CLASSPATH=!CLASSPATH!;%%c

if exist "%JAVA_HOME%\bin\java.exe" goto okHome 

echo The JAVA_HOME environment variable is not defined correctly
@pause

:okhome 
echo Using JAVA_HOME %JAVA_HOME%
echo Using JAVA_OPTS %JAVA_OPTS%
"%JAVA_HOME%\bin\java" %JAVA_OPTS% -cp %CLASSPATH% com.tlb.ssh.yst.MyExecutor
@pause

:end