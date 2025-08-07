pipeline {
  agent any

  tools {
    jdk 'openJdk-17.0.0.1'
  }

  environment {
    DEPLOY_DIR   = 'D:\\RentHouse'
    APP_JAR      = "${DEPLOY_DIR}\\RentHouse.jar"
    SERVICE_NAME = 'RentHouseService'
  }

  stages {
    

    stage('Deploy') {
      steps {
        bat """
            @echo off
            sc query RentHouseService
            sc query RentHouseService | findstr /R /C:"STATE *: *1  STOPPED" >nul
            echo error leve = %ERRORLEVEL%
            if %ERRORLEVEL% EQU 1 (
                echo RentHouseService is stopped. Starting service...
                sc start RentHouseService
            ) else (
                echo RentHouseService is already running.
            )
            REM 1초 대기
            ping -n 2 127.0.0.1 >nul
          

          
        """
      }
    }
  }
}
