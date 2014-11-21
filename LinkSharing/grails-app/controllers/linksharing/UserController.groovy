package linksharing

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class UserController {

    def scaffold = true
    def topicService
    def userService
    def resourceService

    def dashboard(){

        User user = session["user"] as User

        List<ReadingItem> unreadItemList = ReadingItem.findAllByUserAndIsRead(user,false)

        List<Resource> unreadResourceList = []
        unreadItemList.each { readingItem ->
            unreadResourceList << readingItem.resource
        }

        List<Topic> trendingTopics = topicService.trendingTopicList()
        List<Topic> subscribedTopics = topicService.subscribedTopicList(user)

        render(view:"dashboard",model: ["user":user,"itemList":unreadResourceList,"trendingTopics":trendingTopics,\
         "subscribedTopicList":subscribedTopics])
    }

    @Transactional
    def save(UserCommand userCommandInstance) {
        if (userCommandInstance == null) {
            notFound()
            return
        }

        if (userCommandInstance.hasErrors()) {
            respond userCommandInstance.errors,  view:'../login/login'
            return
        }

        User userInstance = new User()
        userInstance=userService.savePhotoAndReturnUser(userCommandInstance,userInstance)

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'../login/login'
            return
        }

        userInstance.save flush:true

        LoginController lc = new LoginController()
                lc.loginHandler(userInstance)
        }

    def showUserProfile(User user){

        List<Resource> resourceList = resourceService.getAllPublicPostsOfUser(user)

        List<Topic> topicList = topicService.getTopicsCreatedByUserByVisibility(user,Visibility.PUBLIC)

        render(view: "showProfile",model: [user:user,itemList:resourceList,subscribedTopicList:topicList])

    }

    def editProfile(){

        User user = session.user

        UserCommand userCommandInstance = new UserCommand(email: user.email,firstName: user.firstName,lastName: user.lastName,
                userName: user.userName)

        List<Topic> topicList = topicService.getAllTopicsCreatedByUser(user)

        render(view: "editProfile",model: [userCommandInstance: userCommandInstance,user:user,topicList:topicList])


    }

    def update(){


        User user = session['user']
        List<Topic> topicList = topicService.getAllTopicsCreatedByUser(user)

        UserCommand userCommandInstance = new UserCommand()
        userCommandInstance.firstName = params.firstName
        userCommandInstance.lastName = params.lastName
        userCommandInstance.photo=params.photo

        userCommandInstance.email = user.email
        userCommandInstance.userName = user.userName
        userCommandInstance.password = user.password
        userCommandInstance.confirmPassword= user.password

        userCommandInstance.validate()


        if (userCommandInstance.hasErrors()) {
            respond userCommandInstance.errors,  view:'editProfile',model: [user:user,topicList:topicList]
            return
        }

        User userInstance = User.findById(user.id)
        userInstance=userService.savePhotoAndReturnUser(userCommandInstance,userInstance)

        userInstance.save flush:true

        session.user= userInstance

        redirect(action: "editProfile")
    }

    def changePassword(){


        User user = session['user']
        List<Topic> topicList = topicService.getAllTopicsCreatedByUser(user)

        UserCommand userCommandInstance = new UserCommand()
        userCommandInstance.firstName = user.firstName
        userCommandInstance.lastName = user.lastName
//        userCommandInstance.photo=user.userPhoto

        userCommandInstance.email = user.email
        userCommandInstance.userName = user.userName
        userCommandInstance.password = params.password
        userCommandInstance.confirmPassword= params.confirmPassword

        userCommandInstance.validate()


        if (userCommandInstance.hasErrors()) {
            respond userCommandInstance.errors,  view:'editProfile',model: [user:user,topicList:topicList]
            return
        }

        User userInstance = User.findById(user.id)
//        userInstance=userService.savePhotoAndReturnUser(userCommandInstance,userInstance)
        userInstance.password = userCommandInstance.password

        userInstance.save flush:true
        session.user= userInstance

        redirect(action: "editProfile")

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

@Validateable
class UserCommand{

    static def final IMAGE_TYPE = ["image/jpg","image/jpeg","image/png","image/bmp","image/gif"]

    String email
    String userName
    String password
    String firstName
    String lastName
    String confirmPassword
    CommonsMultipartFile photo

    static constraints={
        importFrom User

        photo nullable: true,blank:false, validator: {val ->
                                                     if(!val?.empty && val!=null && !IMAGE_TYPE.contains(val?.contentType)){
                                                        return  "image.upload.validator"
                                                            }
                                                        }
        confirmPassword nullable: false, blank: false

        password validator: { val, obj ->
            if(!val.equals(obj.confirmPassword)){
                return "password.no.match"
            }
        }

    }

}
