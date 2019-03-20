#!/usr/bin/env groovy
package com.cleverbuilder

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

class GlobalVars {
   static String foo = "bar"
   def obj;
   // refer to this in a pipeline using:
   //
   // import com.cleverbuilder.GlobalVars
   // println GlobalVars.foo

    GlobalVars(pipeline){
        obj = pipeline
    }

   def send_request(){
       def http = new groovyx.net.http.HTTPBuilder('http://www.codehaus.org')
       obj.echo(http)
   }
}
