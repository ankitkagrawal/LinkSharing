package linksharing

class ApplicationTagLib {
//    static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"

    def showPosts = { attr ->
        String type = attr.type
    if(type.equalsIgnoreCase("inbox")){
         out <<  "<g:render template='inbox'/> "
    }
    else if(type.equalsIgnoreCase("recentPost")){
         out <<  "<g:render template='recentPost'/> "
    }
    else out<<""
    }


    def showTopics ={ attr ->
        String type=attr.type

        if(type.equalsIgnoreCase("subscriptions")){
            out << "<g:render template='subscription'/> "
        }
        else if(type.equalsIgnoreCase("trendingTopics")){
            out << "<g:render template='trendingTopics'/> "
        }
    }

    def isEditable = { attrs ->

        String topicId = attrs.topicId
        String userId = attrs.userId

        TopicService topicService = new TopicService()

        if(topicService.isUserCreatorOfTopic(topicId,userId)){
            out << "<g:render template='editTopic'/> "
        }
        else {
            out << ""
        }

    }

    def isSubscribed ={attrs ->

        String topicId = attrs.topicId
        String userId = attrs.userId

        SubscriptionService subscriptionService = new SubscriptionService()
        if(subscriptionService.isUserSubscribedToTopic(topicId,userId)){
            out << "<g:render template='showEmailAndSeriousness'/> "
        }
        else{
            out << "<g:render template='subscribeTopic'/> "
        }

    }

    def isRead={ attrs ->

        String userId = attrs.userId
        String postId = attrs.postId

        ResourceService resourceService = new ResourceService()
        if(resourceService.isPostRead(userId,postId))
            out << "<g:render template='showMarkAsRead'/> "
        else
            out << "<g:render template='showMarkAsUnread'/> "
    }

    def showResource={ attrs ->

        Resource resource = attrs.resource
        if(resource instanceof LinkResource){
            out << "<g:render template='showLinkResource'/> "
        }
        else if (resource instanceof DocumentResource){
            out << "<g:render template='showDocumentResource'/> "
        }


    }

}
