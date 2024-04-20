package example.com.utils

import example.com.Main

import net.dv8tion.jda.api.entities.Guild

val prefix = Main.dotenv["PREFIX"]
val botchannel : String? = Main.dotenv["BOT_CHANNEL"].toString()
val adminbotchannel = Main.dotenv["ADMIN_BOT_CHANNEL"]
val token = Main.dotenv["TOKEN"]
val guild : String? = Main.dotenv["GUILD"]
val github = "https://github.com/mrtuxa/pookie"
val bot_status = Main.dotenv["BOT_STATUS"]