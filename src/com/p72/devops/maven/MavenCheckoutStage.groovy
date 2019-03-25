package com.p72.devops.maven

import com.p72.devops.*

@groovy.transform.InheritConstructors
class MavenCheckoutStage extends ICheckoutStage {


    def postAction(c){
        jenkins.echo c
    }

}