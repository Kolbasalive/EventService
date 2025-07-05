pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/твой-профиль/твой-репозиторий.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
