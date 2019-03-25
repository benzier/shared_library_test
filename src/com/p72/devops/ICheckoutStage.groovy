package com.p72.devops

import com.p72.devops.util.*

abstract class ICheckoutStage {

    private def p
    JenkinsUtils jenkins

    ICheckoutStage(JenkinsUtils jenkins){
        this.jenkins = jenkins;
        
    }

    def getP(){
        return p;
    }

    public void setP(pipeline){
        this.p=pipeline
    }

    public void checkout(c) {
        this.p.git c
    }

    abstract def postAction(params);

}