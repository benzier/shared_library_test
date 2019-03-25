package com.p72.devops.stage.shared

import com.p72.devops.stage.factory.*
import com.p72.devops.util.*

class DefaultCheckoutStage extends ICheckoutStage {

    DefaultCheckoutStage(JenkinsUtils jenkins){
        super(jenkins);
    }

    def postAction(c){
        jenkins.echo c
    }

}