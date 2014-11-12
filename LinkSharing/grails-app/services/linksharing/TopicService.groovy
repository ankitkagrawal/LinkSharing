package linksharing

import grails.transaction.Transactional

@Transactional
class TopicService {

    Boolean isUserCreatorOfTopic(String userId, String topicId){

        Topic topic = Topic.find("from topic where id=${topicId} and user_id=${userId}")
        if(topic){return true}
        else return false

    }

    Boolean isUserSubscribedToTopic(User user, Topic topic){

        Subscription subscription = Subscription.findByTopicAndUser(topic,user)
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

    Boolean subscribeUserToTopic(Topic topicInstance, User userInstance, Seriousness seriousness){

        Subscription subscription = new Subscription(seriousness:seriousness, user:userInstance,topic:topicInstance)
        if(subscription.validate()){
            subscription.save flush: true
            return true
        }
        else{
            println subscription.errors
            return false
        }

    }

    List<Topic> trendingTopicList(){

        List<Topic> topicList= Topic.list()

        topicList.sort{
            -it.resources.size()
        }
        return topicList.subList(0,5)

        /*def criteria = Resource.createCriteria()

        def result = criteria.list {

            projections{
                groupProperty('topic')
                rowCount('total')
            }

            order('total','desc')
            maxResults(5)


        }*/

        //println result


    }


}
