class MavenCheckoutStage implements ICheckoutStage {

       

    def checkout(Map params){
        return git(params)
    }

}