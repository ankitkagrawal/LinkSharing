package linksharing

import grails.transaction.Transactional

@Transactional(readOnly = false)
class ResourceService {

    def grailsApplication

    def serviceMethod() {

    }
    Boolean isPostRead(def user, def resource){

        ReadingItem readingItem = ReadingItem.findByUserAndResource(user,resource)

        return readingItem?.isRead
    }

    Boolean markResourceReadUnread(def resource, def user, def read){

        Resource resourceInstance = Resource.findById(resource)
        User userInstance = User.findById(user)

        ReadingItem readingItem = ReadingItem.findByResourceAndUser(resourceInstance,userInstance)
        ReadingItem readingItem1 = ReadingItem.get(readingItem.id)

//        Boolean isRead = read.equals("true")
        readingItem1.isRead=read
        readingItem1.save(flush: true,failOnError: true)
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

    def getDocumentResourceFromCommand(DocumentResourceCommand documentResourceCommandInstance, User user ){

        DocumentResource documentResourceInstance = new DocumentResource()
        documentResourceInstance.properties = documentResourceCommandInstance.properties
        documentResourceInstance.user=user

        String docType = documentResourceCommandInstance.document.contentType

        docType = docType.split("/")[1]
        String timeStamp = System.currentTimeMillis().toString()
        String docName = timeStamp + "." + docType

        File fileOnServer = new File(grailsApplication.config.user.doc.location+docName)
        fileOnServer.bytes=documentResourceCommandInstance.document.bytes

        documentResourceInstance.path=docName

        Topic topic =Topic.findById( documentResourceCommandInstance.topicId.toLong())
        documentResourceInstance.topic=topic

        return documentResourceInstance
    }

    List<Resource> getAllPublicPostsOfUser(User user){

        List<Resource> resourceList = Resource.createCriteria().list {

            eq('user',user)
            "topic"{
                eq('visibility',Visibility.PUBLIC)
            }

            order('dateCreated','desc')

        }

        return resourceList

    }

}
