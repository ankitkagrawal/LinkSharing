package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class ReadingItemController {
    def scaffold = true

    def resourceService

    def markAsRead(){

        resourceService.markResourceReadUnread(params.resource,params.user,(params.read).equals("true"))

        redirect(controller: 'user', action: 'dashboard' )

    }
}
