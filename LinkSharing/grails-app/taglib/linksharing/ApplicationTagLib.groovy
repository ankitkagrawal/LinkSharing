package linksharing

class ApplicationTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"

    def showPosts = { attr ->
        def type = attr.type
        def itemList = attr.itemList

//    if(type.equalsIgnoreCase("inbox")){
         out <<  """<table><th>${type}</th>"""

         itemList.each { resource ->
           out<< "<tr><td>Topic Name - ${resource.topic.name} Title - ${resource.title}"
           out<< "Description - ${resource.description}<br/>"
           out<< "Download----View full site----"
           out<< g.link(controller:"readingItem",action:"markAsRead",params:"${[resource:resource.id,user:session['user'].id]}"){"Mark as read"}
           out<< "----View Post</td></tr>"
         }

    out<< """</table>"""
//    }
//    else if(type.equalsIgnoreCase("recentPost")){
//         out <<  "<g:render template='recentPost'/> "
//    }
//    else out<<""
    }


    def showTopics ={ attr ->
        String type=attr.topicType
        def topicList = attr.topicList

        if(type.equalsIgnoreCase("trendingTopics") || type.equalsIgnoreCase("subscriptions")) {
            topicList.each { topic ->

                out << "<tr><td>"
                out << g.link(controller: "topic", action: "show", params: [topicId: topic.id]){ "${ topic.name }" }
                out << "<br><a class=\"topicDetail\">Subscriptions Post<br>${topic.subscripstions.size()} -- ${topic.resources.size()}" +
                        "</td></tr>"
            }
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

        List<Resource> resourceList = attrs.resourceList
        User user = attrs.user

        resourceList.each { resource ->

            out << "<tr><td><div style=\"float: left;\">${resource.title}</div>"

            out << "<div style=\"float: right;\">"
            out<< g.link([controller: "topic", action: "show", params:[topicId:resource.topic.id]]) {"${resource.topic.name}" }
            out << "</div>"

            out << "<div style=\"float: left;clear: left;\">${resource.description}</div>"

            out<< "<div style=\"float: left;clear: left;margin-top:2px;margin-bottom:2px\">"
            out << "<div style=\"margin-left:5px;float: left;\">"
            out << g.img(dir: "/LinkSharing/assets",file: "fb.jpeg",width:"20", height:"20")
            out << "</div>"
            out << "<div style=\"margin-left:5px;float: left;\">"
            out << g.img(dir: "/LinkSharing/assets",file: "gp.jpeg",width:"20", height:"20")
            out << "</div>"
            out << "<div style=\"margin-left:5px;float: left;\">"
            out << g.img(dir: "/LinkSharing/assets",file: "twitter.png",width:"20", height:"20")
            out << "</div>"

            out << "</div>"

/*          out<< "<div style=\"float: right;margin-bottom: 2px;margin-top: 2px;margin-left: 10px;\">"


            if(resource.user.id==user.id){
                out << "Delete"
            }
            out<< "</div>"
*/

            out<< "<div style=\"float: right;margin-bottom: 2px;margin-top: 2px;margin-left: 10px;\">"

            if (resource instanceof LinkResource) {
               out << "View full site"

            } else if (resource instanceof DocumentResource) {
                out << "Download"
            }

            out<< "</div><div style=\"float: right;margin-bottom: 2px;margin-top: 2px;margin-left: 10px;\">"
//            out << g.link(controller:"readingItem",action:"markAsRead",params:"${[resource:resource.id,user:session['user'].id]}"){"Mark as read"}
            out<< g.link([controller: "readingItem", action: "markAsRead", params: [resource: resource.id,user:session["user"].id]]) { "Mark as read"}
            out << "</div><div style=\"float: right;margin-bottom: 2px;margin-top: 2px;\">View Post</div></td></tr>"



        }

    }

}
