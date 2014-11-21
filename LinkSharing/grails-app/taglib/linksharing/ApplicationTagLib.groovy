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

        out << "<table class=\"topicTable\"><th>${type}</th>"

            topicList.each { topic ->

                out << "<tr><td><div style=\"float: left;padding: 5px;\">"
                out << g.img(uri: "${grailsApplication.config.user.photo.location.profile+topic.user.userPhoto}",width:"80", \
                 height:"80")
                out << "</div><div style=\"padding: 5px;\">"
                out << g.link(controller: "topic", action: "show", params: [topicId: topic.id]){ "${ topic.name }" }
                out << "</div><div style=\"font-size: x-small;\">@${topic.name}</div><div style=\"float: left;margin: 5px;\">"
                out << "<a class=\"topicDetail\">Subscriptions <br>${topic.subscriptions.size()}</a></div><div style=\"float:left;margin: 5px;\"><a class=\"topicDetail\">Posts<br>${topic.resources.size()}</a></div>"

                out << "<div style=\"clear: left;\">"

                out<< isEditable(topic: topic)

                out << "</div>"

                out<< "</td></tr>"
            }

        out << "</table>"
    }

    def isEditable = { attrs ->
        Topic topic = attrs.topic
        User user =session["user"]

        if(topic.user.id==user.id) {

            out << "<div style=\"float:left;\">"

            def formAttr = [controller: "topic", action: "update"]
            def formBody = g.select(from: Seriousness.values(), name: "seriousness", value: Subscription.
                    findByTopicAndUser(topic,user)?.seriousness,class: "formAttr")
            formBody += g.select(from: Visibility.values(), name: "visibility", value: topic.visibility,class: "formAttr")
            formBody += g.submitButton(name: "Submit", value: "Submit",class: "formAttr",style: "width:43px;")
            formBody += g.hiddenField(name: "topicId",value: topic.id)
            formBody += g.hiddenField(name: "userId",value: user.id)

            out << g.form(formAttr, formBody)

            out << "</div><div style=\"padding-top: 2px;padding-left: 200px;\">"

            def mailLinkAttr =[controller: "mail",action: "index",params: [id: topic.id]]
            def mailLinkBody = asset.image(src:"mail-icon.png", width:"18", height:"18",title:"Send Invitation")
            out << g.link(mailLinkAttr,mailLinkBody)

            def linkAttr = [controller: "topic",action: "deleteTopic",params: [topicId: topic.id],onclick:"return confirm(\"Delete ${topic.name}?\")"]
            def imgAttr = asset.image(src:"delete.png", width:"18", height:"18",title:"Delete Topic")
            out << g.link(linkAttr,imgAttr)

            out<<"</div>"

        }
        else {
           out<< isSubscribed(topic: topic)
        }

    }

    def isSubscribed ={attrs ->

        Topic topic = attrs.topic
        User user = session["user"]
        Subscription subscription = Subscription.findByTopicAndUser(topic,user)

        if(subscription!=null && topic.user.id!=user.id) {

            out << "<div style=\"float:left;\">"

            def formAttr = [controller: "subscription", action: "update"]
            def formBody = g.select(from: Seriousness.values(), name: "seriousness", value: Subscription.
                    findByTopicAndUser(topic, user)?.seriousness,class: "formAttr")
            formBody += g.submitButton(name: "Submit", value: "Submit",class: "formAttr",style: "width:43px;")
            formBody += g.hiddenField(name: "topicId",value: topic.id)
            formBody += g.hiddenField(name: "userId",value: user.id)
            out << g.form(formAttr, formBody)
            out<<"</div>"

            out << "<div style=\"float:left;\">"
            formAttr = [controller: "subscription", action: "unSubscribe"]
            formBody = g.submitButton(name:"unsubscribe",value:"Unsubscribe",class: "formAttr")
            formBody += g.hiddenField(name: "topicId",value: topic.id)
            formBody += g.hiddenField(name: "userId",value: user.id)
            out << g.form(formAttr, formBody)

            out << "</div><div style=\"padding-top: 2px;padding-left: 200px;\">"
//            out << asset.image(src:"mail-icon.png", width:"18", height:"18",title:"Send Invitation")
            def mailLinkAttr =[controller: "mail",action: "index",params: [id: topic.id]]
            def mailLinkBody = asset.image(src:"mail-icon.png", width:"18", height:"18",title:"Send Invitation")
            out << g.link(mailLinkAttr,mailLinkBody)
            out<<"</div>"

        }else {
           out<< isNotSubscribed(topic: topic)
        }
    }

    def isNotSubscribed={attrs ->

        Topic topic = attrs.topic
        User user =session["user"]
        Subscription subscription = Subscription.findByTopicAndUser(topic,user)

        if(topic.user.id!=user.id && subscription==null) {

            out << "<div style=\"float:left;\">"

            def formAttr = [controller: "subscription", action: "addNewSubscription"]
            def formBody = g.select(from: Seriousness.values(), name: "seriousness",class: "formAttr",style: "width:105px;")
            formBody += g.submitButton(name: "subscribe", value: "subscribe",class: "formAttr",style: "width:63px;")
            formBody += g.hiddenField(name: "topicId",value: topic.id)
            formBody += g.hiddenField(name: "userId",value: user.id)

            out << g.form(formAttr, formBody)

            out<<"</div>"

        }


    }

    def showResource={ attrs ->

        List<Resource> resourceList = attrs.resourceList
        String header = attrs.ListType
        User user = attrs.user

        out << "<th>${header}</th>"

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

            out<< "<div style=\"float: right;margin-bottom: 2px;margin-top: 2px;margin-left: 10px;\">"

            out << resourceViewOption(resource: resource)

            out<< "</div><div style=\"float: right;margin-bottom: 2px;margin-top: 2px;margin-left: 10px;\">"

            out<< markResourceReadUnread(resource: resource)

            out << "</div><div style=\"float: right;margin-bottom: 2px;margin-top: 2px;\">View Post</div>"

            out<< "</td></tr>"

        }
    }

    def resourceViewOption = { attrs ->
        def resource = attrs.resource

        if(resource instanceof  LinkResource){
            out << g.link(target: "_blank", url: "${resource.url}") {"View full site"}
        }
        else if(resource instanceof DocumentResource){
            out << g.link(controller: "documentResource", action: "download",params:[resourcePath: resource.path,\
             resourceName:resource.title]) { "Download"}
        }
    }

    def markResourceReadUnread={ attrs ->

        def resource = attrs.resource

        if(!resourceService.isPostRead(session["user"],resource)) {
            out << g.link([controller: "readingItem", action: "markAsRead", params: [resource: resource.id, user: session["user"].id,\
             read:true]]){ "Mark as read" }
        }
        else
        {
            out << g.link([controller: "readingItem", action: "markAsRead", params: [resource: resource.id, user: session["user"].id,\
             read:false]]){ "Mark as unread"}
        }
    }


    def showUsersList ={ attrs ->

        List<User> userList = attrs.usersList

        userList.each { user ->
            out << "<tr><td><div style=\"float: left;padding: 5px;\">"
            out << g.img(uri: "${grailsApplication.config.user.photo.location.profile+user.userPhoto}",width:"80", height:"80")
            out << "</div><div style=\"padding: 5px;\">"
            out << g.link(controller: "user", action: "showUserProfile", params: [id: user.id]){ "${ user.firstName+" "+user.lastName}" }
            out << "</div><div style=\"font-size: x-small;\">@${user.firstName}</div><div style=\"float: left;margin: 5px;\">"
            out << "<a class=\"topicDetail\">Subscriptions <br>${user.topics.size()}</div><div style=\"float: left;margin: 5px;\"> Posts <br>"+
                    "${user.resources.size()} </div></td></tr>"
        }
    }
}
