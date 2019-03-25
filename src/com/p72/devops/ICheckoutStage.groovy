package com.p72.devops

import com.p72.devops.util.*

abstract class ICheckoutStage {

    def p
    JenkinsUtils jenkins

    ICheckoutStage(JenkinsUtils jenkins){
        this.jenkins = jenkins;
    }

    public void setPipeline(pipeline){
        p=pipeline
    }

    public final void checkout(c) {
        p.git c
    }

    abstract def postAction(params);

}