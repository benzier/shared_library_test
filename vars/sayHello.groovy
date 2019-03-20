#!/usr/bin/env groovy

import com.cleverbuilder.*


def call(String name = 'human') {
  GlobalVars obj = new GlobalVars()
  obj.send_request()
  echo "Hello, ${GlobalVars.foo} ${name}."
}

