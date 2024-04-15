package example.com.handlers

import dev.minn.jda.ktx.events.CoroutineEventListener
import example.com.commands.slash.Ping
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class EventHandler : CoroutineEventListener {
    override suspend fun onEvent(event: GenericEvent) {
        val jda = event.jda

        when (event) {
            is SlashCommandInteractionEvent -> {
                Ping()
            }
        }
    }
}