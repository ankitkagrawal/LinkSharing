package linksharing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResourceRatingController {
    def scaffold = true
}
