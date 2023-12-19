@Library('encoding-library') _
import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

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
                    def vqlFileContent = bat(returnStdout: true, script: "@cat C:\\Users\\mhassan\\Downloads\\gitDb2.vql")
                    def encodedVqlString = encode.encode(vqlFileContent)

                   // def jsonSlurper = new JsonSlurper()
                    def revisionCreationResponse = bat(returnStdout: true, script: "@curl -u admin:admin -d \"{\\\"name\\\":\\\"jenkins_revision\\\",\\\"content\\\":\\\"${encodedVqlString}\\\"}\" -H \"Content-Type:application/json\" -X POST http://kubernetes.docker.internal:10090/revisions/loadFromVQL")
                    println("respoooonse")
                    println("bakooor: " + revisionCreationResponse)
                    println("bakoor string: "+ vqlFileContent)
                    def map = parseJsonToMap(revisionCreationResponse.toString())
                    println("id++++++++++")
                    println([${map.id}])

                    def res = bat(returnStdout: true, script: "curl -u admin:admin -d \"{\\\"revisionIds\\\":\\\"[${map.id}]\\\",\\\"environmentId\\\":\\\"${1}\\\"}\" -H \"Content-Type: application/json\" -X POST http://kubernetes.docker.internal:10090/deployments")
                    println("deplooooy")
                    println(res)
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

