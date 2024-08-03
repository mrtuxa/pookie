package example.com.commands.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import example.com.audioplayer.AudioPlayerSendHandler


class GuildMusicManager(manager: AudioPlayerManager) {
    val player: AudioPlayer = manager.createPlayer()
    val trackScheduler: TrackScheduler = TrackScheduler(player)

    init {
        player.addListener(trackScheduler)
    }

    val sendHandler: AudioPlayerSendHandler
        get() = AudioPlayerSendHandler(player)
}