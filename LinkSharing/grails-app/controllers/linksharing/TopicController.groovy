package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class TopicController {

    TopicService topicService

    def scaffold = true

    def save(Topic topicInstance) {

        if (topicInstance == null) {
            //notFound()
            return
        }

        topicInstance.user=session["user"] as User

/*
        if (topicInstance.hasErrors()) {
            respond topicInstance.errors, view:'create'
            return
        }
*/

        topicInstance.save flush:true,failOnError: true

        topicInstance=topicService.subscribeCreator(topicInstance)

//        respond view: 'index',params:["topic":topicInstance]

        index(topicInstance)

    }

    def index(Topic topic){

        List<Resource> resourceList= []
        topic.resources.each {
            resourceList<<it
        }

        render(view: "index",model:["topic":topic,"itemList":resourceList,"userList":topic.subscriptions?.user])
    }

    def show(Integer topicId){
        Topic topic = Topic.findById(topicId)
        index(topic)
    }



}
