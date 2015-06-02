package kcsound

import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress
import kotlin.platform.platformStatic

object Main {

	platformStatic public fun main(vararg args: String) {

			val host: String  = "localhost";
	    val port: Int  = 8887;

			println("Starting server on port:" + port);

	    val server: Server = Server(InetSocketAddress(host, port));
	    server.startService();
	}
}
