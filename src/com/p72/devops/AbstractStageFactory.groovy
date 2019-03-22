package com.p72.devops

import com.p72.devops.maven.*
import com.p72.devops.nodejs.*
import com.p72.devops.docker.*
import com.p72.devops.conda.*

abstract class AbstractStageFactory {
    protected pipeline;
    private Map config;

    AbstractStageFactory(pipeline) {
        this.pipeline=pipeline
    }

    abstract ICheckoutStage checkoutStageFactory()
    abstract IBuildStage buildStageFactory()
    abstract ITestStage testStageFactory()
    abstract IPackageStage packageStageFactory()
    abstract IPublishStage publishStageFactory()
    abstract IDeployStage deployStageFactory()

    static AbstractStageFactory getFactory(String project_type, pipeline){
        switch(project_type){
            case "maven":
                return new MavenStageFactory(pipeline);
                break;
            /*case "nodejs":
                return new NodejsFactory(pipeline);
                break;
            case "conda":
                return new CondaFactory(pipeline);
                break;
            case "dotNet":
                return new DotnetFactory(pipeline);
                break;
            case "docker":*/
            default:
                return null//new DockerFactory(pipeline);
        }
    }

    protected 

    private defaultCheckout(String giturl){
        pipeline.git(url: "${giturl}")
    }
}