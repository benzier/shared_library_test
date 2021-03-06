package com.p72.devops.stage

import com.p72.devops.stage.factory.*

class StageManager {

    def cachedRepos = [:]


    // CONSTANTS - STAGES
    public static final String checkoutStage = "checkoutStage"
    public static final String buildStage = "buildStage"
    public static final String testStage = "testStage"
    public static final String packageStage = "packageStage"
    public static final String publishStage = "publishStage"
    public static final String deployStage = "deployStage"

    public static final String defaulRepo = "internal"

    def pipeline
    def config
    def default_config = [
        [
            stage: StageManager.checkoutStage,
            default: true, 
            class: 'com.p72.devops.stage.shared.DefaultCheckoutStage', 
            repo: StageManager.defaulRepo,
            order: 10 
        ], 
        [
            stage: StageManager.buildStage,
            default: true, 
            class: '', 
            repo: "dsl",
            order: 20
        ],
        [
            stage: StageManager.testStage,
            default: true, 
            class: '', 
            repo: "dsl",
            order: 30
        ], 
        [
            stage: StageManager.packageStage,
            default: true, 
            class: '', 
            repo: "dsl",
            order: 40
        ],
        [
            stage: StageManager.publishStage,
            default: true, 
            class: '', 
            repo: "dsl",
            order: 40
        ],
        [
            stage: StageManager.deployStage,
            default: true, 
            class: '', 
            repo: "dsl",
            order: 50
        ]
    ]

    StageManager(pipeline, config){
        this.cachedRepos.put(StageManager.defaulRepo, null)
        this.pipeline = pipeline
        this.config = config
        
        // read external configuration
        config.stages.each { cfgStage ->
            def stage = default_config.find { defStage -> defStage.stage ==cfgStage.stage }
            if(stage){
                stage.default = false
                stage.repo = cfgStage.repo?:stage.repo
                stage.class = cfgStage.class?:stage.class
            }else{
                println "${cfgStage.stage} is not supported"
            }
        }
        this.config.stages = this.default_config

        config.stages.sort(true) { a, b -> 
            a.order <=> b.order
        }
    }


    def startPipeline(){

        AbstractStageFactory factory = AbstractStageFactory.getFactory(config.project_type, this.pipeline)
        def stage = null
        def lib = null;
        //config.stages.each { stageConf ->
            //check if the library was added to jenkins
            def stageConf = config.stages[0]
            pipeline.stage(stageConf.stage.minus("Stage")){
                if(stageConf.repo != StageManager.defaulRepo){
                    lib = getLibrary(stageConf.repo)
                }
                stage = factory."${stageConf.stage}Factory"(stageConf.class, lib);

                //stage?.preAction
                stage.checkout()
                stage?.postAction "test"
            }
        //}
    }

    def getLibrary(repo){
        def cRepo=cachedRepos.get(repo)
        if(cRepo){
            return cRepo;
        } else {
            this.pipeline.println "******* loading external library *******"
            def libraryName = repo.split("/")[-1].minus(".git")
            def library = pipeline.library(
                identifier: "${libraryName}@master",
                retriever: pipeline.modernSCM (
                    [
                        $class: 'GitSCMSource',
                        remote: repo
                    ]
                )
            )
            cachedRepos.put(repo, library)
            this.pipeline.println "******* external library loaded *******"
            return library
        }
    }


    /*def runStage(String stageName) {
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
    }*/
}