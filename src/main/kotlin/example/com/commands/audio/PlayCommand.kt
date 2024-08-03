package example.com.commands.audio

import net.dv8tion.jda.api.entities.GuildVoiceState
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.net.URI
import java.net.URISyntaxException


class PlayCommand : ListenerAdapter() {
    private fun isUrl(url: String): Boolean {
        try {
            URI(url)
            return true
        } catch (e: URISyntaxException) {
            return false
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == "play") {
            event.deferReply().setEphemeral(false).queue()
            val channel = event.channel.asTextChannel()
            val self: Member = event.guild!!.selfMember
            val selfVoiceState: GuildVoiceState? = self.getVoiceState()


            val member: Member? = event.member
            val memberVoiceState: GuildVoiceState? = member?.getVoiceState()

            if (!memberVoiceState!!.inAudioChannel()) {
                event.hook.sendMessage("You have to be on voice for it to work").queue()
            }

            if (memberVoiceState.inAudioChannel()) {
                val audioManager = event.guild!!.audioManager
                val memberChannel: VoiceChannel = memberVoiceState.channel!!.asVoiceChannel()
                if (!selfVoiceState!!.inAudioChannel()) {
                    audioManager.openAudioConnection(memberChannel)
                    event.hook.sendMessage("Connecting to " + memberChannel.getAsMention()).complete()
                }
                val youtubeUrl = event.getOption("track")
                var trackUrl = youtubeUrl!!.asString
                if (!isUrl(trackUrl)) {
                    trackUrl = "ytsearch:$trackUrl"
                    PlayerManager.instance?.loadAndPlay(channel, trackUrl)
                } else {
                    PlayerManager.instance?.loadAndPlay(channel, trackUrl)
                }
            } else if (memberVoiceState.channel!!.asVoiceChannel() != selfVoiceState?.channel!!.asVoiceChannel()) {
                event.hook.sendMessage("You’re supposed to be on my voice channel for this to work").queue()
            }
        }
    }
}