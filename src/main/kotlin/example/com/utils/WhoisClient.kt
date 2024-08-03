package example.com.utils

import java.net.Socket
import java.io.BufferedReader
import java.io.InputStreamReader

class WhoisClient {
    companion object {
        const val DEFAULT_HOST = "whois.iana.org"
        const val DEFAULT_PORT = 43
    }
    fun get(domain: String): String {
        val socket = Socket(DEFAULT_HOST, DEFAULT_PORT)
        val writer = socket.getOutputStream().bufferedWriter()
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

        writer.write("$domain\r\n")
        writer.flush()

        var response = ""
        reader.lines().forEach { line -> {
            response += line + "\n"
        }}
        socket.close()
        return response

    }
}
