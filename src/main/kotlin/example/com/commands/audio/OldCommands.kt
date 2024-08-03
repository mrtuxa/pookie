package example.com.commands.audio

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;
import java.net.URI;
import java.net.URISyntaxException;


class OldCommands : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message: Message = event.message
        val audioManager = event.guild.audioManager
        val voiceChannel: AudioChannel? = event.member!!.voiceState!!.channel
        val userVoiceState = event.member!!.voiceState
        val botVoiceState = event.guild.selfMember.voiceState

        if (message.getContentRaw().startsWith("!play")) {
            if (!userVoiceState!!.inAudioChannel()) {
                message.reply("You need to be in a voice channel!").queue()
            } else {
                if (!botVoiceState!!.inAudioChannel()) {
                    audioManager.openAudioConnection(voiceChannel)
                    audioManager.isSelfDeafened = true
                }

                var link: String = java.lang.String.join(" ", message.getContentRaw())
                if (!isUrl(link)) {
                    link = "ytsearch:$link"
                }
                PlayerManager.instance?.loadAndPlay(event.channel.asTextChannel(), link)
            }
        }

        if (message.getContentRaw().startsWith("!skip")) {
            checkAudioChannel(userVoiceState, botVoiceState, message)

            PlayerManager.instance?.getMusicManager(event.guild)?.trackScheduler?.nextTrack()
            message.reply("Success! Track skipped!").queue()
        }

        if (message.getContentRaw().startsWith("!stop")) {
            checkAudioChannel(userVoiceState, botVoiceState, message)

            audioManager.closeAudioConnection()
            PlayerManager.instance?.getMusicManager(event.guild)?.trackScheduler?.audioPlayer?.destroy()
            message.reply("Success! Music is no longer playing!").queue()
        }
    }

    fun checkAudioChannel(userVoiceState: GuildVoiceState?, botVoiceState: GuildVoiceState?, message: Message) {
        if (!userVoiceState!!.inAudioChannel()) {
            message.reply("You aren't in voice channel!").queue()
        }
        if (!botVoiceState!!.inAudioChannel()) {
            message.reply("I'm aren't in voice channel!").queue()
        }
    }

    fun isUrl(url: String?): Boolean {
        try {
            URI(url)
            return true
        } catch (e: URISyntaxException) {
            return false
        }
    }
}