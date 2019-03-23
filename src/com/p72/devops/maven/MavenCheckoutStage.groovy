package com.p72.devops.maven

import com.p72.devops.*

class MavenCheckoutStage extends ICheckoutStage {

    private def git
    private def sh
    MavenCheckoutStage(git, sh){
        this.git=git
        this.sh=sh
    }

    def checkout(c){
        git c
    }

    def shell(c){
        sh c
    }

}