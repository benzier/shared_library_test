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
            cps {
                script("""
                    @Library('pipeline-library-demo')_

                    defaultPipeline {
                        branchName = 'master'
                        gitRepoUrl = 'https://github.com/benzier/shared_library_test.git'
                        conf = [ stages: [            [
                                    stage: com.p72.devops.stage.StageManager.checkoutStage,
                                    class: 'com.p72.miteam.MiteamCheckoutStage', 
                                    repo: "https://github.com/benzier/shared_library_external.git",
                                ]
                        ], project_type: "maven"]
                    }
                """)
                sandbox(true)
            }
        }

    }
}