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
        obj =pipeline
    }

   def send_request(){

        def http = new groovyx.net.http.HTTPBuilder( 'http://ajax.googleapis.com' )
            // perform a GET request, expecting JSON response data
            http.request( GET, JSON ) {
            uri.path = '/ajax/services/search/web'
            uri.query = [ v:'1.0', q: 'Calvin and Hobbes' ]

            headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

            // response handler for a success response code:
            response.success = { resp, json ->
                obj.echo resp.status

                // parse the JSON response object:
                json.responseData.results.each {
                obj.echo "  ${it.titleNoFormatting} : ${it.visibleUrl}"
                }
            }

            // handler for any failure status code:
            response.failure = { resp ->
                obj.echo "Unexpected error: ${resp.status} : ${resp.statusLine.reasonPhrase}"
            }
        }


   }
}
