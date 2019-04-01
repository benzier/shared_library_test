import com.p72.devops.util.*

def pipelinejob = pipelineJob('example') {
    
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


