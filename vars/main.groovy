#!/usr/bin/env groovy

import com.p72.devops.*


def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    /*node {
        stage("LAAAALEEE"){
            //GlobalVars obj = new GlobalVars(this)
            RestClient client = new RestClient(pipeline: this);
            def var = client.getApiCall([:], "https://jsonplaceholder.typicode.com","/users")
            
            //println obj.send_request()
            //println obj.send_request2()
            var.each {
                echo it.toString()
            }
            echo "Hello, ${config.name}."
        }
    }*/

    node {
        //def server = Artifactory.newServer url: SERVER_URL, credentialsId: CREDENTIALS
        def rtMaven = Artifactory.newMavenBuild()
        def buildInfo

        stage ('Clone') {
            git url: 'https://github.com/JFrog/project-examples.git'
        }

        stage ('Artifactory configuration') {
            rtMaven.tool = "M2" // Tool name from Jenkins configuration
            //rtMaven.deployer releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local', server: server
            //rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: server
            buildInfo = Artifactory.newBuildInfo()
        }

        stage ('Exec Maven') {
            //rtMaven.run pom: 'maven-example/pom.xml', goals: 'clean install', buildInfo: buildInfo
        }

        StepManager manager = new StepManager(pipeline:this)
        echo "TESSST"
        def result = manager.runTest "nada"


        /*def p = [:]
        p.name = "Ruben"
        def result = Eval.xy(this,p,'x.customTestStage y')
        */echo "${result}"

        stage ('Publish build info') {
            //server.publishBuildInfo buildInfo
        }
    }


}

/*

node {
    def server = Artifactory.newServer url: SERVER_URL, credentialsId: CREDENTIALS
    def rtMaven = Artifactory.newMavenBuild()
    def buildInfo

    stage ('Clone') {
        git url: 'https://github.com/JFrog/project-examples.git'
    }

    stage ('Artifactory configuration') {
        rtMaven.tool = MAVEN_TOOL // Tool name from Jenkins configuration
        rtMaven.deployer releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local', server: server
        rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: server
        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Exec Maven') {
        rtMaven.run pom: 'maven-example/pom.xml', goals: 'clean install', buildInfo: buildInfo
    }

    stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }
}
*/