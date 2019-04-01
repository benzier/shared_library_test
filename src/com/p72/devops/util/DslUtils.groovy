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
}