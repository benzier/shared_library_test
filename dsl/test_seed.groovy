pipelineJob('example') {
    parameters {
        activeChoiceParam('state') {
            description('select one option')
            groovyScript {
                script('["Sao Paulo", "Rio de Janeiro", "Parana:selected", "Acre"]')
                fallbackScript('return ["ERROR"]')
            }
        }
    }
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