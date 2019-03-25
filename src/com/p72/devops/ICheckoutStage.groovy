package com.p72.devops

import com.p72.devops.util.*

@groovy.transform.Canonical
abstract class ICheckoutStage {

    JenkinsUtils jenkins
    private def pipeline
    
    ICheckoutStage(JenkinsUtils jenkins, pipeline){
        this.jenkins = jenkins;
        this.pipeline = pipeline
    }

    public def checkout(c){
        this.pipeline.git c
    }

    abstract def postAction(params);

}