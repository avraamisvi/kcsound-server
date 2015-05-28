package kcsound

import csnd6.csnd6Constants;
import csnd6.csnd6;
import csnd6.Csound;
import org.java_websocket.WebSocket

public class KCSound(server: Server) {
	
	var composition: Composition? = null;
	var player: Player? = null;
	val server: Server = server;
	
	public fun startSystem() {
		var par = csnd6Constants.CSOUNDINIT_NO_ATEXIT or csnd6Constants.CSOUNDINIT_NO_SIGNAL_HANDLER;
        csnd6.csoundInitialize(par);
        player = Player();
		
		server.run();
	}
	
	fun compile(composition: Composition) {//TODO
//		return Composition("","");//TODO fazer
	}
	
	fun play(conn: WebSocket, composition: Composition) {	
		player?.play(conn, composition);
	}
	
	fun stop(conn: WebSocket) {	
		player?.stop(conn);
	}	
	
	fun process(conn: WebSocket, msg: Message) {
		
		when(msg.type) {
			MessageType.PLAY -> play(conn, (msg as PlayMessage).composition)
			MessageType.STOP -> stop(conn)
			else -> {
				println("unknown");
			}
		}
	}
	
}