package kcsound

import csnd6.Csound

public class Player(server: Server, composition: Composition) {
	
	val csound = Csound();
	val composition = composition;
	val server: Server = server;
	
	fun run(): Player {
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
        csound.Perform();
		
		return this;
	}
}