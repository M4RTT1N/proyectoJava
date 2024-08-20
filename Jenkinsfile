pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8.4'
        jdk 'JDK11'
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
                    jacoco(
                        execPattern: 'target/jacoco.exec'
                    )
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
            emailext body: 'Se ha completado una ejecución del pipeline. Por favor, revisa los resultados.',
                     subject: "Estado del Pipeline: ${currentBuild.fullDisplayName}",
                     to: 'aomarttin@gmail.com'
        }
    }
}