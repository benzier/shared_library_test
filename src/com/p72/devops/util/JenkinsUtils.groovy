package com.p72.devops.util

class JenkinsUtils {

    private pipeline;
    JenkinsUtils(pipeline){
        this.pipeline = pipeline;
    }

    def println(param){
        pipeline.println param
    }

    def echo(param){
        pipeline.echo param
    }

    def sh(param) {
        pipeline.sh param
    }

    def dir(param) { 
        pipeline.dir param
    }

}