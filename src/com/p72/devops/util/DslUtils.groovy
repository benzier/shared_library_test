package com.p72.devops.util

class DslUtils {

    def context
    DslUtils(context){
        this.context=context;
    }

    def addParam(job, name){
        job.parameters {
            activeChoiceParam(name) {
                description('select one option')
                groovyScript {
                    script('["Sao Paulo", "Rio de Janeiro", "Parana:selected", "Acre"]')
                    fallbackScript('return ["ERROR"]')
                }
            }
        }
    }

    def createBuildJob(job){
        job.definition {
            context.cps {
                context.script("""
                    @Library('pipeline-library-demo')_

                    defaultPipeline {
                        repo = 'https://github.com/benzier/shared_library_test.git' // This parameter is provided by DSL                       
                        conf = [ stages: [            [
                                    stage: com.p72.devops.stage.StageManager.checkoutStage,
                                    class: 'com.p72.miteam.MiteamCheckoutStage', 
                                    repo: "https://github.com/benzier/shared_library_external.git",
                                ]
                        ], project_type: "maven"]
                    }
                """)
            }
        }

    }
}