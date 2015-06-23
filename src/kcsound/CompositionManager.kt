package kcsound;

import kcsound.instruments.*;
import kcsound.composition.*;
import com.google.gson.*;

import kcsound.templates.*;

public class CompiledComposition {
  var orchestra: String="";
  var score: String="";
  var id: Int = -1;
}

public object CompositionManager {

  var compiledsId = 0;
  var orchestra: OrchestraTemplate = OrchestraTemplate();
  var score: ScoreTemplate = ScoreTemplate();

  public fun compile(composition: Composition): CompiledComposition {

    this.orchestra = OrchestraTemplate();
    this.score = ScoreTemplate();

    var compiled = CompiledComposition();

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

      orchestra.addMode(json.get("id").getAsInt(), json.get("mode").getAsInt());
      orchestra.addGlobal(instrument.globals());
      orchestra.addInstrument(instrument.body(), json.get("id").getAsInt());
    }

    compiled.orchestra = this.orchestra.generate();
    println("createOrchestra:" + compiled.orchestra)
  }

  fun createScore(compo: Composition, compiled: CompiledComposition) {

    this.score.instruments = compo.orchestra!!.instruments;

    for(group: Map.Entry<String,Group> in compo.score!!.groups.entrySet()) {
      this.score.addGroup(group.getValue());
    }

    compiled.score = this.score!!.generate();

    println("createScore:" + compiled.score)
  }

  fun createInstrument(obj: JsonObject): Instrument {
      return InstrumentsManager.createInstrument(obj.get("name").getAsString());
  }
}
