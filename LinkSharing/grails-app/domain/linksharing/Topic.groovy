package linksharing


class Topic {

    String name
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static belongsTo = [user : User]

    static hasMany = [subscriptions:Subscription,resources:Resource]

    static constraints = {
        subscriptions display:false
        resources display:false
        dateCreated display:false
        lastUpdated display:false
        name unique: 'user'

    }
}