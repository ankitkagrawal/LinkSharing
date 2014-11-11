package linksharing

class LoginController {

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

}
