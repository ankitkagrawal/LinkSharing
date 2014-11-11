package linksharing

class User {

    String email
    String userName
    String password
    String firstName
    String lastName
    //Byte[] photo
    Boolean admin=false
    Boolean active=true
    Date dateCreated
    Date lastUpdated

    static hasMany = [topics:Topic, resources:Resource]
    //,subscription:Subscription,readingItem:ReadingItem,resourceRating:ResourceRating

    static constraints = {
     //   photo nullable: true
        email email:true, unique:true, nullable:false, blank: false
        userName unique:true, nullable:false, blank: false
        password size: 5..12, password:true
        admin display:false
        active display:false
        topics display:false
        resources display:false
    }
}