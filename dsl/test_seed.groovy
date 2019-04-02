import com.p72.devops.util.*

DslUtils util = new DslUtils(this)


def pipelinejob = pipelineJob('example2');

util.createBuildJob(pipelinejob)