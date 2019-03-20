#!/usr/bin/env groovy

import com.cleverbuilder.*


def call(String name = 'human') {
  GlobalVars obj = new GlobalVars(this)
  //println obj.send_request()
  println obj.send_request2()
  echo "Hello, ${GlobalVars.foo} ${name}."
}

