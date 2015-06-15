package kcsound

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/*
  This server sends only the current time status of a composition being played
*/
public object TimeLineServer {

    var client: WebSocket? = null;
    var server: LocalServer? = null;

  	fun startService(address: InetSocketAddress){
      val serv: LocalServer = LocalServer(address);
      this.server = serv;

      this.server?.run();
  	}

    public fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        println("TimeLineServer new connection to " + conn.getRemoteSocketAddress());
        this.client = conn;
    }

    public fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {
        println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    public fun onMessage(conn: WebSocket, message: String) {
    }

    public fun onError(conn: WebSocket, ex: Exception) {
        System.err.println("an error occured on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
    }

    fun send(current: Double) {
      this.client?.send("$current");
    }
}

public class LocalServer(address: InetSocketAddress)  : WebSocketServer(address) {


    public override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
      TimeLineServer.onOpen(conn, handshake)
    }

    public override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {
      TimeLineServer.onClose(conn, code, reason, remote)
    }

    public override fun onMessage(conn: WebSocket, message: String) {
      TimeLineServer.onMessage(conn, message)
    }

    public override fun onError(conn: WebSocket, ex: Exception) {
      TimeLineServer.onError(conn, ex)
    }
}
