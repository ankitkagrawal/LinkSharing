package linksharing

import grails.plugin.mail.MailService
import grails.transaction.Transactional

@Transactional
class MailingService {

   MailService mailService

   def sendMail(List toList,String sender,String mailSubject, String mailBody){

       mailService.sendMail {
           to toList.toArray()
           from sender
           subject mailSubject
           body mailBody
       }

   }

}
