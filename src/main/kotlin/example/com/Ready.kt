package example.com

import example.com.admin.ServerLogging
import example.com.commands.audio.OldCommands
import example.com.commands.audio.PlayCommand
import example.com.commands.informations.WhoisCommand
import example.com.commands.slash.JoinVC
import example.com.commands.slash.Ping


import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import example.com.utils.guild
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.OptionData

class Ready : ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        println(event.jda.selfUser.name)
        val guild = event.jda.getGuildById(guild!!)

        val youtubeUrl : OptionData = OptionData(OptionType.STRING, "track", "Track name or url", true)
        val whoisOption : OptionData = OptionData(OptionType.STRING, "domain", "Domain name", true)
        guild!!.updateCommands().addCommands(
            Commands.slash("ping", "replies with the ping of the bot to the discord api"),
            Commands.slash("setup-ticket", "setup ticket channel"),
            Commands.slash("join", "Joins in your current channel"),
            Commands.slash("play", "Play Command").addOptions(youtubeUrl),
            Commands.slash("whois", "Gets information about the given domain").addOptions(whoisOption)
        ).queue()

        event.jda.addEventListener(Ping())
       //  event.jda.addEventListener(SetupTicket())
        event.jda.addEventListener(ServerLogging)
        event.jda.addEventListener(JoinVC())
        event.jda.addEventListener(OldCommands())
        event.jda.addEventListener(PlayCommand())
        event.jda.addEventListener(WhoisCommand())
    }
}

