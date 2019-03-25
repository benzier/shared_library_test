package com.p72.devops.stage

class StageManager{

    def pipeline
    def config
    def default_config = [
        [
            stage: 'checkoutStage',
            default: true, 
            class: 'com.p72.devops.stage.shared.DefaultCheckoutStage', 
            repo: "internal",
            order: 10 
        ], 
        [
            stage: 'build',
            default: true, 
            class: '', 
            repo: "dsl",
            order: 20
        ],
        [
            stage: 'test',
            default: true, 
            class: '', 
            repo: "dsl",
            order: 30
        ], 
        [
            stage: 'publish',
            default: true, 
            class: '', 
            repo: "dsl",
            order: 40
        ],
        [
            stage: 'deploy',
            default: true, 
            class: '', 
            repo: "dsl",
            order: 50
        ]
    ]

    StageManager(pipeline, config){
        this.pipeline = pipeline
        this.config = config
        
        // read external configuration
        config.stages.each { cfgStage ->
            stage = default_config.find { defStage -> defStage.stage ==cfgStage.stage }
            if(stage){
                stage.default = false
                stage.repo = cfgStage.repo?:stage.repo
                stage.class = cfgStage.class?:stage.class
            }else{
                println "${cfgStage.stage} is not supported"
            }
        }
        config.stages.sort(true) { a, b -> 
            a.order <=> b.order
        }
    }


    def startPipeline(){

        AbstractStageFactory factory = AbstractStageFactory.getFactory(config.project_type, this.pipeline)
        
        config.stages.each { stage ->
            pipeline.println(stage.stage)
            pipeline.println(stage.order)
        }
        /*def coStage = factory."${}Factory"('com.p72.devops.stage.shared.DefaultCheckoutStage');
        def params = [url: "https://github.com/benzier/shared_library_external.git"]
        coStage.checkout params //url: "https://github.com/benzier/shared_library_external.git"
        def result = coStage.postAction ("echo test")*/
    }


    def runStage(String stageName) {
        return pipeline.customTestStage(name:"manager")
    }

    def runExternalStage(Map parameters, String libraryName, String stageName, String repo, String branch="master" ){
        pipeline.library(
            identifier: "${libraryName}@${branch?:"master"}",
            retriever: pipeline.modernSCM (
                [
                    $class: 'GitSCMSource',
                    remote: repo
                ]
            )
        )
        return Eval.xy(pipeline,parameters,"x.${stageName} y")
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