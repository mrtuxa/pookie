package example.com.audioplayer

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion
import java.util.*
import java.util.concurrent.LinkedBlockingQueue


class TrackManager(private val player: AudioPlayer) : AudioEventAdapter() {
    private val queue: Queue<AudioInfo?> = LinkedBlockingQueue()

    fun queue(track: AudioTrack?, author: Member?) {
        val info = AudioInfo(track!!, author!!)
        queue.add(info)

        if (player.playingTrack == null) {
            player.playTrack(track)
        }
    }

    override fun onTrackStart(player: AudioPlayer, track: AudioTrack) {
        val info = queue.element()
        val vChan: AudioChannelUnion? = info!!.getAuthor().voiceState!!.channel
        if (vChan == null) { // User has left all voice channels
            player.stopTrack()
        } else {
            info.getAuthor().guild.audioManager.openAudioConnection(vChan)
        }
    }

    override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
        val g: Guild = queue.poll()!!.getAuthor().guild
        if (queue.isEmpty()) {
            g.getAudioManager().closeAudioConnection()
        } else {
            player.playTrack(queue.element()!!.track)
        }
    }

    fun shuffleQueue() {
        val tQueue: List<AudioInfo?> = ArrayList(queuedTracks)
        val current = tQueue[0]
        //tQueue.removeAt(0)
        Collections.shuffle(tQueue)
        //tQueue.add(0, current)
        purgeQueue()
        queue.addAll(tQueue)
    }

    val queuedTracks: Set<AudioInfo?>
        get() = LinkedHashSet(queue)

    fun purgeQueue() {
        queue.clear()
    }

    fun remove(entry: AudioInfo?) {
        queue.remove(entry)
    }

    fun getTrackInfo(track: AudioTrack): AudioInfo? {
        return queue.stream().filter { audioInfo: AudioInfo? -> audioInfo!!.track == track }.findFirst().orElse(null)
    }
}