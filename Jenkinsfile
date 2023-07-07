pipeline {
    agent any
    tools {
            maven 'Maven 3.8.5'
            jdk 'Java 11.0.18'
        }
    stages {
        stage('Build') {
            steps {
                echo 'maven-3.8.5'
                sh '''
                    mvn --version
                    mvn clean install -DskipTests
                '''
            }
        }
        stage('Test') {
            steps {
                echo '// TO-DO check connection Database'
                echo '// TO-DO run unit test for Kafka'
            }
        }
        stage('Deploy') {
            steps {
                echo '// TO-DO build docker image'
            }
        }
    }
}