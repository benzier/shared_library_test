package com.p72.devops.stage.factory

import com.p72.devops.util.*

abstract class IBuildStage {

    def pipeline
    def jenkins

    public IBuildStage(JenkinsUtils jenkins){
        this.jenkins = jenkins
    }

    public final void injectPipeline(pipeline){
        this.pipeline=pipeline
    }

    abstract def performAction() {}
    abstract def preAction()
    abstract def postAction(params)
}