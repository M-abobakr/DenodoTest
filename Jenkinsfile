@Library('encoding-library') _

pipeline {
    agent any

    environment { 
        NAME = 'Mohamed'
    }

    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
    }

    stages {
        stage('Hello') {
            environment {
                TEST_CREDENTIAL = credentials('testCredential')
            }

            steps {
                echo "Hello ${params.PERSON}"
                echo "Welcome ${NAME}"
                echo "cred username: ${TEST_CREDENTIAL_USR}"
                echo "cred password: ${TEST_CREDENTIAL_PSW}"
                echo "cred: ${TEST_CREDENTIAL}"
            }
        }
        
        stage('Test Solution manager APIs') {
            steps {
                script {
                    def output = bat(returnStdout: true, script: "curl http://kubernetes.docker.internal:10090/environments -u admin:admin")
                    println(output.readLines().last().trim())
                }
            }
        }

        // stage('Test access system variable') {
        //     steps {
        //         bat "C:\\Denodo\\DenodoPlatform8.0\\bin\\export --login admin --password admin --server //localhost/api_tutorial --file C:\\Users\\mhassan\\Downloads\\test2.vql"
        //     }
        // }

        stage('Test shared library') {
            steps {
                script {
                    def vqlFileContent = bat(returnStdout: true, script: "cat C:\\Users\\mhassan\\Downloads\\gitDb2.vql").readLines().last().trim()
                    def encodedVqlString = encode.encode(vqlFileContent)

                    println(encodedVqlString)
                }
            }
        }
    }

    post {
        always { 
            echo 'I will always say Hello again!'
        }
        success {
            echo 'I will say Hello only if job is success'
        }
        failure {
            echo 'I will say Hello only if job is failure'
        }
    }
}

