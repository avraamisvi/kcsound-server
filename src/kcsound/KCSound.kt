package kcsound

import csnd6.csnd6Constants;
import csnd6.csnd6;
import csnd6.Csound;

public class KCSound(server: Server) {
	
	var composition: Composition? = null;
	var player: Player? = null;
	val server: Server = server;
	
	public fun startSystem() {
		var par = csnd6Constants.CSOUNDINIT_NO_ATEXIT or csnd6Constants.CSOUNDINIT_NO_SIGNAL_HANDLER;
        csnd6.csoundInitialize(par);
		
		server.run();
	}
	
	fun compile(composition: Composition): Composition {//TODO
		return Composition("","");//TODO fazer
	}
	
	fun play(composition: Composition): Player {	
		return Player(server, composition).run();
	}
	
	fun process(msg: Message) {
		when(msg.type) {
			MessageType.PLAY -> play((msg as PlayMessage).composition)
			else -> {
				println("unknown");
			}
		}
	}
	
}