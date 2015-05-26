package kcsound

import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

fun main(args : Array<String>) {
	
	val host: String  = "localhost";
    val port: Int  = 8887;

    val server: Server = Server(InetSocketAddress(host, port));
    server.startService();
}