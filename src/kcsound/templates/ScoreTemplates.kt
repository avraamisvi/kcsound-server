package kcsound.templates

import com.google.gson.*;
import kcsound.composition.*;

public class ScoreTemplate {

  var score: String="";
  var instruments: JsonObject?=null;

  public fun addGroup(group: Group) {
    for(entry: GroupEntry in group!!.entries!!.iterator()) {

      var entryStart: Double = entry.start!!;
      var entryDuration: Double = entry.duration!!;

      for(key: String in group!!.instruments!!.keySet()) {

        val id = group!!.instruments!!.get(key);
        val instr = this.instruments!!.get(id.toString()).getAsJsonObject();//group!!.instruments!!.get(key)

        for(pianoNoteEntry: Map.Entry<String, JsonElement> in instr.get("piano").getAsJsonObject().entrySet()) {

          val noteJson = pianoNoteEntry.getValue() as JsonObject;

          val note = noteJson.get("note").getAsString();
          var noteDuration = noteJson.get("duration").getAsDouble();
          val noteStart = entryStart + noteJson.get("start")!!.getAsDouble();

          noteDuration = (if (noteDuration > entryDuration) entryDuration else noteDuration)/1000//divided by 1000 to transform in seconds

          score += "i$id $noteStart $noteDuration\n";
        }
      }
    }
  }

  public fun generate(): String {
    return score;
  }
}
