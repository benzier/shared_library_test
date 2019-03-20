#!/usr/bin/env groovy
package com.cleverbuilder

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.JSON

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
        println "sendrequest"
        obj.echo "sendrequest1"
        def http = new HTTPBuilder( 'http://ajax.googleapis.com' );
        obj.echo "sendrequest2"
        // perform a GET request, expecting JSON response data
        def result="lalala"
        http.request( GET, JSON ) { 
            uri.path = '/ajax/services/search/web'
            uri.query = [ v:'1.0', q: 'Calvin and Hobbes' ]

            headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

            // response handler for a success response code:
            response.success = { resp, json ->
                result=resp.status

                // parse the JSON response object:
                json.responseData.results.each {
                obj.echo "  ${it.titleNoFormatting} : ${it.visibleUrl}"
                }
            }

            // handler for any failure status code:
            response.failure = { resp ->
                obj.echo "Unexpected error: ${resp.status} : ${resp.statusLine.reasonPhrase}"
                result=resp.status
            }
        }
        obj.echo "sendrequest3"
        return result
    }


    def send_request2(){
        this.doGET("http://ajax.googleapis.com", "/ajax/services/search/web")
    }

    def doGET( String serverUrl, String endpoint, debug=false) { 
        try { 
            def http = new HTTPBuilder(serverUrl) 
            http.headers += ["Accept": "application/json", "Content-Type" : "application/json"/*, "Authorization": "Basic \$basicAuth"*/] 
            http.get( path : endpoint ) {  response, reader -> 
                def resp= response
                return reader
            }
        } catch(groovyx.net.http.HttpResponseException err){
            return "null" 
        }
    }
}
