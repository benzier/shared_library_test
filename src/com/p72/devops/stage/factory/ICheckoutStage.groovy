package com.p72.devops.stage.factory

import com.p72.devops.util.*

abstract class ICheckoutStage {

    def pipeline
    def jenkins

    public ICheckoutStage(JenkinsUtils jenkins){
        this.jenkins = jenkins
    }

    public void injectPipeline(pipeline){
        this.pipeline=pipeline
    }

    public final void checkout() {
        this.pipeline.println "******** Performing checkout ********"
        this.pipeline.git url:pipeline.params.repo
        this.pipeline.println "********* Checkout completed *********"
    }

    abstract def postAction(params);

}