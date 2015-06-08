package kcsound;

import kcsound.instruments.*;
import kcsound.composition.*;
import com.google.gson.*;

import kcsound.templates.*;

public class CompiledComposition {
  var orchestra: String?=null;
  var score: String?=null;
  var id: Int = -1;
}

public object CompositionManager {

  var compiledsId = 0;
  var orchestra: OrchestraTemplate = OrchestraTemplate();
  var score: ScoreTemplate?=null;

  public fun compile(composition: Composition): CompiledComposition {

    val compiled = CompiledComposition();

    this.createOrchestra(composition, compiled);
    this.createScore(composition, compiled);
    this.compiled.id = this.compiledsId;

    this.compiledsId++;

    return compiled;
  }

  fun createOrchestra(compo: Composition, compiled: CompiledComposition) {

    for(orchItem: Set<Map.Entry<String,JsonElement>> in compo.orchestra.instruments.entrySet()) {

      var json = orchItem.getValue() as JsonObject;
      val instrument = this.createInstrument(json);

      // Compilando instrumento
      instrument.compile(json);

      val instrumentBodyStr = instrument.body();
      val globalStr = instrument.globals();

      orchestra.addGlobal(globalStr);
      orchestra.addInstrument(instrumentBodyStr, json.get("_id").getAsInteger());
    }

    compiled.orchestra = orchestra.generate();
  }

  fun createScore(compo: Composition, compiled: CompiledComposition) {

    score.instruments = composition.orchestra.instruments;

    for(group: Group in compo.score.groups) {
      score.addGroup(group);
    }

    compiled.score = score.generate();
  }

  fun createInstrument(obj: JsonObject): Instrument {
      return InstrumentsManager.createInstrument(obj.get("name").getAsString());
  }
}
