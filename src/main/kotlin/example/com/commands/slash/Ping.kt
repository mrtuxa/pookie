package example.com.commands.slash

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter


class Ping : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == "ping") {
            val time = System.currentTimeMillis()

            event.reply("Pong!").setEphemeral(true) // reply or acknowledge
                .flatMap {
                    event.hook.editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time)
                } // then edit original
                .queue() // Queue both reply and edit
        }
    }
}