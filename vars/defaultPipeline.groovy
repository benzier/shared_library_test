#!/usr/bin/env groovy

import com.p72.devops.stage.factory.*
import com.p72.devops.stage.*


def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()


    // Base configuration
    String project_type="maven"

    node {
        // Store the configuration
        this.config = config
        
        StageManager manager = new StageManager(this, config.conf)
        manager.startPipeline()
    }
}