import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson
import kcsound.PlayMessage
import kcsound.Composition

public class EmptyClient(serverUri:URI, draft:Draft): WebSocketClient(serverUri, draft) {

//    constructor(serverURI: URI):super(serverURI) {
//        
//    }

    public override fun onOpen(handshakedata: ServerHandshake) {
    	val gson = Gson();
		var msg = PlayMessage(Composition("""
				sr = 44100
                ksmps = 32
                nchnls = 2
                0dbfs = 1
                instr 1
                aout vco2 0.5, 440
                outs aout, aout
                endin			
			""", "i1 0 1"));
		
        this.send(gson.toJson(msg));
    }

    public override fun onClose(code:Int, reason: String, remote: Boolean) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    public override fun onMessage(message: String) {
        System.out.println("received message: " + message);
    }

    public override fun onError(ex: Exception) {
        System.err.println("an error occured:" + ex);
    }

}

fun main(args : Array<String>) {
	val client = EmptyClient(URI("ws://localhost:8887"), Draft_10());
	client.connect();
}