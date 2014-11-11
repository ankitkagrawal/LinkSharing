package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReadingItemController {
    def scaffold = true

//    def markAsRead(Boolean flag,Resource resource,User user){
//
//        new ResourceService().markResourceReadUnread(flag,resource,user)
//
//    }
}
