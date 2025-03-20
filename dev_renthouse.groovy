pipeline {
    agent any

    tools {
        // DEV_RCRM_ERCS
        maven 'Maven-3.9.5'
        jdk 'jdk-17' //jdk1.8.0_92, jdk-1.7.0_75, jdk-17
    }
    
    environment {
        REPO_URL = 'https://github.com/youp-han/RentHouse.git'

    }

    stages {
        stage('Checkout') { //
        
            steps {
                // Clone repository ABC into a subfolder named "ABC"
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "refs/heads/main"]], // 브랜치 이름을 설정하세요 (예: main)
                    doGenerateSubmoduleConfigurations: false,

                    userRemoteConfigs: [[
                        url: "${REPO_URL}",
                        credentialsId: 'personal_git'
                    ]]
                ])

                
            }
        }
        
    
        stage('Build') {
            steps {
                bat "mvn -B -f ${env.WORKSPACE}/pom.xml clean package -DskipTests"

            }
        }

        stage('Check Running Process') {
            steps {
                script {
                    // 포트를 기반으로 실행 중인 프로세스 확인
                    def jarRunning = bat(
                        script: 'netstat -ano | findstr :8099',
                        returnStatus: true
                    ) == 0
                    
                    if (jarRunning) {
                        echo 'JAR file is running. Stopping the process...'
                        bat 'for /f "tokens=5" %a in (\'netstat -ano ^| findstr :8099\') do taskkill /PID %a /F'
                    } else {
                        echo 'No process is running on port 8099.'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the JAR file...'
                bat "copy ${env.WORKSPACE}\\target\\renthouse-0.0.1-SNAPSHOT.jar E:\\Programs\\rent_house\\renthouse.jar"
            }
        }
        stage('Run') {
            steps {
                echo 'Starting the application...'
                bat "start /B java -jar E:\\Programs\\rent_house\\renthouse.jar --server.port=8099"
            }
        }
        
        


    }
}

