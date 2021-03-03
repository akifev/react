import org.bson.Document

class User(
    val username: String,
    val currency: String
) {
    constructor(doc: Document) : this(doc.getString("username"), doc.getString("currency"))

    override fun toString(): String {
        return "username: $username, currency: $currency"
    }
}

