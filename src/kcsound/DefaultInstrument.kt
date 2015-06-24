package kcsound

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import kcsound.instruments.*;

/*
  A default instrument
*/
class DefaultInstrument(definition: JsonObject) : Instrument {

  override val type: String = "DefaultInstrument";
  val definition = definition;
  var genGlobals = "";
  var genBody = "";

  /**
   * Generate body content.
   */
  override public fun body():String {
    return this.genBody;
  }

  /**
   * Generate globals to be added to the score.
   */
  override public fun  globals():String {
    return this.genGlobals;
  }

  /**
   * Compiles the instrument.
   */
   override public fun compile(data:JsonObject) {
     //TODO execute js code

     //val regex = Regex("<INSTR_ID>");

     for(el: JsonElement in this.definition.get("body").getAsJsonArray()) {
       this.genBody = "$genBody\n${el.getAsString()}";
     }

     this.genBody = this.genBody.replace("<INSTR_ID>", data.get("id").getAsString());

     for(el: JsonElement in this.definition.get("globals").getAsJsonArray()) {
       this.genGlobals = "$genGlobals\n${el.getAsString()}";
     }

     this.genGlobals = this.genGlobals.replace("<INSTR_ID>", data.get("id").getAsString());
   }
}
