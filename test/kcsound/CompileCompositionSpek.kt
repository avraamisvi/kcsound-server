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

class CompileCompositionSpek: Spek() {init {

    given("A received composition with one simple sine oscilator") {

        val composition = Composition();
        val orchestra = Orchestra();
        val score = Score();

        val instruments_json = Gson().fromJson("""
                            {
                              '1': {
                              notesId: 0,
                              id: 1,
                              name: 'simpleoscilator',
                              label: 'simpleoscilator',
                              type: 123,
                              mode: 1,
                              pattern:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                              octave:1,
                              piano:{
                                '1':{
                                  id:'1',
                                  note:'A0',
                                  start: 0,
                                  duration: 1000
                                }
                              },
                              notes = []
                            }
                          }
                         """, javaClass<JsonObject>());

        orchestra.instruments = Gson().fromJson(instruments_json, javaClass<JsonObject>());
        val group: Group = Group();
        group.name = "simpleoscilator";

        val instrs = HashMap<String, String>();
        instrs.put("1", "simpleoscilator");
        group.instruments = instrs;

        val entry = GroupEntry();
        entry.id = 1;
        entry.start = 300.0;
        entry.duration = 1000.0;

        group.entries = ArrayList();
        group.entries.add(entry)

        score.groups = HashMap();
        score.groups.put("1",group);

        composition.orchestra = orchestra;
        composition.score = score;

        on("compiling the composition") {
            val compiled = CompositionManager.compile(composition);

            val score_ideal = "i1 0.3 1.0 439.99999999764907\n";
            val regex = Regex("[ \\s]");
            val orchestra_ideal =
"""sr = 44100
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

            it("should result compile it to a csound file format string with a sine oscilator") {
                assertEquals(score_ideal, compiled.score)
                assertEquals(orchestra_ideal, compiled.orchestra.replace(regex, ""))
            }
        }
    }
}}
