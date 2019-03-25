package com.p72.devops.maven

import com.p72.devops.*

class MavenCheckoutStage extends ICheckoutStage {

    private def pipeline
    MavenCheckoutStage(pipeline){
        this.pipeline=pipeline
        pipeline.println(pipeline.class.name)
    }

    def checkout(c){
        pipeline.git c
    }

    def sh(c){
        pipeline.bat c
    }

}