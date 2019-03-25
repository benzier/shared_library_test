package com.p72.devops

import com.p72.devops.util.*

abstract class ICheckoutStage {

    def p
    JenkinsUtils jenkins

    ICheckoutStage(JenkinsUtils jenkins, pipeline){
        this.jenkins = jenkins;
        this.p = pipeline;
    }

    public def checkout(c){
        this.p.git c
    }

    abstract def postAction(params);

}