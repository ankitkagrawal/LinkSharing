package linksharing


class Topic {

    String name
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static belongsTo = [user : User]

    static hasMany = [subscripstions:Subscription,resources:Resource]

    static constraints = {
        subscripstions display:false
        resources display:false
        dateCreated display:false
        lastUpdated display:false
        name unique: 'user'

    }
}