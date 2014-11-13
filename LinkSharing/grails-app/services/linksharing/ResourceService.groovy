package linksharing

import grails.transaction.Transactional

@Transactional(readOnly = false)
class ResourceService {

    def serviceMethod() {

    }
/*
    Boolean isPostRead(String userId, String postId){

        ReadingItem readingItem = ReadingItem.find("from reading_item where user_id = ${userId} and resource_id=${postId}")

        return readingItem?.isRead
    }*/

    Boolean markResourceReadUnread(def resource, def user){

        Resource resourceInstance = Resource.findById(resource)
        User userInstance = User.findById(user)

        ReadingItem readingItem = ReadingItem.findByResourceAndUser(resourceInstance,userInstance)
        readingItem.isRead=true
        readingItem.save(flush: true,failOnError: true)
        return true
    }

    def populateUnreadItems(def resourceInstance){

        List<Subscription> subscriptionList = Subscription.findAllByTopic(resourceInstance.topic)
        List<User> userList = []
        subscriptionList.each {subscription ->
            userList << subscription.user
        }

        userList.each { user ->

            new ReadingItem(user:user,resource: resourceInstance,isRead: false).save( flash :true)
        }
    }


}
