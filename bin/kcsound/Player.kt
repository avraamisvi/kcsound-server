package kcsound

import csnd6.Csound
import org.java_websocket.WebSocket

public class Player {
	
	val csound = Csound();
	var playing: Boolean = false;
	 
	fun stop(conn: WebSocket) {
		playing = false;
	}
	
	fun play(conn: WebSocket, composition: Composition) {
		
		playing = true;
		
		// Using SetOption() to configure Csound
        // Note: use only one commandline flag at a time 
        csound.SetOption("-odac");  

        // Compile the Csound Orchestra string
        csound.CompileOrc(composition.orchestra);     

        // Compile the Csound SCO String
        csound.ReadScore(composition.score);

        // When compiling from strings, this call is necessary before doing 
        // any performing
        csound.Start();

        // This call runs Csound to completion
//        csound.Perform();
		
        while (csound.PerformKsmps() == 0 && conn.isOpen() && playing) {
//        	println("time:" + csound.GetScoreTime());
			conn.send(""+csound.GetScoreTime());
			
			if(conn.isClosed()) {
				break;
			}
        }
		
        // stops Csound
        csound.Stop();	
		
		csound.Cleanup();	
	}
}