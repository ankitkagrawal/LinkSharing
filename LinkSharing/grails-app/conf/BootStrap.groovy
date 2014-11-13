import linksharing.DocumentResource
import linksharing.LinkResource
import linksharing.Resource
import linksharing.Seriousness
import linksharing.Topic
import linksharing.TopicService
import linksharing.User
import linksharing.Visibility

class BootStrap {

    //     select topic_id,count(*) as total from subscription group by topic_id order by total desc limit 5;
    //  select topic_id, count(*) as total from resource group by topic_id order by total desc limit 5;

/*
delete from reading_item where resource_id in(1,2,3,4,11,12,13,21,22,31,32,41);

delete from resource where id in(1,2,3,4,11,12,13,21,22,31,32,41);

delete from reading_item where resource_id>50;

delete from resource where id>50;
*/

    def topicService
    def resourceService

    List<User> userList=[]
    List<Topic> topicList=[]

    def init = { servletContext ->

        createUsers()
         createTopic()
        subscribeAll()
        createResources()
       // topicService.trendingTopicList()
    }

    def createUsers(){

        User user1= new User(firstName: "fname1",lastName: "lName1",password: "123456",\
         userName: "uName1",active: true,admin: true,email: "abc1@gmail.com",userPhoto: "123.png").save(failOnError: true)

       User user2= new User(firstName: "fname2",lastName: "lName2",password: "123456",\
         userName: "uName2",active: true,admin: false,email: "abc2@gmail.com" ,userPhoto: "123.png").save(failOnError: true)

        userList<<user1
        userList<<user2
    }

    def createTopic(){

        userList.each{user ->
            5.times {
                Topic topic = new Topic(name: "topic${it + 1}", visibility: Visibility.PUBLIC, user: user)
                        topic.save(flush: true,failOnError: true)
                        topicService.subscribeCreator(topic)
                topicList<<topic
            }
        }
    }

    def subscribeAll(){

        userList.each {User user ->
            topicList.each {Topic topic ->
                if(!topicService.isUserSubscribedToTopic(user,topic)){
                    topicService.subscribeUserToTopic(topic,user,Seriousness.SERIOUS)
                }
            }
        }
    }

    def createResources(){

        topicList.each { topic ->
            5.times {
                LinkResource linkResource=   new LinkResource(topic: topic,title: "LinkedResource${it}",\
                  description: "desc", user: topic.user, url: "url ${it}").save(failOnError: true)
                resourceService.populateUnreadItems(linkResource)
                DocumentResource documentResource =   new DocumentResource(topic: topic,title: "DocResource${it}",\
                  description: "desc", user: topic.user, path: "path ${it}").save(failOnError: true)
                resourceService.populateUnreadItems(documentResource)
            }
        }
    }

    def destroy = {
    }
}
