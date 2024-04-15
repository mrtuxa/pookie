package example.com.commands.slash

import dev.minn.jda.ktx.messages.EmbedBuilder
import dev.minn.jda.ktx.messages.send
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color

class SetupTicket : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == "setup-ticket") {
           val guild = event.guild
           // val channel = guild!!.getTextChannelById(event.getOption("channel")!!.asLong)
            val channel = event.channel


            val ticketEmbed: EmbedBuilder = EmbedBuilder()
                    .setTitle("Ticket System")
                    .setAuthor(event.jda.selfUser.name, "https://github.com/mrtuxaa/pookie", event.jda.selfUser.avatarUrl)
                    // .setThumbnail(event.jda.selfUser.)
                    .setColor(Color.BLACK)

            println("test")

            channel.sendMessageEmbeds(ticketEmbed.build()).queue()
            event.reply("sending embed").queue()


        }


    }
}