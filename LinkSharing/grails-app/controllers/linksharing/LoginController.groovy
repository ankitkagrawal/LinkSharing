package linksharing

class LoginController {

    def userService

    def index() {
        render(view:"login")
    }

    def loginHandler(){

        User user = User.findWhere(userName: params.username,password: params.password)

        if(user?.active) {
            session["user"]=user
            redirect(controller: 'user', action: 'dashboard' )
        }
        else{
            index()
        }
    }

    def logoutHander(String userName){
        session["user"]=null
        session.invalidate()
        render(view:"login")
    }


    def registerNewUser(UserCommand userCommandInstance){
        if (userCommandInstance == null) {
            notFound()
            return
        }

        if (userCommandInstance.hasErrors()) {
            respond userCommandInstance.errors,  view:'login'
            return
        }

        User userInstance =userService.savePhotoAndReturnUser(userCommandInstance)


        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'login'
            return
        }
        // println "2......"

        userInstance.save flush:true

/*        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }*/
//           '*' { respond userInstance, [status: CREATED] }

        LoginController lc = new LoginController()
        lc.loginHandler(userInstance)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
