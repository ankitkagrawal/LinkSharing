package linksharing

import grails.transaction.Transactional

@Transactional
class TopicService {

    Boolean isUserCreatorOfTopic(String userId, String topicId){

        Topic topic = Topic.find("from topic where id=${topicId} and user_id=${userId}")
        if(topic){return true}
        else return false

    }

    Boolean isUserSubscribedToTopic(String userId, String topicId){

        Subscription subscription = Subscription.find("from subscription where topic_id=${topicId} and user_id=${userId}")
        if(subscription){return true}
        else return false

    }

    Boolean subscribeCreator(Topic topicInstance){

        Subscription subscription = new Subscription(seriousness: Seriousness.VERY_SERIOUS, user:topicInstance.user,topic:topicInstance)
        if(subscription.validate()){
            subscription.save flush: true
            return true
        }
        else{
            println subscription.errors
            return false
        }
    }
}
