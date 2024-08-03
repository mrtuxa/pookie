package example.com.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent


interface ISlashCommand {
    fun execute(event: SlashCommandInteractionEvent?)
}