pipeline {
    agent {
        docker {
            image 'maven:3.9.3-eclipse-temurin-11'
        }
    }
    environment {
        DOCKER_REGISTRY_USERNAME = credentials('DOCKER_REGISTRY_USERNAME')
        DOCKER_REGISTRY_PASSWORD = credentials('DOCKER_REGISTRY_PASSWORD')
    }

    stages {
        stage('Build Package and Image') {
            steps {
                sh '''
                    mvn clean package -DskipTests
                    echo '----->>>>> Build image <<<<<<-------'
                    docker build --build-arg profile=prod -t tamanh97/order:0.1 .
                '''
                //java -Dspring.profiles.active=dev -jar Order-0.0.1-SNAPSHOT.jar
            }
        }
        stage('Run Test') {
            steps {
                sh '''
                    echo '// Test run image order_48'
                    docker run --name order_48 -it tamanh97/order:0.1
                '''
                //java -Dspring.profiles.active=dev -jar Order-0.0.1-SNAPSHOT.jar
            }
        }
//         stage('Test') {
//             steps {
//                 echo '// TO-DO check connection Database'
// //                 sh 'mvn test'
//             }
//             post {
//                 always {
//                     junit 'target/surefire-reports/*.xml'
//                 }
//             }
//         }
//         stage('Deploy') {
//             steps {
//                 echo '// TO-DO build docker image'
//             }
//         }
    }
}

// pipeline {
//   agent any
//   environment {
//     DOCKER_REGISTRY_USERNAME = credentials('DOCKER_REGISTRY_USERNAME')
//     DOCKER_REGISTRY_PASSWORD = credentials('DOCKER_REGISTRY_PASSWORD')
//   }
//
//   stages {
//     stage('Build') {
//       steps {
//         sh '''
//           echo "Starting to build docker image"
//           docker build -t <dockerhub_account>/obo:v1.${BUILD_NUMBER} -f Dockerfile .
//         '''
//         sh '''
//           echo "Starting to push docker image"
//           echo ${DOCKER_REGISTRY_PASSWORD} | docker login -u ${DOCKER_REGISTRY_USERNAME} --password-stdin
//           docker push "<dockerhub_account>/obo:v1.${BUILD_NUMBER}"
//         '''
//       }
//     }
//   }
// }