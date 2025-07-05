pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/Kolbasalive/EventService.git'
            }
        }
        stage('Build') {
            steps {
                bat 'gradlew.bat build'
            }
        }
        stage('Test') {
            steps {
                bat 'gradlew.bat test'
            }
        }
    }
}
