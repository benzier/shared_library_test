import com.p72.devops.util.*

DslUtils util = new DslUtils(this)


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

util.addParam(pipelinejob, "sisi")