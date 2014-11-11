import linksharing.DocumentResource
import linksharing.LinkResource
import linksharing.Resource
import linksharing.Topic
import linksharing.TopicService
import linksharing.User
import linksharing.Visibility

class BootStrap {

    def topicService

    def init = { servletContext ->

        List<User> userList = createUsers()
        List<Topic> topicList = createTopic(userList)
        createResources(topicList)

    }

    List<User> createUsers(){

        User user1= new User(firstName: "fname1",lastName: "lName1",password: "123456",\
         userName: "uName1",active: true,admin: true,email: "abc1@gmail.com" ).save()

       User user2= new User(firstName: "fname2",lastName: "lName2",password: "123456",\
         userName: "uName2",active: true,admin: false,email: "abc2@gmail.com" ).save()

       List<User> userList =[]
        userList<<user1
        userList<<user2
        return userList

    }

    List<Topic> createTopic(List<User> userList){

        List<Topic> topicList =[]
        userList.each{user ->
            5.times {
                Topic topic = new Topic(name: "topic${it + 1}", visibility: Visibility.PUBLIC, user: user)
                        topic.save(flush: true,failOnError: true)
                        topicService.subscribeCreator(topic)
                topicList<<topic
            }
        }
        return topicList
    }

    def createResources(List<Topic> topicList){

        topicList.each { topic ->
            5.times {
                 new LinkResource(topic: topic,title: "LinkedResource${it}",\
                  description: "desc", user: topic.user, url: "url ${it}").save(failOnError: true)
                 new DocumentResource(topic: topic,title: "DocResource${it}",\
                  description: "desc", user: topic.user, path: "path ${it}").save(failOnError: true)
            }
        }
    }

    def destroy = {
    }
}
