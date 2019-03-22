abstract class AbstractStageFactory {
    private pipeline;
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
                return new MavenFactory(pipeline);
                break;
            case "nodejs":
                return new NodejsFactory(pipeline);
                break;
            case "conda":
                return new CondaFactory(pipeline);
                break;
            case "dotNet"
                return new DotnetFactory(pipeline);
                break;
            case "docker"
            default:
                return new DockerFactory(pipeline);
        }
    }

    private defaultCheckout(String giturl){
        pipeline.git(url: "${giturl}")
    }
}