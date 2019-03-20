#!/usr/bin/env groovy

import com.GlobalVars


def call(String name = 'human') {
  echo "Hello, ${GlobalVars.foo} ${name}."
}

