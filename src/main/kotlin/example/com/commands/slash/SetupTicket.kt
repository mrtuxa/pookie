/*
package example.com.commands.slash

import dev.minn.jda.ktx.messages.EmbedBuilder
import dev.minn.jda.ktx.messages.send
import example.com.utils.domain
import example.com.utils.server_name
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color

class SetupTicket : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == "setup-ticket") {
            event.deferReply(true)
            val embed: EmbedBuilder = EmbedBuilder()
            embed.setTitle(server_name, domain)
            embed.setDescription("")
        }


    }
}
 */