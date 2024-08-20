pipeline {
    agent any
    
    tools {
        maven 'Maven_3.8.4'
        jdk 'JDK11'
        git 'Default'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                sh 'mvn package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }
    
    post {
        always {
            emailext body: "Se ha completado una ejecución del pipeline. Estado: ${currentBuild.result}",
                     subject: "Estado del Pipeline: ${currentBuild.fullDisplayName}",
                     to: 'aomarttin@gmail.com'
        }
    }
}