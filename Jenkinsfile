pipeline {
    agent any
    tools {
            maven '3.8.5'
            jdk '8.221'
        }
    stages {
        stage('Build') {
            steps {
                echo 'step building maven....'
                sh '''
                    mvn --version
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