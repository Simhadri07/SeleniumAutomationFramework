pipeline {
    agent any

    parameters {
        string(
            name: 'branch',
            defaultValue: 'develop',
            description: 'Git branch to execute'
        )

        booleanParam(
            name: 'runConfig',
            defaultValue: false,
            description: 'Load latest pipeline configuration changes'
        )

        choice(
            name: 'browser',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Browser for Selenium execution'
        )

        choice(
            name: 'environment',
            choices: ['qa', 'staging', 'prod'],
            description: 'Test environment'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${params.branch}"]],
                    userRemoteConfigs: [[
                        credentialsId: 'GitHub-Credentials',
                        url: 'https://github.com/Simhadri07/SeleniumAutomationFramework.git'
                    ]]
                ])
            }
        }

        stage('Environment Check') {
            steps {
                bat '''
                    java -version
                    mvn -version
                    git --version
                '''
            }
        }

        stage('Load Config') {
            when {
                expression {
                    params.runConfig
                }
            }
            steps {
                bat '''
                    git log -1 --oneline
                    dir /s /b *.xml *.properties *.json
                '''
            }
        }

        stage('Run Selenium Tests') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'selenium-test-credentials',
                        usernameVariable: 'TEST_USERNAME',
                        passwordVariable: 'TEST_PASSWORD'
                    )
                ]) {
                    bat """
                        mvn -B clean test ^
                        -Dbrowser=${params.browser} ^
                        -Denvironment=${params.environment}
                    """
                }
            }
        }

        stage('Test Reports') {
            steps {
                echo 'Test reports are published in the pipeline post actions.'
            }
        }
    }

    post {
        always {
            echo 'Selenium pipeline completed.'
            junit(
                allowEmptyResults: true,
                testResults: '**/target/surefire-reports/*.xml'
            )
            archiveArtifacts(
                artifacts: '**/target/surefire-reports/**/*,**/target/extent-reports/**/*,**/target/logs/**/*,**/target/screenshots/**/*',
                allowEmptyArchive: true
            )
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/extent-reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Test Report'
            ])
        }
        success {
            echo 'Selenium automation PASSED.'
        }
        failure {
            echo 'Selenium automation FAILED.'
        }
    }
}
