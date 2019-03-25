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

    ICheckoutStage checkoutStageFactory(String className) {
        return instanceClass(className, ICheckoutStage)
    }
    IBuildStage buildStageFactory(String className) { return null; }
    ITestStage testStageFactory(String className) { return null; }
    IPackageStage packageStageFactory(String className) { return null; }
    IPublishStage publishStageFactory(String className) { return null; }
    IDeployStage deployStageFactory(String className) { return null; }

    private Object instanceClass(String className, Class superClass){
        def constructor = null;
        def stage=null;

        Class classToload = this.getClass().classLoader.loadClass(className, true, false);     
        if (superClass == classToload.getSuperclass()){
            pipeline.println("is instance of ICheckoutStage")
        }else{
            // error
            pipeline.println("is not instance of ICheckoutStage")
        }

        if(classToload.getDeclaredConstructors().size() != 1){
            // error
            pipeline.println("only one constructor is allowed")
        }
        constructor=classToload.getDeclaredConstructors()[0];

        stage = constructor.newInstance(jenkins);
        stage.injectPipeline(pipeline)

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