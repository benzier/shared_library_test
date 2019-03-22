package com.p72.devops.maven

import com.p72.devops.*

class MavenCheckoutStage extends ICheckoutStage {

    MavenCheckoutStage(git,sh){
        super (git, sh)
    }

    def checkout(Map params){
        pipeline.git url: params.url
    }

}