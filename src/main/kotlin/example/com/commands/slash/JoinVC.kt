package example.com.commands.slash

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class JoinVC : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == "join") {
            val member = event.member
            if (member != null && member.voiceState != null) {

                val channel = event.member!!.voiceState!!.channel!!.id
                val guild = event.guild
                val voiceChannel = guild!!.getVoiceChannelById(channel)
                val manager = guild.audioManager

                manager.openAudioConnection(voiceChannel)

                event.reply("owo :3")
            }
        }
    }
}