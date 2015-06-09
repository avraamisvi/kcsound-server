package kcsound

import kotlin.test.assertEquals
import org.jetbrains.spek.api.*
import kotlin.test.assertTrue
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.HashMap
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

        val instrs = HashMap<Int, String>();
        instrs.put(1, "simpleoscilator");
        group.instruments = instrs;

        val entry = GroupEntry();
        entry.id = 1;
        entry.start = 300.0;
        entry.duration = 1000.0;

        group.entries = array(entry);

        score.groups = array(group);

        composition.orchestra = orchestra;
        composition.score = score;

        on("compiling the composition") {
          //  val value = taxRateCalculator.calculateRate(200, 10)

            val compiled = CompositionManager.compile(composition);

            println(compiled.orchestra);
            println(compiled.score);

            it("should result compile it to a csound file format string with a sine oscilator") {
                assertEquals(300, 300)
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
