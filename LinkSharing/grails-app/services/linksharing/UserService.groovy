package linksharing

import grails.transaction.Transactional
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Transactional
class UserService {

    def grailsApplication

    def serviceMethod() {

    }

    def savePhotoAndReturnUser(UserCommand userCommandInstance){

        User userInstance = new User()
        userInstance.properties = userCommandInstance.properties

        CommonsMultipartFile sbc = userCommandInstance.photo
        String imageType = sbc.contentType

        imageType = imageType.split("/")[1]
        String timeStamp = System.currentTimeMillis().toString()
        String imageName = timeStamp + "." + imageType

            File photoOnServer = new File(grailsApplication.config.user.photo.location.login + imageName)
            photoOnServer.bytes = sbc.bytes

            userInstance.userPhoto = imageName

            return userInstance

    }
}
