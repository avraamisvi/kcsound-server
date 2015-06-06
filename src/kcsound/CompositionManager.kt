package kcsound;

import kcsound.instruments.*;
import kcsound.composition.*;
import com.google.gson.*;

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

    //TODO rever
    for(orchItem: Map.Entry<String, JsonElement> in compo.orchestra!!.instruments!!.entrySet()) {
      val instrument = this.createInstrument(orchItem.getValue() as JsonObject);
      val compiledStr = instrument.compile(orchItem.getValue() as JsonObject);
    }

    return "";
  }

  fun createOrchestraInstrument(instr: Instrument, json: JsonObject): String {
    return "instr ";
  }

  fun createInstrument(obj: JsonObject): Instrument {
      return InstrumentsManager.createInstrument(obj.get("name").getAsString());
  }
}
