package com.p72.devops

abstract class ICheckoutStage {

    @delegate JenkinsUtils utils
    
    ICheckoutStage(JenkinsUtils utils, pipeline){
        this.utils = utils;
    }

    def checkout(c){
        //pipeline.println pipeline.git.delegate.class.name
        pipeline.git c
    }

    /*def p(param){
        pipeline.println param
    }*/

    abstract def postAction(params);


}