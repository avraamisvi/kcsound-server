package kcsound;

public class CompiledComposition {
  var orchestra: String?=null;
  var score: String?=null;
  var id: Int = -1;
}

public object CompositionManager {

  public fun compile(composition: Composition): CompiledComposition {
    return CompiledComposition();
  }

  fun createOrchestra(compo: Composition, compiled: CompiledComposition): String {
    val header =  """
                    sr = 44100
                    ksmps = 32
                    nchnls = 2
                    0dbfs  = 1
                  """;

    var orchestra = "";

    for(orchItem: Set<Map.Entry<String,JsonElement>> in compo.orchestra.entrySet()) {
      val instrument = this.createInstrument(orchItem.getValue() as JsonObject);
      val compiledStr = instrument.compile();
    }

  }

  fun createOrchestraInstrument(instr: Instrument, json: JsonObject): String {
    return "instr " +
  }

  fun createInstrument(obj: JsonObject): Instrument {
      InstrumentsManager.createInstrument(obj.get("name").getAsString());
      return instrument_;
  }
}
