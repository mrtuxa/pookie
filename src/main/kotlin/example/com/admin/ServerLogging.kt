package example.com.admin

import example.com.Main
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageHistory
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.LocalDate
import java.time.LocalDateTime

object ServerLogging : ListenerAdapter() {
    private val receivedMessages: MutableList<Message> = mutableListOf()

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val logging = true

        if (logging) {
            val message = event.message
            receivedMessages.add(message)
            logMessages(event)
        }
    }

    private fun logMessages(event: MessageReceivedEvent) {
        val channel = event.guild.getTextChannelById(1229475594899558410L)// Replace with your channel ID
        val path: Path = Paths.get(("log-" + channel!!.name + LocalDate.now()).toString() + ".log")
    }

    fun getLog(): String {
        val logEntries = receivedMessages.reversed().joinToString("\n") { message ->
            val id = message.id
            val author = message.author.name
            val content = message.contentRaw
            val created = message.timeCreated
            val link = message.jumpUrl // Get the URL of the message
            "Time=${created} ID=$id author=$author message=$content link=$link"
        }
        return logEntries
    }
}