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
        // stage('Hello') {
        //     environment {
        //         TEST_CREDENTIAL = credentials('testCredential')
        //     }

        //     steps {
        //         echo "Hello ${params.PERSON}"
        //         echo "Welcome ${NAME}"
        //         echo "cred username: ${TEST_CREDENTIAL_USR}"
        //         echo "cred password: ${TEST_CREDENTIAL_PSW}"
        //         echo "cred: ${TEST_CREDENTIAL}"
        //     }
        // }
        
        // stage('Test Solution manager APIs') {
        //     steps {
        //         script {
        //             def output = bat(returnStdout: true, script: "curl http://kubernetes.docker.internal:10090/environments -u admin:admin")
        //             println(output.readLines().last().trim())
        //         }
        //     }
        // }

        // stage('Test access system variable') {
        //     steps {
        //         bat "C:\\Denodo\\DenodoPlatform8.0\\bin\\export --login admin --password admin --server //localhost/gitdb --file C:\\Users\\mhassan\\Downloads\\gitdb.vql"
        //     }
        // }

        stage('Test shared library') {
            steps {
                script {
                    def vqlFileContent = bat(encoding: 'UTF-8', returnStdout: true, script: "@Get-Content C:\\Users\\mhassan\\Downloads\\gitdb.vql")
                    def encodedVqlString = encode.encode(vqlFileContent)

println("filllle : " + vqlFileContent)
println("balkkor: " + encodedVqlString)
                    // def revisionCreationResponse = bat(returnStdout: true, script: "@curl -u admin:admin -d \"{\\\"name\\\":\\\"jenkins_revision\\\",\\\"content\\\":\\\"${encodedVqlString}\\\",\\\"replace\\\":\\\"REPLACE_EXISTING\\\"}\" -H \"Content-Type:application/json\" -X POST http://kubernetes.docker.internal:10090/revisions/loadFromVQL")
                    // def map = parseJsonToMap(revisionCreationResponse.toString())
                    // def revisionIds = [map.id]

                    // def res = bat(returnStdout: true, script: "curl -u admin:admin -d \"{\\\"revisionIds\\\":${revisionIds},\\\"environmentId\\\":${1}}\" -H \"Content-Type: application/json\" -X POST http://kubernetes.docker.internal:10090/deployments")
                    // println(res)
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

