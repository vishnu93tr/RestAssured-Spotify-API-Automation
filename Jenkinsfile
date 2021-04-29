pipeline {
    agent any
    parameters{
      string(name:'base_uri',defaultValue:'https://api.spotify.com',description:'Base URI for pipeline job')
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