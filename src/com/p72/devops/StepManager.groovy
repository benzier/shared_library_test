package com.p72.devops

class StepManager{
    def pipeline = null
    def pipelineSteps = [
            [
                stage: 'checkout',
                default: true, 
                class: "checkout", 
                repo: "dsl",
                order: 10 
            ], 
            [
                stage: 'build',
                default: true, 
                class: "mi class", 
                repo: "dsl",
                order: 20
            ],
            [
                stage: 'test',
                default: true, 
                class: "customTestStage", 
                repo: "dsl",
                order: 30
            ], 
            [
                stage: 'publish',
                default: true, 
                class: "mi class", 
                repo: "dsl",
                order: 40
            ],
            [
                stage: 'deploy',
                default: true, 
                class: "mi class", 
                repo: "dsl",
                order: 50
            ]
        ]
    
    def runStage(String stageName) {
        return pipeline.customTestStage(name:"manager")
    }

    def runExternalStage(Map parameters, String libraryName, String stageName, String repo, String branch="master" ){
        if(branch==null) branch="master"
        pipeline.echo "branch -> ${branch?:"master"}"
        pipeline.library(
            identifier: "${libraryName}@${branch}",
            retriever: pipeline.modernSCM (
                [
                    $class: 'GitSCMSource',
                    remote: repo
                ]
            )
        )
        //pipeline.otherStep(parameters)
        return Eval.xy(pipeline,parameters,"x.otherStep y")
    }

}

/*
stage('Load 3scale Library'){
  steps {
    script {
      try {
        library identifier: "3scale-library_branch@${env.BRANCH_NAME}",
            retriever: modernSCM(
                [
                    $class: 'GitSCMSource',
                    remote: 'git@<redacted>:3scale/cp-shared-library.git',
                    credentialsId: '<redacted>'
                ]
            )
      }catch(Exception e) {
        echo "tried to load library version from ${env.BRANCH_NAME}, but branch does not appear to exist in library repo. Continuing with default version."
        library identifier: '3scale-library_default@master',
            retriever: modernSCM(
                [
                    $class: 'GitSCMSource',
                    remote: 'git@<redacted>:3scale/cp-shared-library.git',
                    credentialsId: '<redacted>'
                ]
            )
      }
    }
  }
}*/