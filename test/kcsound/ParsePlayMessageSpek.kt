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
              header:"",
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
                      duration: 3000
                    }
                  },
                  notes = []
                }
              }
            },

            score:{
              groups:{

                "1":{
                  id:"1",
                  name:"abacate",

                  instruments:{
                    "1":"simpleoscilator"
                  },
                  entries: [
                      {
                        id: "1",
                        start: 300,
                        duration: 3000
                      },
                      {
                        id: "2",
                        start: 3300,
                        duration: 3000
                      },
                      {
                        id: "3",
                        start: 4300,
                        duration: 3000
                      }
                  ]
                }

              }
            }

          }
        }
        """;

        on("parsing the message") {

            var par = csnd6Constants.CSOUNDINIT_NO_ATEXIT or csnd6Constants.CSOUNDINIT_NO_SIGNAL_HANDLER;
            csnd6.csoundInitialize(par);

            it("it should play a sound") {

                val regex = Regex("[ \\s]");

                val processMessage = ProcessMessage();
                val msg = processMessage.process(message);
                play((msg as PlayMessage).composition);

                val compiled = CompositionManager.compile((msg as PlayMessage).composition);
                val orch = """sr = 44100
                ksmps = 32
                nchnls = 2
                0dbfs  = 1

                seed 0
                gisine ftgen 0, 0, 2^10, 10, 1

                instr 1

                  ipeak random 0, 1 		;where is the envelope peak
                  asig  poscil .8, p4, gisine
                  aenv  transeg 0, p3*ipeak, 6, 1, p3-p3*ipeak, -6, 0
                  aL,aR pan2 asig*aenv, ipeak	;pan according to random value
                        outs aL, aR

                endin
                """.replace(regex, "");

                val scor = """
                i1 0.3 3.0 439.99999999764907
                i1 3.3 3.0 439.99999999764907
                i1 4.3 3.0 439.99999999764907
                """.replace(regex, "");

                assertEquals("$orch$scor", "${compiled.orchestra}${compiled.score}".replace(regex, ""));
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

    csound.CompileOrc(compiled.orchestra);
    csound.ReadScore(compiled.score);

    // When compiling from strings, this call is necessary before doing
    // any performing
    csound.Start();

    // This call runs Csound to completion
    csound.Perform();

    // stops Csound
    csound.Stop();
}
