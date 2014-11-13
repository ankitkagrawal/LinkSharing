package linksharing

import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    def scaffold = true
    def topicService
    def userService

    def dashboard(){

        User user = session["user"] as User

        List<ReadingItem> unreadItemList = ReadingItem.findAllByUserAndIsRead(user,false)

        List<Resource> unreadResourceList = []
        unreadItemList.each { readingItem ->
            unreadResourceList << readingItem.resource
        }

        List<Topic> trendingTopics = topicService.trendingTopicList()

        render(view:"dashboard",model: ["user":user,"itemList":unreadResourceList,"trendingTopics":trendingTopics])
    }

    @Transactional
    def save(UserCommand userCommandInstance) {
        if (userCommandInstance == null) {
            notFound()
            return
        }
        User userInstance =userService.savePhotoAndReturnUser(userCommandInstance)


        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
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

class UserCommand{

    String email
    String userName
    String password
    String firstName
    String lastName
    CommonsMultipartFile photo

    static constraints={
        importFrom User
    }
}
