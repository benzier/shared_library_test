package com.p72.devops.maven

import com.p72.devops.*

class MavenStageFactory extends AbstractStageFactory {

    private def pipeline
    protected MavenStageFactory(pipeline){
        this.pipeline=pipeline;
        super(pipeline);
    }

    ICheckoutStage checkoutStageFactory() { return new MavenCheckoutStage(pipeline); }
    IBuildStage buildStageFactory() { return null; }
    ITestStage testStageFactory() { return null; }
    IPackageStage packageStageFactory() { return null; }
    IPublishStage publishStageFactory() { return null; }
    IDeployStage deployStageFactory() { return null; }

}