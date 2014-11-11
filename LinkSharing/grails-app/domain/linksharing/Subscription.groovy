package linksharing



class Subscription {

    Seriousness seriousness
    Date dateCreated
    Date lastUpdated

    Boolean active=true

    static belongsTo = [topic:Topic,user:User]

    static constraints = {
        topic unique: 'user'
        active display:false
        lastUpdated display:false
    }

}