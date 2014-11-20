package linksharing

class MailController {

    def mailingService

    def index(Topic topic) {

        List<Topic> topicList = Subscription.findAllByUser(session.user)?.topic

//        def topicId = params.topicId

        render(view: "index",model: [topicList:topicList,topicId:topic?.id])
    }

    def sendMail(Topic topic){

        String to = params.recipient
        String from = session.user?.email
        /*String topicId= params.topic

        Topic topic = Topic.findById(topicId.toLong())*/

        String subject = "Invitation to subscribe topic ${topic?.name}"

        String body = """
        Hi !

                ${session.user?.firstName}  has invited you to subscribe topic ${topic?.name}.

        Thanks."""

        mailingService.sendMail(to.tokenize(","),from,subject,body)

        redirect(controller: 'user', action: 'dashboard' )
    }


}
