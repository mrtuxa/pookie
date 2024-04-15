package example.com.commands.slash

import dev.minn.jda.ktx.messages.EmbedBuilder
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color

class SetupTicket : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == "setup-ticket") {
            val channel = event.getOption("channel")!!.asString


            fun embedTicket(member: Member) {
                val roleEmbed: EmbedBuilder = EmbedBuilder()
                    .setTitle("Ticket System")
                    .setAuthor(event.jda.selfUser.name, "website())", event.jda.selfUser.avatarUrl)
                    // .setThumbnail(event.jda.selfUser.)
                    .setColor(Color.BLACK)
            }


        }


    }
}