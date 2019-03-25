package com.p72.devops.maven

import com.p72.devops.*

class MavenCheckoutStage extends ICheckoutStage {

    private def pipeline
    MavenCheckoutStage(pipeline){
        this.pipeline=pipeline
    }

    def checkout(c){
        pipeline.git c
    }

    def shell(c){
        println pipeline
        pipeline.bat(c)
    }

}