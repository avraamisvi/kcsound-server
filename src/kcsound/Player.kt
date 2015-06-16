package kcsound

import csnd6.Csound
import org.java_websocket.WebSocket
import java.lang.Runnable;

public class Player {

	var playing: Boolean = false;

	fun stop(conn: WebSocket) {
		playing = false;
	}

	fun play(conn: WebSocket, composition: Composition) {

		Thread(Runnable{
			val csound = Csound();

			playing = true;

		// Using SetOption() to configure Csound
			// Note: use only one commandline flag at a time
			csound.SetOption("-odac");

			val compiled = CompositionManager.compile(composition);

			// Compile the Csound Orchestra string
			csound.CompileOrc(compiled.orchestra);

			// Compile the Csound SCO String
			csound.ReadScore(compiled.score);

			// When compiling from strings, this call is necessary before doing
			// any performing
			csound.Start();

			// This call runs Csound to completion
		//        csound.Perform();

			while (csound.PerformKsmps() == 0 && conn.isOpen() && playing) {

				TimeLineServer.send(csound.GetScoreTime());

				if(conn.isClosed()) {
					break;
				}
			}

			println("play: $playing");

			// stops Csound
			csound.Stop();

		//		csound.Cleanup();
		}).start();

	}
}
