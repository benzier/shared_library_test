pipelineJob('example') {
    definition {
        cps {
            script{"""
                @Library('pipeline-library-demo')_
                stage('Demo') {
                    echo 'Hello World'
                    sayHello 'Dave'
                }           
            """}
        }
    }
}