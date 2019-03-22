package com.p72.devops.maven

class MavenCheckoutStage implements ICheckoutStage {

       

    def checkout(Map params){
        return git(params)
    }

}