package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SubscriptionController {
    def scaffold = true
}
