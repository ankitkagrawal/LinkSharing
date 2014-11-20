package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class TopicController {

    TopicService topicService
    def subscriptionService

    def scaffold = true

    def save(Topic topicInstance) {

        if (topicInstance == null) {
            //notFound()
            return
        }

        topicInstance.user=session["user"] as User

        topicInstance.save flush:true,failOnError: true

        topicInstance=topicService.subscribeCreator(topicInstance)

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

    def update(){

        topicService.updateVisibility(params.visibility,params.topicId)

        subscriptionService.updateSeriousness(params.seriousness,params.topicId,params.userId)

        redirect(controller: "user",action: "dashboard")

    }

    def deleteTopic(){

        String topicId = params.topicId

        Topic topic = Topic.findById(topicId.toLong())

        topic.delete flush: true

        redirect(controller: "user",action: "dashboard")
    }

    def search(){

        List searchList = topicService.search(params.search_text)
        List<Topic> trendingTopicList = topicService.trendingTopicList()

        render(view: "search",model: [itemList:searchList,keyword:params.search_text,trendingTopics:trendingTopicList])

    }


}
