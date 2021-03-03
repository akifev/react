import io.reactivex.netty.protocol.http.server.HttpServer
import rx.Observable

class Server(private val database: Database) {
    fun startServer() {
        HttpServer.newServer(8080).start { req, resp ->
            val request = req.decodedPath.substring(1).split("/").toTypedArray()
            when (request[0]) {
                "register" -> {
                    val username = request[1]
                    val currency = request[2]

                    database.addUser(username, currency)

                    val response = Observable.just("Success! <$username> -> <$currency>")

                    return@start resp.writeString(response)
                }
                "users" -> {
                    val users = database.getUsers()
                    val response = users.map { "$it\n" }

                    return@start resp.writeString(response)
                }
                "add_product" -> {
                    val name = request[1]
                    val price = request[2].toDouble()

                    database.addProduct(name, price)

                    val response = Observable.just("Success! <$name> -> <$price>")

                    return@start resp.writeString(response)
                }
                "products" -> {
                    val username = request[1]
                    val products = database.getProducts()
                    val user = database.getUser(username)

                    val response = user.map { it.currency }
                        .flatMap { currency -> products.map { "${it.toString(currency)}\n" } }

                    return@start resp.writeString(response)
                }
                else -> {
                    val response = Observable.just("OOPS")

                    return@start resp.writeString(response)
                }
            }
        }.awaitShutdown()
    }
}