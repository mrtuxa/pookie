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

class ServerLogging : ListenerAdapter() {
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
        val channel = event.guild.getTextChannelById(Main.dotenv["LOGGING_CHANNEL"])// Replace with your channel ID
        val path: Path = Paths.get(("log-" + channel!!.name + LocalDate.now()).toString() + ".log")

        try {
            Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE).use { writer ->
                for (message in receivedMessages.reversed()) { // Reversed so that newer messages are at the bottom
                    val id = message.id
                    val author = message.author.name
                    val content = message.contentRaw
                    val link = message.jumpUrl // Get the URL of the message
                    writer.write("Time=${LocalDateTime.now()} ID=$id author=$author message=$content link=$link") // Write message in the desired format
                    writer.newLine() // Add a newline after each message
                }
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    fun getLog(): String {
        val logEntries = receivedMessages.reversed().joinToString("\n") { message ->
            val id = message.id
            val author = message.author.name
            val content = message.contentRaw
            val link = message.jumpUrl // Get the URL of the message
            "Time=${LocalDateTime.now()} ID=$id author=$author message=$content link=$link"
        }
        return logEntries
    }
}