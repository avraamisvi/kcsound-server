package kcsound

import kotlin.test.assertEquals
import org.jetbrains.spek.api.*
import kotlin.test.assertTrue
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.HashMap
import java.util.ArrayList
import kcsound.composition.*
import kotlin.text.Regex
import csnd6.csnd6Constants;
import csnd6.csnd6;
import csnd6.Csound;

class ParsePlayMessageSpek: Spek() {init {

    given("A play message with a composition with one simple sine oscilator") {

        val message = """
        {
          type: 'PLAY',
          composition:{

            orchestra:{
              header:'',
              instruments: {
                  "1": {
                  notesId: 0,
                  id: "1",
                  name: "simpleoscilator",
                  label: "simpleoscilator",
                  type: 123,
                  mode: 1,
                  pattern:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                  octave:1,
                  piano:{
                    "1":{
                      id:"1",
                      note:"A0",
                      start: 0,
                      duration: 1000
                    }
                  },
                  notes = []
                }
              }
            },

            score:{
              "1":{
                id:"1",
                name: "abacate",
                instruments: {
                  "1":"simpleoscilator"
                },
                entries: [
                    {
                      id: "1",
                      start: 300,
                      duration: 1000
                    }
                ]
              }
            },

          	compiledId:-1
          }
        }
        """;

        on("parsing the message") {

            var par = csnd6Constants.CSOUNDINIT_NO_ATEXIT or csnd6Constants.CSOUNDINIT_NO_SIGNAL_HANDLER;
            csnd6.csoundInitialize(par);

            it("it should play a sound") {

                val processMessage = ProcessMessage();
                val msg = processMessage.process(message);
                //play((msg as PlayMessage).composition);

                val compiled = CompositionManager.compile((msg as PlayMessage).composition);
                println(compiled.orchestra);
                println(compiled.score);

                assertEquals(true, false)
            }
        }
    }
}}

fun play(composition: Composition) {
    val csound = Csound();

  // Using SetOption() to configure Csound
    // Note: use only one commandline flag at a time
    csound.SetOption("-odac");

    val compiled = CompositionManager.compile(composition);

    // Compile the Csound Orchestra string
    csound.CompileOrc(compiled.orchestra);
    println(compiled.orchestra);

    // Compile the Csound SCO String
    csound.ReadScore(compiled.score);
    println(compiled.score);

    // When compiling from strings, this call is necessary before doing
    // any performing
    csound.Start();

    // This call runs Csound to completion
    csound.Perform();

    // stops Csound
    csound.Stop();
}
