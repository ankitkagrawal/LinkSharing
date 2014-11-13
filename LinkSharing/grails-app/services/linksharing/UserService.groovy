package linksharing

import grails.transaction.Transactional
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Transactional
class UserService {

    def grailsApplication

    def serviceMethod() {

    }

    def savePhotoAndReturnUser(UserCommand userCommandInstance){

        String timeStamp=System.currentTimeMillis().toString()


        CommonsMultipartFile sbc = userCommandInstance.photo
        String imageType =sbc.contentType
        imageType=imageType.split("/")[1]
        String imageName=timeStamp+"."+imageType
        File photoOnServer = new File(grailsApplication.config.user.photo.location.login+imageName)
        photoOnServer.bytes=sbc.bytes
//        sbc.bytes=userCommandInstance.photo.bytes
//        photoOnServer.bytes=userCommandInstance.photo.bytes
//        photoOnServer.append(userCommandInstance.photo.inputStream)
//        photoOnServer.name=grailsApplication.config.user.photo.location+timeStamp+".jpg"

        User userInstance= new User()
        userInstance.properties= userCommandInstance.properties
        userInstance.userPhoto=imageName

        return userInstance

    }
}
