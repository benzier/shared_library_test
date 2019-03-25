package com.p72.devops.stage.factory

import com.p72.devops.util.*

abstract class ICheckoutStage {

    def pipeline
    JenkinsUtils jenkins

    public ICheckoutStage(JenkinsUtils jenkins){
        this.jenkins = jenkins
    }

    public void injectPipeline(pipeline){
        this.pipeline=pipeline
    }

    public final void checkout(c) {
        this.pipeline.git c
    }

    abstract def postAction(params);

}