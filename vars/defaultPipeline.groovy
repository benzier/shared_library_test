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
        /*def conf = [ stages: [
            [
                stage: StageManager.checkoutStage,
                class: 'com.p72.miteam.MiteamCheckoutStage', 
                repo: "https://github.com/benzier/shared_library_external.git",
            ]
        ], project_type: "maven"]*/
        def conf = [ stages: [], project_type: "maven"]

        StageManager manager = new StageManager(this, conf)
        manager.startPipeline()
    }
}