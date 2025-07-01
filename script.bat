@echo off
setlocal enabledelayedexpansion

REM ===== Configuration =====
set "BUILD_DIR=target"
set "WAR_NAME=crud.war"
set "WAR_PATH=%BUILD_DIR%\%WAR_NAME%"
set "TOMCAT_WEBAPP_DIR=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"
set "TOMCAT_LIB_DIR=C:\Program Files\Apache Software Foundation\Tomcat 10.1\lib"

echo =================================================
echo [DEBUG] Starting deployment script...
echo Working directory: %CD%
echo Build dir: %BUILD_DIR%
echo WAR name: %WAR_NAME%
echo WAR path: %WAR_PATH%
echo Tomcat webapps dir: %TOMCAT_WEBAPP_DIR%
echo Tomcat lib dir: %TOMCAT_LIB_DIR%
echo =================================================

echo.
echo =================================================
echo [1] Running Maven clean package...
echo =================================================
call mvn clean package
echo [DEBUG] Maven exited with code %ERRORLEVEL%

echo.
echo =================================================
echo [2] Listing contents of %BUILD_DIR%...
echo =================================================
dir "%BUILD_DIR%"

echo.
echo =================================================
echo [3] Checking WAR file presence...
echo Expected at: "%WAR_PATH%"
echo =================================================
if exist "%WAR_PATH%" (
    echo [OK] Found WAR: %WAR_PATH%
) else (
    echo [ERROR] WAR NOT found at %WAR_PATH%
)

echo.
echo =================================================
echo [4] Checking Tomcat webapps directory...
echo =================================================
if exist "%TOMCAT_WEBAPP_DIR%" (
    echo [OK] Tomcat webapps exists.
    echo [DEBUG] Its contents:
    dir "%TOMCAT_WEBAPP_DIR%"
) else (
    echo [ERROR] Tomcat webapps directory NOT found: %TOMCAT_WEBAPP_DIR%
)

echo.
echo =================================================
echo [5] Attempting to copy WAR to Tomcat...
echo      copy "%WAR_PATH%" "%TOMCAT_WEBAPP_DIR%\" /Y
echo =================================================
copy "%WAR_PATH%" "%TOMCAT_WEBAPP_DIR%\" /Y
echo [DEBUG] copy exited with code %ERRORLEVEL%
if %ERRORLEVEL% equ 0 (
    echo [OK] WAR successfully copied.
) else (
    echo [ERROR] Failed to copy WAR file.
)

echo.
echo =================================================
echo [6] Final contents of Tomcat webapps:
echo =================================================
dir "%TOMCAT_WEBAPP_DIR%"

echo.
echo =================================================
echo Deployment script completed.
echo Press any key to exit.
pause > nul
