package com.p72.devops

import com.p72.devops.util.*

abstract class ICheckoutStage {

    def pipeline
    JenkinsUtils jenkins

    ICheckoutStage(JenkinsUtils jenkins){
        this.jenkins = jenkins;
    }

    public void setPipeline(pipeline){
        this.pipeline=pipeline
    }

    public final void checkout(c) {
        this.pipeline.git c
    }

    abstract def postAction(params);

}