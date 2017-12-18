package trpo

import com.google.gson.*
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.util.concurrent.atomic.AtomicInteger

fun JsonObject.emplaceProperty(key: String, value: String) = this.also { addProperty(key, value) }
fun JsonObject.emplaceProperty(key: String, value: Int) = this.also { addProperty(key, value) }

val errorMessageRegex = Regex("[^:]+:(.*) at line (\\d+) column (\\d+) .*")

/**
 * Parse error message.
 *
 * @param errorMessage error message
 * @param defaults values to return if errors occured during parsing message
 * @return parsed message or defaults.
 */
fun findOrDefault(errorMessage: String?, vararg defaults: String): List<String> {
    if (errorMessage == null) return defaults.toList()
    val groups = errorMessageRegex.find(errorMessage) ?: return defaults.toList()
    val destructed = groups.destructured.toList()
    if (destructed.size != defaults.size) return defaults.toList()
    return destructed
}

/**
 * Validate json.
 *
 * @param jsonString json to validate
 * @return json object with input json or with error description.
 */
fun JsonParser.processJson(jsonString: String, requestId: Int) = try {
    parse(jsonString)
} catch (ex: JsonParseException) {
    val (message, line, column) = findOrDefault(ex.message, "Unknown error", "-1", "-1")
    JsonObject().emplaceProperty("errorCode", message.hashCode())
            .emplaceProperty("errorMessage", message)
            .emplaceProperty("errorPlace", "line $line column $column")
            .emplaceProperty("resource", "json string")
            .emplaceProperty("request-id", requestId)
}

fun main(args: Array<String>) {
    val parser = JsonParser()
    val builder = GsonBuilder().setPrettyPrinting().create()
    val server = HttpServer.create()
    val serverPort = (System.getenv("SERVER_PORT") ?: "7777").toInt()
    server.bind(InetSocketAddress(serverPort), 0)
    val requestIdGenerator = AtomicInteger(0)
    server.createContext("/") {
        val responseObject = parser.processJson(
                jsonString = it.requestBody.bufferedReader().readText(),
                requestId = requestIdGenerator.getAndIncrement()
        )
        val response = builder.toJson(responseObject) + "\n"
        it.sendResponseHeaders(200, response.length.toLong())
        it.responseBody.write(response.toByteArray())
        it.responseBody.close()
    }
    server.executor = null
    server.start()
}
