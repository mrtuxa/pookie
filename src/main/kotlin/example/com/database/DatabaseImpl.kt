package example.com.database

import example.com.Main
import io.github.cdimascio.dotenv.Dotenv
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;


class DatabaseImpl private constructor() : Database {
    private var connection: Connection? = null
    private var createUserStatement: PreparedStatement? = null

    init {
        try {
            connection = DriverManager.getConnection(
                "jdbc:sqlite:nachhilfe.db"
            )
            connection?.createStatement()?.execute(
                "CREATE TABLE IF NOT EXISTS `members` (" +
                        "`guild_id` VARCHAR NOT NULL," +
                        "`member_id` VARCHAR NOT NULL," +
                        "`member_rank` INT DEFAULT 0," +
                        "`member_joined` DATETIME NOT NULL DEFAULT 'CURRENT_TIMESTAMP()'," +
                        "`member_activated` BOOLEAN DEFAULT true," +
                        "PRIMARY KEY (`guild_id`,`member_id`));"
            )
            createUserStatement =
                connection?.prepareStatement("INSERT INTO members (\"guild_id\", \"member_id\") VALUES (?, ?)")
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun createUser(guildId: String?, userId: String?) {
        try {
            createUserStatement!!.clearParameters()
            createUserStatement!!.setString(1, guildId)
            createUserStatement!!.setString(2, userId)
            createUserStatement!!.execute()
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun deleteUser(guildId: String?, userId: String?) {
        try {
            connection?.createStatement()
                ?.execute("UPDATE members SET member_activated = false WHERE guild_id = \"$guildId\" AND user_id = \"$userId\";")
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun setRank(guildId: String?, userId: String?, rank: Int) {
    }

    override fun getRank(guildId: String?, userId: String?): Int {
        return 0
    }

    override fun getJoinTime(guildId: String?, userId: String?): LocalDateTime? {
        return null
    }

    companion object {
        val dotenv: Dotenv = Main.dotenv
        private var databaseImpl: DatabaseImpl? = null
        val database: Database?
            get() {
                if (databaseImpl == null) {
                    databaseImpl = DatabaseImpl()
                }
                return databaseImpl
            }
    }
}
