pipeline {
    agent any
    parameters{
      choice(
      name: 'base_uri',
      choices: ['https://api.spotify.com'],
      description: 'Base URI According to env'
      )
    }
    tools {
            maven 'Maven 3.8.1'
            jdk 'jdk8'
        }
    stages {
    stage ('Initialize Maven and JDK 8') {
                steps {
                    sh '''
                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                    '''
                }
            }
        stage('Build Project and Run tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                sh "mvn clean test -DBASE_URI=${params.base_uri}"
                }
            }
        }
        stage('Initialize Docker'){
                def dockerHome=tool 'docker'
                def mavenHome=tool 'Maven 3.8.1'
                env.path="${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
              }
        stage('Build Docker image'){
        steps{
            sh "docker build -t='vishnu26121993/restassured:${BUILD_NUMBER}' ."
            sh "docker build -t='vishnu26121993/restassured' ."
         }
        }
        stage('Push image to docker hub'){
        steps{
           withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', passwordVariable: 'pass', usernameVariable: 'user')]){
            sh "docker login --username=${user} --password=${pass}"
            sh "docker push vishnu26121993/restassured:${BUILD_NUMBER}"
            sh "docker push vishnu26121993/restassured:latest"
           }
        }
        }
        stage('Generate Allure Reports') {
            steps {
            script {
                    allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']]
                    ])
            }
            }
            }
            }
}