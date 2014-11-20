package linksharing

import grails.transaction.Transactional

@Transactional
class BootStrapService {

    def topicService
    def resourceService

    List<User> userList=[]
    List<Topic> topicList=[]

    def init(){
        createUsers()
        createTopic()
        subscribeAll()
        createResources()
    }

   private def createUsers(){

        User user1= new User(firstName: "fname1",lastName: "lName1",password: "123456",\
         userName: "uName1",active: true,admin: true,email: "abc1@gmail.com",userPhoto: "123.png").save(failOnError: true)

        User user2= new User(firstName: "fname2",lastName: "lName2",password: "123456",\
         userName: "uName2",active: true,admin: false,email: "abc2@gmail.com" ,userPhoto: "123.png").save(failOnError: true)

        userList<<user1
        userList<<user2
    }

    private def createTopic(){

        userList.eachWithIndex{user,idx ->
            5.times {
                Topic topic = new Topic(name: "topic${it + 1}--${idx}", visibility: Visibility.PUBLIC, user: user)
                topic.save(flush: true,failOnError: true)
                topicService.subscribeCreator(topic)
                topicList<<topic
            }
        }
    }

    private def subscribeAll(){

        userList.each {User user ->
            topicList.each {Topic topic ->
                if(!topicService.isUserSubscribedToTopic(user,topic)){
                    topicService.subscribeUserToTopic(topic,user,Seriousness.SERIOUS)
                }
            }
        }
    }

    private def createResources(){

        topicList.eachWithIndex { topic,idx ->
            5.times {
                LinkResource linkResource=   new LinkResource(topic: topic,title: "LinkedResource${it}--${idx}",\
                  description: "desc", user: topic.user, url: "url ${it}").save(failOnError: true)
                resourceService.populateUnreadItems(linkResource)
                DocumentResource documentResource =   new DocumentResource(topic: topic,title: "DocResource${it}--${idx}",\
                  description: "desc", user: topic.user, path: "path ${it}").save(failOnError: true)
                resourceService.populateUnreadItems(documentResource)
            }
        }
    }
}
