package com.p72.devops.maven

import com.p72.devops.*

class MavenStageFactory extends AbstractStageFactory {

    private def pipeline
    protected MavenStageFactory(pipeline){
        super(pipeline);
        this.pipeline=pipeline;
    }

    ICheckoutStage checkoutStageFactory() { return new MavenCheckoutStage(pipeline.&git); }
    IBuildStage buildStageFactory() { return null; }
    ITestStage testStageFactory() { return null; }
    IPackageStage packageStageFactory() { return null; }
    IPublishStage publishStageFactory() { return null; }
    IDeployStage deployStageFactory() { return null; }

}