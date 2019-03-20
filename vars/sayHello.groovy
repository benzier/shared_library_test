#!/usr/bin/env groovy

import com.cleverbuilder.*


def call(String name = 'human') {
  GlobalVars obj = new GlobalVars(this)
  obj.send_request()
  println send_request2()
  echo "Hello, ${GlobalVars.foo} ${name}."
}

