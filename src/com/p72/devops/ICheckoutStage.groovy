package com.p72.devops

import com.p72.devops.util.*
import groovy.lang.*

abstract class ICheckoutStage {

    JenkinsUtils utils
    def pipeline
    
    ICheckoutStage(JenkinsUtils utils, pipeline){
        this.utils = utils;
        this.pipeline=pipeline
    }

    def checkout(c){
        //pipeline.println pipeline.git.delegate.class.name
        pipeline.git c
    }

    /*def p(param){
        pipeline.println param
    }*/

    abstract def postAction(params);


}