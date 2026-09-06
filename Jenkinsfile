pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    parameters {

        string(
            name: 'branch',
            defaultValue: 'develop',
            description: 'Git branch to execute'
        )

        booleanParam(
            name: 'runConfig',
            defaultValue: false,
            description: 'Load and display the latest test configuration'
        )

        choice(
            name: 'browser',
            choices: [
                'chrome',
                'firefox',
                'edge'
            ],
            description: 'Browser for Selenium execution'
        )

        choice(
            name: 'environment',
            choices: [
                'qa',
                'staging',
                'prod'
            ],
            description: 'Test environment'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                echo "========================================"
                echo "CHECKOUT"
                echo "========================================"

                echo "Branch      : ${params.branch}"
                echo "Run Config  : ${params.runConfig}"
                echo "Browser     : ${params.browser}"
                echo "Environment : ${params.environment}"

                checkout([
                    $class: 'GitSCM',

                    branches: [[
                        name: "*/${params.branch}"
                    ]],

                    userRemoteConfigs: [[
                        credentialsId: 'GitHub-Credentials',
                        url: 'https://github.com/Simhadri07/SeleniumAutomationFramework.git'
                    ]]
                ])

                bat 'git log -1 --oneline'
            }
        }

        stage('Environment Check') {
            steps {
                echo "========================================"
                echo "ENVIRONMENT CHECK"
                echo "========================================"

                bat '''
                    echo ===== JAVA =====
                    java -version

                    echo.
                    echo ===== MAVEN =====
                    mvn -version

                    echo.
                    echo ===== GIT =====
                    git --version

                    echo.
                    echo ===== WORKSPACE =====
                    echo %WORKSPACE%
                '''
            }
        }

        stage('Load Config') {
            when {
                expression {
                    return params.runConfig
                }
            }

            steps {
                echo "========================================"
                echo "LOAD CONFIG"
                echo "========================================"

                bat '''
                    echo ===== COMMIT =====
                    git log -1 --oneline

                    echo.
                    echo ===== TEST CONFIGURATION =====
                    dir /s /b src\\test\\resources\\*.xml

                    echo.
                    echo ===== CONFIG FILES =====
                    dir /s /b src\\test\\resources\\*.properties
                '''
            }
        }

        stage('Run Selenium Tests') {
            steps {
                echo "========================================"
                echo "SELENIUM TESTNG"
                echo "========================================"

                echo "Browser     : ${params.browser}"
                echo "Environment : ${params.environment}"

                withCredentials([
                    usernamePassword(
                        credentialsId: 'selenium-test-credentials',
                        usernameVariable: 'TEST_USERNAME',
                        passwordVariable: 'TEST_PASSWORD'
                    )
                ]) {

                    bat '''
                        mvn -B --no-transfer-progress clean test ^
                        -Dbrowser=%browser% ^
                        -Denvironment=%environment%
                    '''
                }
            }
        }

        stage('Test Reports') {
            steps {
                echo "========================================"
                echo "TEST REPORTS"
                echo "========================================"

                echo "Surefire reports will be published in post actions."
            }
        }
    }

    post {

        always {
            echo "========================================"
            echo "PUBLISHING RESULTS"
            echo "========================================"

            junit(
                allowEmptyResults: true,
                testResults: '**/target/surefire-reports/*.xml'
            )

            archiveArtifacts(
                artifacts: '''
                    **/target/surefire-reports/**/*,
                    **/target/extent-reports/**/*,
                    **/target/logs/**/*,
                    **/target/screenshots/**/*
                ''',
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
            echo "Selenium automation PASSED."
        }

        failure {
            echo "Selenium automation FAILED."
        }
    }
}
