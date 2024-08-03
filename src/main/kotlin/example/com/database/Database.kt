package example.com.database

import java.time.LocalDateTime


interface Database {
    fun createUser(guildId: String?, userId: String?)
    fun deleteUser(guildId: String?, userId: String?)
    fun setRank(guildId: String?, userId: String?, rank: Int)

    fun getRank(guildId: String?, userId: String?): Int
    fun getJoinTime(guildId: String?, userId: String?): LocalDateTime?
}
