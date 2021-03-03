import com.mongodb.client.model.Filters.eq
import com.mongodb.rx.client.MongoClients
import org.bson.Document
import rx.Observable

class Database {
    private val client = MongoClients.create()
    private val database = client.getDatabase("test")

    fun addUser(username: String, currency: String) {
        val user = Document("_id", username).append("username", username).append("currency", currency)
        val users = database.getCollection("users")
        users.insertOne(user).subscribe()
    }

    fun getUsers(): Observable<User> {
        val users = database.getCollection("users")
        return users.find().toObservable().map { x -> User(x) }
    }

    fun getUser(username: String): Observable<User> {
        val users = database.getCollection("users")
        return users.find(eq("_id", username)).toObservable().map { x -> User(x) }
    }

    fun addProduct(name: String, price: Double) {
        val product = Document("name", name).append("price", price)
        val products = database.getCollection("products")
        products.insertOne(product).subscribe()
    }

    fun getProducts(): Observable<Product> {
        val products = database.getCollection("products")
        return products.find().toObservable().map { x -> Product(x) }
    }
}
