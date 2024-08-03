package example.com.systems

import example.com.utils.server_domain
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.buttons.Button
import java.awt.Color
import java.security.Permissions
import java.text.SimpleDateFormat
import java.util.*
import java.util.EnumSet
import java.util.concurrent.CompletableFuture
import kotlin.math.floor


class TicketSystem : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if(event.name == "setup-ticket") {
            val embed = EmbedBuilder()

            embed.setColor(Color.BLUE)
            embed.setTitle("Ticket System | QNS", server_domain)
            embed.setDescription("click on the button to create a ticket")
            embed.setFooter("Ticket System | QNS", event.jda.selfUser.avatarUrl)


            event.replyEmbeds(embed.build()).addComponents(button()).queue()
        }
    }

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        if(event.id == "openTicket") {
            val guild = event.guild;
            val roles: String = java.lang.String.valueOf(event.member?.roles)

            if(!roles.contains("Tickets")) {
                val min = 1000;
                val max = 99999;

                val random_int =
                    floor(Math.random() * (max - min + 1 + min)).toInt()
                val format = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
                val date: Date = Date()

                val user = event.member?.effectiveName

                val embed = EmbedBuilder()
                embed.setColor(Color.GREEN)
                embed.setTitle("$user's Ticket")
                embed.setDescription("A team member will take care of your ticket, please have a moment of patience.")
                embed.setDescription("date: ${format.format(date)}")

                val category = guild?.getCategoryById(1268916620118462536)


                guild?.createTextChannel("ticket-$user-$random_int", category)
            }

        }
    }

    private fun button(): ActionRow {
        return ActionRow.of(Button.primary("openTicket", "create Ticket"))
    }
}