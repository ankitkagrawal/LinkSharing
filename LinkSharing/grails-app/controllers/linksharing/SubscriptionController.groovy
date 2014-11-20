package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class SubscriptionController {

    def subscriptionService
    def scaffold = true


    def addNewSubscription(){

        def seriousness = params.seriousness
        String topicId= params.topicId
        String userId = params.userId

        Topic topic = Topic.findById(topicId.toLong())
        User user = User.findById(userId.toLong())

        Subscription subscription = new Subscription(seriousness: seriousness,topic: topic,user:user)

        subscription.save flush: true,failOnError: true

        List<Resource> resourceList = Resource.findAllByTopic(topic)

        resourceList.each {resource ->
            new ReadingItem(resource: resource,user: user,isRead: false).save flush: true,failOnError: true
        }

        redirect(controller: "user",action: "dashboard")

    }

    def unSubscribe(){
        String topicId= params.topicId
        String userId = params.userId

        Topic topic = Topic.findById(topicId.toLong())
        User user = User.findById(userId.toLong())

        Subscription subscription = Subscription.findByTopicAndUser(topic,user)

        subscription.delete flush: true

        List<Resource> resourceList = Resource.findAllByTopic(topic)

        resourceList.each {resource ->

            ReadingItem readingItem = ReadingItem.findByResourceAndUser(resource,user)
            readingItem.delete flush: true
        }


        redirect(controller: "user",action: "dashboard")

    }

    def update(){

        subscriptionService.updateSeriousness(params.seriousness,params.topicId,params.userId)

        redirect(controller: "user",action: "dashboard")

    }

}