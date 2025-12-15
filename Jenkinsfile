pipeline {
    agent any
    
    tools {
        maven 'Maven3'
        jdk 'Java21'
    }
    
    environment {
        TOMCAT_URL = 'http://192.168.0.16:8080'  // Replace with actual Node 4 IP
        APP_NAME = 'healthcare'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '📥 Checking out code from GitHub...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Building project with Maven...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo '🧪 Running unit tests...'
                sh 'mvn test'
            }
        }
        
        stage('Package') {
            steps {
                echo '📦 Creating WAR file...'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Deploy to Tomcat') {
            steps {
                echo '🚀 Deploying to Tomcat server...'
                script {
                    withCredentials([usernamePassword(credentialsId: 'tomcat-credentials', 
                                                     usernameVariable: 'TOMCAT_USER', 
                                                     passwordVariable: 'TOMCAT_PASS')]) {
                        sh """
                            curl -v -u ${TOMCAT_USER}:${TOMCAT_PASS} \
                            --upload-file target/${APP_NAME}.war \
                            '${TOMCAT_URL}/manager/text/deploy?path=/${APP_NAME}&update=true'
                        """
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline completed successfully!'
            echo "🌐 Access your application at: ${TOMCAT_URL}/${APP_NAME}"
        }
        failure {
            echo '❌ Pipeline failed! Check the logs above.'
        }
        always {
            echo '🧹 Cleaning up workspace...'
            cleanWs()
        }
    }
}
