package example.com.plugins

import example.com.Main
import example.com.Main.jda
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Bot is online as ${jda!!.selfUser.name}")
        }
    }
}
