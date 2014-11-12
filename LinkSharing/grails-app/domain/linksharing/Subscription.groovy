package linksharing



class Subscription {

    Seriousness seriousness
    Date dateCreated
    Date lastUpdated

    static belongsTo = [topic:Topic,user:User]

    static constraints = {
        topic unique: 'user'
        lastUpdated display:false
    }

}