package example.com.utils

import example.com.dotenv
import net.dv8tion.jda.api.entities.Guild

val prefix = dotenv["PREFIX"]
val botchannel : String? = dotenv["BOT_CHANNEL"].toString()
val adminbotchannel = dotenv["ADMIN_BOT_CHANNEL"]
val token = dotenv["TOKEN"]
val guild : String? = dotenv["GUILD"]