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

   def bootStrapService

    def init = { servletContext ->
        bootStrapService.init()
    }



    def destroy = {
    }
}
