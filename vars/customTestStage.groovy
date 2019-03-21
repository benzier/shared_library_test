#!/usr/bin/env groovy

import com.p72.devops.*

def call(Map p){

    echo "nothing to show ${p.name}"
    def error = 3/0
    return "result of it"
}