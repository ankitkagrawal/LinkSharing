package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class LinkResourceController {

    ResourceService resourceService

    def scaffold = true


    def create(){

        List<Topic> topicList = Subscription.findAllByUser(session["user"]).topic

        render(view:"create",model:["topicList":topicList])
    }

    @Transactional
    def save(LinkResourceCommand linkResourceCommandInstance) {
        if (linkResourceCommandInstance == null) {
            notFound()
            return
        }

        if (linkResourceCommandInstance.hasErrors()) {
            respond linkResourceCommandInstance.errors, view:'create'
            return
        }

        User user = session["user"]
        LinkResource linkResourceInstance = new LinkResource()
        linkResourceInstance.properties = linkResourceCommandInstance.properties
        linkResourceInstance.topic=Topic.findById(linkResourceCommandInstance.topicId.toLong())
        linkResourceInstance.user=user


        if (linkResourceInstance.hasErrors()) {
            respond linkResourceInstance.errors, view:'create'
            return
        }

        linkResourceInstance.save flush:true,failOnError: true
        resourceService.populateUnreadItems(linkResourceInstance)
        resourceService.markResourceReadUnread(linkResourceInstance.id,user.id,true)

        redirect(controller: "user",action: "dashboard")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

class LinkResourceCommand {

    String title
    String description
    String url
    String topicId

    static constraints={
        description widget: 'textarea',maxSize: 1024
        url validator: { val ->
            if(val.size()<8 || !val.substring(0,7).equalsIgnoreCase("http://")){
                return "invalid.url"
            }

        }
    }

}
