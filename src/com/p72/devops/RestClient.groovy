#!/usr/bin/env groovy
package com.p72.devops

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.JSON
import groovy.json.JsonSlurper

class RestClient {

    // Static section


    // private fields
    def pipeline;
    //HTTPBuilder http;

    // public methods
    def getApiCall(Map query, String host, path, contentType=JSON){
        def http = new HTTPBuilder(host)
        def result = ""
        http.get( path : path, 
                contentType : contentType,
                query : query ) { resp, body ->
            result=body
            pipeline.println "response status: ${resp.statusLine}"
        }
        return result;
    }

    def postApiCall(){}
    def putApiCall(){}

    String getSimpleAuthToken(String user, String password){
        // http.auth.basic(user, pass)
        "${user}:${password}".bytes.encodeBase64().toString()
    }

    // private methods
    /*private String convert(InputStream inputStream, Charset charset=) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }*/
}