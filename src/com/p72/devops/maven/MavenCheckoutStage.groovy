package com.p72.devops.maven

import com.p72.devops.*

class MavenCheckoutStage extends ICheckoutStage {

    private def git
    MavenCheckoutStage(git){
        this.git=git
    }

    def checkout(c){
        git c
    }

}