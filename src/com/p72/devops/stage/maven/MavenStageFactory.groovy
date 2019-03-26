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

    ICheckoutStage checkoutStageFactory(String className, externalLibrary) {
        return instanceClass(className, ICheckoutStage, externalLibrary)
    }

    IBuildStage buildStageFactory(String className, externalLibrary) { return null; }
    ITestStage testStageFactory(String className, externalLibrary) { return null; }
    IPackageStage packageStageFactory(String className, externalLibrary) { return null; }
    IPublishStage publishStageFactory(String className, externalLibrary) { return null; }
    IDeployStage deployStageFactory(String className, externalLibrary) { return null; }

    private Object instanceClass(String className, Class superClass, externalLibrary){
        def constructor = null
        def stage = null

        // Create the stage instance from external library
        if(externalLibrary != null){
            stage = Eval.xy(externalLibrary,jenkins,"x.${className}.new y")

            //check that this class inherit from the expected superClass;
            if (superClass == stage.getClass().getSuperclass()) {
                pipeline.println("is instance of ICheckoutStage")
            } else {
                // error
                pipeline.println("is not instance of ICheckoutStage")
            }
        } else {
            Class classToload = this.getClass().classLoader.loadClass(className, true, false);     
            if (superClass == classToload.getSuperclass()) {
                pipeline.println("is instance of ICheckoutStage")
            } else {
                // error
                pipeline.println("is not instance of ICheckoutStage")
            }

            if(classToload.getDeclaredConstructors().size() != 1){
                // error
                pipeline.println("only one constructor is allowed")
            }
            constructor = classToload.getDeclaredConstructors()[0];

            stage = constructor.newInstance(jenkins);
        }

        // Inject pipeline to the step to be able to call jenkins functions
        stage.injectPipeline(pipeline)
        pipeline.println("new ${superClass.toString()} Stage Created: ${stage.getClass().toString()}")
        return stage
    }


    /*void printAllMethods( obj ){
        if( !obj ){
            pipeline.println( "Object is null\r\n" );
            return;
        }
        if( !obj.metaClass && obj.getClass() ){
            printAllMethods( obj.getClass() );
            return;
        }
        def str = "class ${obj.getClass().name} functions:\r\n";
        obj.metaClass.methods.name.unique().each{ 
            str += it+"(); "; 
        }
        pipeline.println "${str}\r\n";
    }*/

}