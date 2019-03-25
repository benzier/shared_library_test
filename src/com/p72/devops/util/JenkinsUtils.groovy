class JenkinsUtils {

    private pipeline;
    JenkinsUtils(pipeline){
        this.pipeline = pipeline;
    }

    def println(param){
        pipeline.println param
    }

    def echo(params){
        pipeline.echo param
    }

}