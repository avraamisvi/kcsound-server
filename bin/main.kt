import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

fun main(args : Array<String>) {
	val client = EmptyClient(URI("ws://localhost:8887"), Draft_10());
	client.connect();
}