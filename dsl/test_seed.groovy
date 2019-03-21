pipelineJob('example') {
    definition {
        cps {
            script('''
                @Library('pipeline-library-demo')_
                sayHello {
                    name = 'Ruben'
                }
            ''')
            sandbox()
        }
    }
}