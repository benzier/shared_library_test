package com.p72.devops.maven

import com.p72.devops.*

//@groovy.transform.InheritConstructors
class MavenCheckoutStage extends ICheckoutStage {

    //private def pipeline
    MavenCheckoutStage(jenkins, pipeline){
        super(jenkins, pipeline)
    }

    /*def checkout(c){
        //pipeline.println pipeline.git.delegate.class.name
        pipeline.git c
    }*/

    

    def postAction(c){
        jenkins.println c
    }

}