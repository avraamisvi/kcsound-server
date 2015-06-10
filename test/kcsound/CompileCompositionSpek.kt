package kcsound

import kotlin.test.assertEquals
import org.jetbrains.spek.api.*
import kotlin.test.assertTrue
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.HashMap
import java.util.ArrayList
import kcsound.composition.*

class CompileCompositionSpek: Spek() {init {

    given("A received composition with one simples sine oscilator") {

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
                                'c440':{
                                  id:'1',
                                  note:'c440',
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

        score.groups = ArrayList();
        score.groups.add(group);

        composition.orchestra = orchestra;
        composition.score = score;

        on("compiling the composition") {
          //  val value = taxRateCalculator.calculateRate(200, 10)

            val compiled = CompositionManager.compile(composition);

            val score_ideal = "i1 300.0 1.0 440";
            val orchestra_ideal =
            """
            sr = 44100
            ksmps = 32
            nchnls = 2
            0dbfs  = 1

            giSine ftgen 0, 0, 2^10, 10, 1

            instr 1

                         krnd  randomh 40, 440, 1	; produce random values
                         ain   poscil3 .6, krnd, giSine
                         kline line    1, p3, 0    	; straight line
                         aL,aR pan2    ain, kline	; sent across image
                               outs    aL, aR

            endin
            """;


            println("orchestra:");
            print(compiled.orchestra);
            println("orchestra ideal: $orchestra_ideal");

            println("score:")
            println(compiled.score);
            println("score ideal: $score_ideal");

            it("should result compile it to a csound file format string with a sine oscilator") {
                assertEquals(orchestra_ideal, compiled.orchestra)
                assertEquals(score_ideal, compiled.score)
            }
        }
    }
}}

/*
public class GroupEntry {
  var id: Int?=null;
  var start: Double?=null;
  var duration: Double?=null;
}

public class Group {
  var id: Int?=null;
  var name: String?=null;
  var instruments: HashMap<Int, String>?=null;
  var entries: Array<GroupEntry>?=null;
}

public class Score {
  public var groups: Array<Group>?=null;
}

public class Orchestra {

  public var header: String?=null;
  public var instruments: JsonObject?=null;
}


*/



        /*val groups_json = Gson().fromJson("""
          {
              '1':{
              name: 'name',
              id: 1,
              instruments: {
                '1':'simplesine'
              },
              entriesId: 100,
              entries: {
                '1':{
                  id: 1,
                  start: 300,
                  duration: 1000
                }
              },
            }
          }
        """, JsonObject.class);*/
