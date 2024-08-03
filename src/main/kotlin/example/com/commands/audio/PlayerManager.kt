package example.com.commands.audio

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import java.util.function.Function


class PlayerManager {
    private val musicManagers: MutableMap<Long, GuildMusicManager>
    private val audioPlayerManager: AudioPlayerManager

    init {
        this.musicManagers = HashMap<Long, GuildMusicManager>()
        this.audioPlayerManager = DefaultAudioPlayerManager()

        AudioSourceManagers.registerRemoteSources(audioPlayerManager)
        AudioSourceManagers.registerLocalSource(audioPlayerManager)
    }

    fun getMusicManager(guild: Guild): GuildMusicManager {
        return musicManagers.computeIfAbsent(guild.getIdLong(),
            Function<Long, GuildMusicManager> { guildId: Long? ->
                val guildMusicManager: GuildMusicManager = GuildMusicManager(audioPlayerManager)
                guild.getAudioManager()                                           .setSendingHandler(guildMusicManager.sendHandler)
                guildMusicManager
            })
    }

    fun loadAndPlay(channel: TextChannel, url: String?) {
        val guildMusicManager: GuildMusicManager = getMusicManager(channel.guild)
        audioPlayerManager.loadItemOrdered(musicManagers, url, object : AudioLoadResultHandler {
            override fun trackLoaded(audioTrack: AudioTrack) {
                guildMusicManager.trackScheduler.queue(audioTrack)

                channel.sendMessage("Track " + audioTrack.info.title + ") by " + audioTrack.info.author + " added to queue!")
                    .queue()
            }

            override fun playlistLoaded(audioPlaylist: AudioPlaylist) {
                val tracks = audioPlaylist.tracks

                if (!tracks.isEmpty()) {
                    guildMusicManager.trackScheduler.queue(tracks[0])
                    channel.sendMessage("Track " + tracks[0].info.title + " by " + tracks[0].info.author + " added to queue!")
                        .queue()
                }
            }

            override fun noMatches() {
            }

            override fun loadFailed(e: FriendlyException) {
            }
        })
    }

    companion object {
        var instance: PlayerManager? = null
            get() {
                if (field == null) {
                    field = PlayerManager()
                }
                return field
            }
            private set
    }
}