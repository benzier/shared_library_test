package com.p72.devops.maven

import com.p72.devops.*

@groovy.transform.InheritConstructors
class MavenCheckoutStage extends ICheckoutStage {

    //private def pipeline
    /*MavenCheckoutStage(pipeline){
        this.pipeline=pipeline
        pipeline.println(pipeline.class.name)
    }*/

    /*def checkout(c){
        //pipeline.println pipeline.git.delegate.class.name
        pipeline.git c
    }*/

    

    def postAction(c){
        utils.println c
    }

}