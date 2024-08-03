package example.com.utils

import example.com.Main

import net.dv8tion.jda.api.entities.Guild


val token = Main.dotenv["TOKEN"]
val guild : String? = Main.dotenv["GUILD"]
val server_name = "Blåhaj Paradise | Queer Safe Space"

class domains {
    class nix2twink {
        val default = "nix2twink.gay"
    }
    class community {
        val blahaj_paradise = "blahaj.${domains.nix2twink().default}"
    };
}

val server_domain = domains.community().blahaj_paradise
