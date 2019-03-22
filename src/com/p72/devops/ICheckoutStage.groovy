abstract class ICheckoutStage {

    protected def git
    protected def sha

    ICheckoutStage(git, sh){
        this.git = git
        this.sh = sh
    }
    

    abstract def checkout(Map params){
    }

}