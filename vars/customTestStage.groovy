#!/usr/bin/env groovy

import com.p72.devops.*

def call(Map params){
    echo "nothing to show ${params.name}"
    return "result of it"
}