package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    def scaffold = true

    def dashboard(){
        User user = session["user"] as User
        render(view:"dashboard",model: user)
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        userInstance.save flush:true

/*        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }*/
//           '*' { respond userInstance, [status: CREATED] }

        LoginController lc = new LoginController()
                lc.loginHandler(userInstance.userName)
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
