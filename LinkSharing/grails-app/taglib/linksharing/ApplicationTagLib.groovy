package linksharing

class ApplicationTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def resourceService
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

                out << "<tr><td><div style=\"float: left;padding: 5px;\">"
                out << g.img(uri: "${grailsApplication.config.user.photo.location.profile+topic.user.userPhoto}",width:"80", height:"80")
                out << "</div><div style=\"padding: 5px;\">"
                out << g.link(controller: "topic", action: "show", params: [topicId: topic.id]){ "${ topic.name }" }
                out << "</div><div style=\"font-size: x-small;\">@${topic.name}</div><div style=\"float: left;margin: 5px;\">"
                out << "<a class=\"topicDetail\">Subscriptions <br>${topic.subscriptions.size()}</div><div style=\"float: left;margin: 5px;\"> Posts <br>${topic.resources.size()} </div>" +
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
        String postId = attrs.resourceId

         return resourceService.isPostRead(userId,postId)
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
               out << g.link(target: "_blank", url: "${resource.url}") {"View full site"}

            } else if (resource instanceof DocumentResource) {
                out << g.link(controller: "documentResource", action: "download",params:[resourcePath: resource.path,resourceName:resource.title]) { "Download"}
            }

            out<< "</div><div style=\"float: right;margin-bottom: 2px;margin-top: 2px;margin-left: 10px;\">"

            if(!resourceService.isPostRead(session["user"],resource)) {
                out << g.link([controller: "readingItem", action: "markAsRead", params: [resource: resource.id, user: session["user"].id,read:true]]){ "Mark as read" }
            }
            else
            {
                out << g.link([controller: "readingItem", action: "markAsRead", params: [resource: resource.id, user: session["user"].id,read:false]]){ "Mark as unread"}
            }
            out << "</div><div style=\"float: right;margin-bottom: 2px;margin-top: 2px;\">View Post</div></td></tr>"
        }
    }

    def showUsersList ={ attrs ->

        List<User> userList = attrs.usersList

        userList.each { user ->
            out << "<tr><td><div style=\"float: left;padding: 5px;\">"
            out << g.img(uri: "${grailsApplication.config.user.photo.location.profile+user.userPhoto}",width:"80", height:"80")
            out << "</div><div style=\"padding: 5px;\">"
            out << g.link(controller: "user", action: "showUserProfile", params: [userId: user.id]){ "${ user.firstName+" "+user.lastName}" }
            out << "</div><div style=\"font-size: x-small;\">@${user.firstName}</div><div style=\"float: left;margin: 5px;\">"
            out << "<a class=\"topicDetail\">Subscriptions <br>${user.topics.size()}</div><div style=\"float: left;margin: 5px;\"> Posts <br>${user.resources.size()} </div>" +
                    "</td></tr>"
        }
    }
}
