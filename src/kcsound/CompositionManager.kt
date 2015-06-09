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
    compiled.id = this.compiledsId;

    this.compiledsId++;

    return compiled;
  }

  fun createOrchestra(compo: Composition, compiled: CompiledComposition) {

    for(orchItem: Map.Entry<String,JsonElement> in compo.orchestra!!.instruments!!.entrySet()) {

      var json = orchItem.getValue() as JsonObject;
      val instrument = this.createInstrument(json);

      // Compilando instrumento
      instrument.compile(json);

      orchestra.addGlobal(instrument.globals());
      orchestra.addInstrument(instrument.body(), json.get("_id").getAsInt());
    }

    compiled.orchestra = orchestra.generate();
  }

  fun createScore(compo: Composition, compiled: CompiledComposition) {

    this.score!!.instruments = compo.orchestra!!.instruments;

    for(group: Group in compo.score!!.groups!!.iterator()) {
      this.score!!.addGroup(group);
    }

    compiled.score = this.score!!.generate();
  }

  fun createInstrument(obj: JsonObject): Instrument {
      return InstrumentsManager.createInstrument(obj.get("name").getAsString());
  }
}
