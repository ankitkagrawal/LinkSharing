package linksharing

class LoginController {

    def userService

    def index() {

        User user = User.findWhere(userName: params.username,\
        password: params.password)

        if(user?.active) {
            loginHandler(user)
        }
        else{
            failedLoginRedirect()
        }
    }

    def failedLoginRedirect(){
        render(view:"login")
    }

    def loginHandler(User user){
//        session["userName"]=user.userName
        session["user"]=user
        redirect(controller: 'user', action: 'dashboard' )
    }

    def logoutHander(String userName){
        session["user"]=null
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
