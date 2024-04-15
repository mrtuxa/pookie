package example.com

import example.com.Main.jda
import example.com.plugins.*
import example.com.utils.token
import io.github.cdimascio.dotenv.Dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity



object Main {
    var dotenv = Dotenv.load()
    var jda : JDA? = null
}

fun main() {



    jda = JDABuilder.createDefault(token)
        .addEventListeners(Ready())
        .build()

    jda!!.presence.setPresence(OnlineStatus.ONLINE, Activity.playing("doing gay stuff :3"))

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}

fun Application.module() {
    configureRouting()
}
