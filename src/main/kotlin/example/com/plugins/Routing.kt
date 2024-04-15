package example.com.plugins

import example.com.Main
import example.com.Main.jda
import example.com.admin.ServerLogging
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Bot is online as ${jda!!.selfUser.name}")
        }
        get("/log") {
            val content = try {
                Files.readString(Paths.get("log*.log"))
            } catch (ex: IOException) {
                ex.printStackTrace()
                "Error reading log file."
            }

            call.respondText(content, ContentType.Text.Plain)
        }
    }
}
