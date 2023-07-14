pipeline {
    agent {
        docker {
            image 'maven:3.9.3-eclipse-temurin-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
//     tools {
//         maven '3.8.5'
//         jdk '8.221'
//     }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                echo '// TO-DO check connection Database'
                echo '// TO-DO run unit test for Kafka'
//                 sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo '// TO-DO build docker image'
            }
        }
    }
}