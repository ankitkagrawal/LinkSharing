package linksharing

abstract class Resource {

    String title
    String description

    //User createdBy
//   Topic topic
    Date dateCreated
    Date lastUpdated

    static belongsTo = [topic:Topic,user:User]

    static hasMany = [readingItems:ReadingItem,ratingItems:ResourceRating]

    static constraints = {
        readingItems display:false
        ratingItems display:false
        //user display:false
        description widget: 'textarea',maxSize: 1024
        dateCreated display:false
        lastUpdated display:false
        title unique: 'topic'

    }
}
