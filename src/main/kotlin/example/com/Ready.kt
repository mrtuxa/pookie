package example.com

import example.com.admin.ServerLogging
import example.com.commands.slash.Ping
import example.com.commands.slash.SetupTicket

import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import example.com.utils.guild
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands

class Ready : ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        println(event.jda.selfUser.name)
        val guild = event.jda.getGuildById(guild!!)


        guild!!.updateCommands().addCommands(
            Commands.slash("ping", "replies with the ping of the bot to the discord api"),
            Commands.slash("setup-ticket", "setup ticket channel")
        ).queue()

        event.jda.addEventListener(Ping())
        event.jda.addEventListener(SetupTicket())
        event.jda.addEventListener(ServerLogging())

    }
}