#!/usr/bin/env groovy

import com.p72.devops.*


def call(String name = 'human') {
    //GlobalVars obj = new GlobalVars(this)
    RestClient client = new RestClient();
    def var = client.getApiCall(null, "https://jsonplaceholder.typicode.com","/todos")
    //println obj.send_request()
    //println obj.send_request2()
    echo var
    echo "Hello, ${name}."
}

