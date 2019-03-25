package com.p72.devops.maven

import com.p72.devops.*
import com.p72.devops.util.*

class MavenStageFactory extends AbstractStageFactory {

    private def pipeline
    MavenStageFactory(pipeline){
        this.pipeline=pipeline;
    }

    ICheckoutStage checkoutStageFactory() { 
        def stage = new MavenCheckoutStage(new JenkinsUtils(pipeline))
        stage.setPipeline(pipeline)
        return stage;
    }
    IBuildStage buildStageFactory() { return null; }
    ITestStage testStageFactory() { return null; }
    IPackageStage packageStageFactory() { return null; }
    IPublishStage publishStageFactory() { return null; }
    IDeployStage deployStageFactory() { return null; }

}