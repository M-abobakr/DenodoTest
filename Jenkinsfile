@Library('encoding-library') _
import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

pipeline {
    agent any

    // def sourceEnvId
    // def targetEnvId

    environment { 
        DenodoUserName = "admin"
        DenodoPassword = "admin"
        ExportedDbVqlPath = "C:\\Users\\mhassan\\Downloads"
        ExportScriptPath = "C:\\Denodo\\DenodoPlatform8.0\\bin\\export"
        SolutionManagerHost = "http://kubernetes.docker.internal:10090"
        DenodoAPIsCredential = credentials('DenodoAPIsCredential')
    }

    parameters {
        string(name: 'SourceEnvName', defaultValue: '', description: 'Name of source environment')
        string(name: 'TergetEnvName', defaultValue: '', description: 'Name of target environment')
        string(name: 'DatabaseToExport', defaultValue: '', description: 'Database name to export from Denodo dataPort server.')
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

        // stage('Export database') {
        //     steps {
        //         bat "${ExportScriptPath} --login ${DenodoUserName} --password ${DenodoPassword} --server //localhost/${params.DatabaseToExport} --file ${ExportedDbVqlPath}\\gitdb.vql"
        //     }
        // }

        stage ('List Environments') {
            steps {
                script{
                    def environments = httpRequest url: ${SolutionManagerHost}+"/environments", authenticate: 'DenodoAPIsCredential'
                
                    println(environments)    
                }                           
            }
        }
        // stage('Test shared library') {
        //     steps {
        //         script {
        //             def vqlFileContent = bat(encoding: 'UTF-8', returnStdout: true, script: "@cat C:\\Users\\mhassan\\Downloads\\gitdb.vql")
        //             def encodedVqlString = encode.encode(vqlFileContent)

        //             println(encodedVqlString[4..-1])
        //             def revisionCreationResponse = bat(returnStdout: true, script: "@curl -u admin:admin -d \"{\\\"name\\\":\\\"jenkins_revision\\\",\\\"content\\\":\\\"${encodedVqlString[4..-1]}\\\",\\\"replace\\\":\\\"REPLACE_EXISTING\\\"}\" -H \"Content-Type:application/json\" -X POST http://kubernetes.docker.internal:10090/revisions/loadFromVQL")
        //             def map = parseJsonToMap(revisionCreationResponse.toString())
        //             def revisionIds = [map.id]

        //             def res = bat(returnStdout: true, script: "curl -u admin:admin -d \"{\\\"revisionIds\\\":${revisionIds},\\\"environmentId\\\":${1}}\" -H \"Content-Type: application/json\" -X POST http://kubernetes.docker.internal:10090/deployments")
        //         }
        //     }
        // }
    }

    // post {
    //     always { 
    //         echo 'I will always say Hello again!'
    //     }
    //     success {
    //         echo 'I will say Hello only if job is success'
    //     }
    //     failure {
    //         echo 'I will say Hello only if job is failure'
    //     }
    // }
}

