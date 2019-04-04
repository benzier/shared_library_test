package com.p72.devops.util

import org.jenkinsci.plugins.workflow.cps.*

class DelegateSample implements Serializable{
    
    @Delegate(interface=false)
    DSL pipeline;
    DelegateSample(DSL p){
        this.pipeline=p;
    }

}