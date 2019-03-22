package com.p72.devops

abstract class ICheckoutStage {

    protected def git
    protected def sh

    ICheckoutStage(git, sh){
        this.git = git
        this.sh = sh
    }
    
    

    abstract def checkout(Map params)

}