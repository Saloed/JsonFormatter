package trpo
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress


fun main(args: Array<String>) {
    val parser = JsonParser()
    val builder = GsonBuilder().setPrettyPrinting().create()
    val server = HttpServer.create()
    server.bind(InetSocketAddress(80), 0)
    server.createContext("/") {
        val response = try {
            val string = it.requestBody.bufferedReader().readText()
            builder.toJson(parser.parse(string)) + "\n"
        } catch (ex: Exception) {
            ex.message + "\n"
        }
        it.sendResponseHeaders(200, response.length.toLong())
        it.responseBody.write(response.toByteArray())
        it.responseBody.close()
    }
    server.executor = null
    server.start()
}
