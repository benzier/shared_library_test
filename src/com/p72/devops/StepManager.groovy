class StepManager{
    def pipelineSteps = [
            [
                stage: 'checkout',
                default: true, 
                class: "mi class", 
                repo: "dsl",
                order: 10 
            ], 
            [
                stage: 'build',
                default: true, 
                class: "mi class", 
                repo: "dsl",
                order: 20
            ],
            [
                stage: 'test',
                default: true, 
                class: "customTestStage", 
                repo: "dsl",
                order: 30
            ], 
            [
                stage: 'publish',
                default: true, 
                class: "mi class", 
                repo: "dsl",
                order: 40
            ],
            [
                stage: 'deploy',
                default: true, 
                class: "mi class", 
                repo: "dsl",
                order: 50
            ]
        ]
    

}