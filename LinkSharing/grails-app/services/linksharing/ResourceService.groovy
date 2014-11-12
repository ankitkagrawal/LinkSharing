package linksharing

import grails.transaction.Transactional

@Transactional
class ResourceService {

    def serviceMethod() {

    }

    Boolean isPostRead(String userId, String postId){

        ReadingItem readingItem = ReadingItem.find("from reading_item where user_id = ${userId} and resource_id=${postId}")

        return readingItem?.isRead
    }

    Boolean markResourceReadUnread(Boolean flag, Resource resource, User user){

        ReadingItem readingItem = ReadingItem.find(resource:resource,user:user)
        readingItem.isRead=flag
        readingItem.save(flush: true,failOnError: true)

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
