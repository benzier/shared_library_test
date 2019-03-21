#!/usr/bin/env groovy

import com.p72.devops.*


def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    node {
        stage("LAAAALEEE"){
            //GlobalVars obj = new GlobalVars(this)
            RestClient client = new RestClient(pipeline: this);
            def var = client.getApiCall([:], "https://jsonplaceholder.typicode.com","/users")
            
            //println obj.send_request()
            //println obj.send_request2()
            var.each {
                echo it.toString()
            }
            echo "Hello, ${name}."
        }
    }
}

