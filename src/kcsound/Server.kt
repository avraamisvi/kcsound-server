package kcsound

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class Server(address: InetSocketAddress)  : WebSocketServer(address) {

	var kcsound = KCSound(this);

	fun startService(){
		kcsound.startSystem();
	}

    public override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        println("new connection to " + conn.getRemoteSocketAddress());
    }

    public override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {
        println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    public override fun onMessage(conn: WebSocket, message: String) {
        System.out.println("received message from " + conn.getRemoteSocketAddress() + ": " + message);

		val processMessage = ProcessMessage();

		kcsound.process(conn, processMessage.process(message));
    }

    public override fun onError(conn: WebSocket, ex: Exception) {
        System.err.println("an error occured on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
				ex.printStackTrace();
    }
}
