package com.p72.devops.stage.shared

import com.p72.devops.stage.factory.*

@groovy.transform.InheritConstructors
class DefaultCheckoutStage extends ICheckoutStage {

    def postAction(c){
        jenkins.echo c
    }

}