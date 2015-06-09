package kcsound

import kotlin.test.assertEquals
import org.jetbrains.spek.api.*
import kotlin.test.assertTrue
import com.google.gson.Gson
import com.google.gson.JsonObject

class CompileCompositionSpek: Spek() {init {

    given("A received composition with one simples sine oscilator") {

/*      val compositionManager = CompositionManager();
        val composition = Composition();

        public class Composition {
        	var orchestra: Orchestra?=null;
        	var score: Score?=null;
        	var compiledId: Int?=-1;
        }*/
        val composition = Composition();
        composition.orchestra = Orchestra();
        composition.score = Score();

        val instrumentsStr = """
                            {
                              '1': {
                              notesId: 0,
                              id: 1,
                              name: 'simplesine',
                              label: 'simplesine',
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
                         """


        JsonObject groupStr = Gson().fromJson("""
          {
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
        """, JsonObject.class);

        composition.orchestra.instruments = Gson().fromJson(instrumentsStr);


        on("compiling the composition") {
          //  val value = taxRateCalculator.calculateRate(200, 10)
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
  var instruments: HashMap<String, Int>?=null;
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
