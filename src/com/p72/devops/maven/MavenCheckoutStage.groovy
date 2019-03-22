package com.p72.devops.maven

import com.p72.devops.*

class MavenCheckoutStage implements ICheckoutStage {

    def checkout(Map params){
        return git(params)
    }

}