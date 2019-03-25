package com.p72.devops

import com.p72.devops.util.*

abstract class ICheckoutStage {

    def p;
    JenkinsUtils jenkins

    ICheckoutStage(JenkinsUtils jenkins){
        this.jenkins = jenkins;
        
    }

    public def getP(){
        return this.p;
    }

    public void setP(pipeline){
        this.p=pipeline
    }

    public void checkout(c) {
        this.p.git c
    }

    abstract def postAction(params);

}