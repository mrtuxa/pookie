package example.com.commands.informations

import example.com.utils.DomainCheck
import example.com.utils.WhoisClient
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.net.MalformedURLException



class WhoisCommand : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == "whois") {
            // domain option
            val domain = event.getOption("domain")?.asString

            // web request to whois
            if (domain != null) {
                val test = WhoisClient().get(domain)
                event.deferReply(true).queue();
                test
                event.reply("```\n${test}\n```").queue()
            }
        }
    }
}