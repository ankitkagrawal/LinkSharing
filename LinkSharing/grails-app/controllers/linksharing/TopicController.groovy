package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TopicController {

    def scaffold = true

    @Transactional
    def save(Topic topicInstance) {
        if (topicInstance == null) {
            //notFound()
            return
        }

        if (topicInstance.hasErrors()) {
            respond topicInstance.errors, view:'create'
            return
        }

        topicInstance.save flush:true
        TopicService topicService
                topicService.subscribeCreator(topicInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'topic.label', default: 'Topic'), topicInstance.id])
                redirect topicInstance
            }
            '*' { respond topicInstance, [status: CREATED] }
        }
    }



}
