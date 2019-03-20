#!/usr/bin/env groovy

import com.cleverbuilder.*


def call(String name = 'human') {
  echo "Hello, ${GlobalVars.foo} ${name}."
}

