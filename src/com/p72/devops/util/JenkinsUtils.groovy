package com.p72.devops.util

class JenkinsUtils {

    private pipeline;
    JenkinsUtils(pipeline){
        this.pipeline = pipeline;
    }

    def println(param){
        pipeline.println param
    }

    def echo(params){
        pipeline.echo param
    }

    def sh(params) {
        pipeline.sh params
    }

}