package com.p72.devops

abstract class ICheckoutStage {

    protected pipeline;
    
    ICheckoutStage(pipeline){
        this.pipeline=pipeline
        pipeline.println(pipeline.class.name)
    }

    def checkout(c){
        //pipeline.println pipeline.git.delegate.class.name
        pipeline.git c
    }

    def p(param){
        pipeline.println param
    }

    abstract def postAction(params);


}