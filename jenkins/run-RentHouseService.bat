@echo off
REM ----------------------------------------
REM 1) 변수 정의
REM ----------------------------------------
set JAVA_EXE=C:\Program Files\Java\jdk-17.0.0.1\bin\java.exe
set APP_DIR=D:\RentHouse
set APP_JAR=%APP_DIR%\RentHouse.jar


"%JAVA_EXE%" "-Dspring.profiles.active=dev -jar \"%APP_JAR%\""

