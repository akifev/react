import org.bson.Document

class Product(
    val name: String,
    var price: Double
) {
    constructor(doc: Document) : this(doc.getString("name"), doc.getDouble("price"))

    fun toString(currency: String): String {
        val alpha = when (currency.toLowerCase()) {
            "ru" -> 1.0
            "us" -> 80.0
            "eu" -> 90.0
            "kr" -> 12.0
            else -> 1.0
        }

        return "name: $name, price: ${price / alpha}"
    }
}

