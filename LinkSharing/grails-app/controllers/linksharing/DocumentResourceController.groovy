package linksharing

import org.springframework.web.multipart.commons.CommonsMultipartFile

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class DocumentResourceController {

    ResourceService resourceService

    def scaffold = true

    def create(){

        List<Topic> topicList = Subscription.findAllByUser(session["user"]).topic

        render(view:"create",model:["topicList":topicList])

    }

    @Transactional
    def save(DocumentResourceCommand documentResourceCommandInstance) {
        if (documentResourceCommandInstance == null) {
            notFound()
            return
        }

       /* if (documentResourceCommandInstance.hasErrors()) {
            respond documentResourceCommandInstance.errors, view:'create'
            return
        }*/

        User user = session["user"]

        DocumentResource documentResourceInstance = resourceService.
                getDocumentResourceFromCommand(documentResourceCommandInstance,user)

        if (documentResourceInstance.hasErrors()) {
            respond documentResourceCommandInstance.errors, view:'create'
            return
        }

        documentResourceInstance.save flush:true,failOnError: true
        resourceService.populateUnreadItems(documentResourceInstance)
        resourceService.markResourceReadUnread(documentResourceInstance.id,user.id,true)

        redirect(controller: "user",action: "dashboard")

    }

    def download(){

        def resourceName =params.resourceName
        String resourcePath =params.resourcePath

        File f = new File(grailsApplication.config.user.doc.location+resourcePath)
        resourceName +="."+resourcePath.tokenize(".").last()
//        resourceName += (resourcePath.split(".")[1])

        response.setContentType('APPLICATION/OCTET-STREAM')
        response.setHeader('Content-Disposition', "Attachment;Filename=\"${resourceName}\"")

        def outputStream = response.getOutputStream()
        outputStream << f.bytes
        outputStream.flush()
        outputStream.close()

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

class DocumentResourceCommand{

    String title
    String description
    CommonsMultipartFile document
    String topicId

    static constraints={
       // importFrom DocumentResource

        description widget: 'textarea',maxSize: 1024
        document nullable: false, blank:false
    }


}
