package com.p72.devops.stage.maven

import com.p72.devops.stage.factory.*
import com.p72.devops.util.*
import java.lang.reflect.*;

class MavenStageFactory extends AbstractStageFactory {

    private def pipeline
    private JenkinsUtils jenkins

    MavenStageFactory(pipeline){
        this.pipeline=pipeline;
        this.jenkins = new JenkinsUtils(pipeline)
    }

    ICheckoutStage checkoutStageFactory() {
        def stage = null
        def className = 'com.p72.devops.stage.shared.DefaultCheckoutStage'
        /*
        stage = Eval.xy( className, jenkins, "new x(y)" )
        */

        Class[] cArg = new Class[1]; 
        cArg[0] = JenkinsUtils.class; 

        Class classToload = this.getClass().classLoader.loadClass(className, true, false);
        printAllMethods(classToload)
        
        classToload.getDeclaredConstructors().each {
            this.pipeline.println(it.toString())
        }
        //this.pipeline.println()
        stage = classToLoad.getDeclaredConstructors[0].newInstance(jenkins);

        /*
        def stage = new com.p72.devops.stage.shared.DefaultCheckoutStage(new JenkinsUtils(pipeline))
        stage.injectPipeline(pipeline)
        */
        return stage
    }
    IBuildStage buildStageFactory() { return null; }
    ITestStage testStageFactory() { return null; }
    IPackageStage packageStageFactory() { return null; }
    IPublishStage publishStageFactory() { return null; }
    IDeployStage deployStageFactory() { return null; }

    void printAllMethods( obj ){
        /*if( !obj ){
            pipeline.println( "Object is null\r\n" );
            return;
        }
        if( !obj.metaClass && obj.getClass() ){
            printAllMethods( obj.getClass() );
            return;
        }*/
        def str = "class ${obj.getClass().name} functions:\r\n";
        obj.metaClass.methods.name.unique().each{ 
            str += it+"(); "; 
        }
        pipeline.println "${str}\r\n";
    }

}