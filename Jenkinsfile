pipeline {
    agent any
    stages {
        stage('Build Project and Run tests') {
            steps {
                sh "mvn clean test -DBASE_URI='https://api.spotify.com'"
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