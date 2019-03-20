#!/usr/bin/env groovy

import com.cleverbuilder.*


def call(String name = 'human') {
  GlobalVar obj = new GlobalVar()
  obj.send_request()
  echo "Hello, ${GlobalVars.foo} ${name}."
}

