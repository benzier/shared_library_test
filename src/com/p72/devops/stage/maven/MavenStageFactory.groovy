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
        def className = 'com.p72.devops.stage.shared.DefaultCheckoutStage'
        //def stage = Eval.xy( className, jenkins, "new x(y)" )
        //return stage;


/*        Class classToload = this.class.classLoader.loadClass( className, true, false);
        Class[] cArg = new Class[3]; //Our constructor has 3 arguments
        cArg[0] = JenkinsUtils.class; //First argument is of *object* type Long

        stage = classToLoad.getDeclaredConstructor(cArg).newInstance(jenkins);
        return stage;*/
        
        def stage = new com.p72.devops.stage.shared.DefaultCheckoutStage(new JenkinsUtils(pipeline))
        stage.injectPipeline(pipeline)
        return stage;
    }
    IBuildStage buildStageFactory() { return null; }
    ITestStage testStageFactory() { return null; }
    IPackageStage packageStageFactory() { return null; }
    IPublishStage publishStageFactory() { return null; }
    IDeployStage deployStageFactory() { return null; }

}