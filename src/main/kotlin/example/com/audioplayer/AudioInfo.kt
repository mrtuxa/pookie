package example.com.audioplayer


import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User

class AudioInfo internal constructor(val track: AudioTrack, author: Member) {
    private val skips: MutableSet<String> = HashSet()
    private val author: Member = author

    fun getSkips(): Int {
        return skips.size
    }

    fun addSkip(u: User) {
        skips.add(u.id)
    }

    fun hasVoted(u: User): Boolean {
        return skips.contains(u.id)
    }

    fun getAuthor(): Member {
        return author
    }
}