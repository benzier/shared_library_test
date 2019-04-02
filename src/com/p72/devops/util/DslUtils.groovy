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

                    Jenkins.instance.getAllItems(Job).each{

                    def jobBuilds=it.getBuilds()

                        //for each of such jobs we can get all the builds (or you can limit the number at your convenience)
                        jobBuilds.each { build ->
                        def runningSince = groovy.time.TimeCategory.minus( new Date(), build.getTime() )
                        def currentStatus = build.buildStatusSummary.message
                        def cause = build.getCauses()[0] //we keep the first cause
                        //This is a simple case where we want to get information on the cause if the build was 
                        //triggered by an user
                        def user = cause instanceof Cause.UserIdCause? cause.getUserId():""
                        //This is an easy way to show the information on screen but can be changed at convenience
                        println "Build: ${build} | Since: ${runningSince} | Status: ${currentStatus} | Cause: ${cause} | User: ${user}" 
                        
                        // You can get all the information available for build parameters.
                        def parameters = build.getAction(ParametersAction)?.parameters
                        parameters.each {
                            println "Type: ${it.class} Name: ${it.name}, Value: ${it.dump()}" 
                        
                            }
                        }
                    }



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