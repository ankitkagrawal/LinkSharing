package linksharing

import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    def serviceMethod() {

    }

    Boolean isUserSubscribedToTopic(String userId, String topicId){

        Subscription subscription = Subscription.find("from subscription where topic_id=${topicId} and user_id=${userId}")
        if(subscription){return true}
        else return false

    }

    def updateSeriousness(def seriousness,def topicId,def userId){


        Topic topic = Topic.findById(topicId.toLong())
        User user = User.findById(userId.toLong())

        Subscription subscription = Subscription.findByTopicAndUser(topic,user)

        subscription?.seriousness = seriousness
        subscription?.save flush: true,failOnError: true

    }
}
