package com.p72.devops.stage.maven

import com.p72.devops.stage.factory.*
import com.p72.devops.util.*

class MavenStageFactory extends AbstractStageFactory {

    private def pipeline
    private JenkinsUtils jenkins

    MavenStageFactory(pipeline){
        this.pipeline=pipeline;
        this.jenkins = new JenkinsUtils(pipeline)
    }

    ICheckoutStage checkoutStageFactory() {
        def className = "com.p72.devops.stage.shared.MavenCheckoutStage"
        def stage = Eval.xy( className, jenkins,"new x(y)")
        return stage;
        /*def stage = new MavenCheckoutStage(new JenkinsUtils(pipeline))
        stage.injectPipeline(pipeline)
        return stage;*/
    }
    IBuildStage buildStageFactory() { return null; }
    ITestStage testStageFactory() { return null; }
    IPackageStage packageStageFactory() { return null; }
    IPublishStage publishStageFactory() { return null; }
    IDeployStage deployStageFactory() { return null; }

}