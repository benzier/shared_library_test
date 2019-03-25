package com.p72.devops

import com.p72.devops.util.*

abstract class ICheckoutStage {

    private def p
    JenkinsUtils jenkins

    ICheckoutStage(JenkinsUtils jenkins){
        this.jenkins = jenkins;
        
    }

    void setPipeline(pipeline){
        p=pipeline
    }

    def checkout(c){
        this.p.git c
    }

    abstract def postAction(params);

}