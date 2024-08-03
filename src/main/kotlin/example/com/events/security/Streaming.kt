package example.com.events.security

import example.com.utils.server_name
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceStreamEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.concurrent.TimeUnit


class Streaming : ListenerAdapter() {
    override fun onGuildVoiceStream(event: GuildVoiceStreamEvent) {
        if(event.member.voiceState!!.isStream) {
            val streaming = EmbedBuilder()
            streaming.setTitle(server_name);
            streaming.setDescription("This is the Security System for the server.");
            streaming.addField("\uD83D\uDCCD IMPORTANT INFORMATION", "Please do not leak any informations about you or any other peson", false);
            streaming.setColor(0x00ff00);
            streaming.setThumbnail(event.jda.selfUser.avatarUrl);
            streaming.setFooter(server_name, event.jda.selfUser.avatarUrl);


            // send private message to user
            event.member.user.openPrivateChannel().queue { privateChannel ->
                privateChannel.sendMessageEmbeds(streaming.build()).queue { message ->
                    message.delete().queueAfter(120, TimeUnit.SECONDS)
                }
            }
        }
    }
}