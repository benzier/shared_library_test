package com.p72.devops.stage.factory

import com.p72.devops.stage.maven.*
//import com.p72.devops.stage.nodejs.*
//import com.p72.devops.stage.docker.*
//import com.p72.devops.stage.conda.*

abstract class AbstractStageFactory {

    abstract ICheckoutStage checkoutStageFactory(String className)
    abstract IBuildStage buildStageFactory(String className)
    abstract ITestStage testStageFactory(String className)
    abstract IPackageStage packageStageFactory(String className)
    abstract IPublishStage publishStageFactory(String className)
    abstract IDeployStage deployStageFactory(String className)

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

    /*
    private defaultCheckout(String giturl){
        pipeline.git(url: "${giturl}")
    }*/
}