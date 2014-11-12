package linksharing

class ResourceRating {

    Integer score

    static belongsTo = [resource:Resource,user:User]

    static constraints = {
            user unique: 'resource'
    }
}
