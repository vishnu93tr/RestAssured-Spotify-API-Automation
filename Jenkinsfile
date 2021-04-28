def skipRemainingStages = false
pipeline {
    agent any
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
                    skipRemainingStages=true
                }
            }
        stage('Build Project and Run tests') {
                when {
                        expression {
                            !skipRemainingStages
                        }
                    }
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                sh "mvn clean test -DBASE_URI='https://api.spotify.com'"
                }
            }
        }
        stage('Generate Allure Reports') {
                    when {
                              expression {
                                    !skipRemainingStages
                                }
                            }
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