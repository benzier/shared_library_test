#!/usr/bin/env groovy

import com.p72.devops.*


def call(String name = 'human') {
    //GlobalVars obj = new GlobalVars(this)
    RestClient client = new RestClient();
    def var = client.getApiCall(new HashMap(), "https://jsonplaceholder.typicode.com","/users")
    
    //println obj.send_request()
    //println obj.send_request2()
    var.each {
        echo it.toString()
    }
    echo "Hello, ${name}."
}

