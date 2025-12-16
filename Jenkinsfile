pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.6'
        jdk 'JDK-17'
    }
    
    environment {
        // ⚠️ CHANGE THESE IP ADDRESSES!
        SONAR_HOST = 'http://192.168.0.28:9000'  // Replace XX with SonarQube node IP
        SONAR_TOKEN = credentials(SONAR_TOKEN = credentials('sonarqube-token')      // Must match credential ID in Jenkins)
        NEXUS_URL = 'http://192.168.0.27:8081'   // Replace YY with Nexus node IP
        TOMCAT_URL = 'http://192.168.0.26:8080'  // Replace ZZ with Tomcat node IP
    }
    
    stages {
        stage('1. Git Checkout') {
            steps {
                echo '========== Cloning Repository =========='
                git branch: 'main', 
                    url: 'https://github.com/UNIQUE0024/healthcare-cicd.git'
                    // ⚠️ Change to YOUR GitHub username and repo name!
            }
        }
        
        stage('2. Maven Build') {
            steps {
                echo '========== Building with Maven =========='
                sh 'mvn clean compile'
            }
        }
        
        stage('3. Unit Tests') {
            steps {
                echo '========== Running Unit Tests =========='
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('4. SonarQube Analysis') {
            steps {
                echo '========== Running SonarQube Analysis =========='
                withSonarQubeEnv('SonarQube') {  // Must match name in Jenkins config
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=healthcare-app \
                          -Dsonar.host.url=${SONAR_HOST} \
                          -Dsonar.login=${SONAR_TOKEN}
                    """
                }
            }
        }
        
        stage('5. Quality Gate Check') {
            steps {
                echo '========== Waiting for Quality Gate =========='
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('6. Package WAR') {
            steps {
                echo '========== Creating WAR File =========='
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('7. Upload to Nexus') {
            steps {
                echo '========== Uploading to Nexus =========='
                script {
                    def pom = readMavenPom file: 'pom.xml'
                    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: "${NEXUS_URL}",
                        groupId: pom.groupId,
                        version: pom.version,
                        repository: 'maven-releases',
                        credentialsId: 'nexus-credentials',  // Must match credential ID in Jenkins
                        artifacts: [
                            [artifactId: pom.artifactId,
                             classifier: '',
                             file: "target/${pom.artifactId}-${pom.version}.war",
                             type: 'war']
                        ]
                    )
                }
            }
        }
        
        stage('8. Deploy to Tomcat') {
            steps {
                echo '========== Deploying to Tomcat =========='
                deploy adapters: [
                    tomcat9(
                        credentialsId: 'tomcat-deployer',  // Must match credential ID in Jenkins
                        path: '',
                        url: "${TOMCAT_URL}"
                    )
                ], 
                contextPath: '/healthcare',
                war: 'target/*.war'
            }
        }
    }
        failure {
            echo '========== DEPLOYMENT FAILED! =========='
            emailext(
                subject: "FAILED: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                    Build Failed!
                    
                    Job: ${env.JOB_NAME}
                    Build Number: ${env.BUILD_NUMBER}
                    
                    Check console output: ${env.BUILD_URL}
                """,
                to: 'your-email@example.com'  // ⚠️ Change to YOUR email!
            )
        }
        always {
            cleanWs()
        }
    }
}
