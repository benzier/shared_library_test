package com.p72.devops

abstract class TestStagePostActionDecorator implements ITestStep {
    ITestStage testStage;

    TestStageDecorator(ITestStage)

    def runTestStage(){
        return testStage.runTestStage();
    }
}