package kcsound

import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress
import kotlin.platform.platformStatic
import java.lang.Runnable

object Main {

	platformStatic public fun main(vararg args: String) {

			val host: String  = "localhost";
	    val port: Int  = 8887;//TODO cfg this

			println("Starting server on port:" + port);

			val threadTimeLine = Thread(Runnable {
				val portTm: Int  = 8886;//TODO cfg this
				println("Starting timeline server on port:" + portTm);
				TimeLineServer.startService(InetSocketAddress("localhost", portTm));
			}).start();

	    val server: Server = Server(InetSocketAddress(host, port));
			server.startService();
	}
}
