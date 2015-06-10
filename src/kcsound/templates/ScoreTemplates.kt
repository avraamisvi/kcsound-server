package kcsound.templates

import com.google.gson.*;
import kcsound.composition.*;

public class ScoreTemplate {

  var score: String="";
  var instruments: JsonObject?=null;

  public fun addGroup(group: Group) {
    for(entry: GroupEntry in group!!.entries) {

      var entryStart: Double = entry.start!!;
      var entryDuration: Double = entry.duration!!;

      for(keyMap: Map.Entry<String,String> in group!!.instruments.entrySet()) {

        val id = keyMap.getKey();//group!!.instruments!!.get(key);
        val instr = this.instruments!!.get(id.toString()).getAsJsonObject();//group!!.instruments!!.get(key)

        for(pianoNoteEntry: Map.Entry<String, JsonElement> in instr.get("piano").getAsJsonObject().entrySet()) {

          val noteJson = pianoNoteEntry.getValue() as JsonObject;

          val note = noteJson.get("note").getAsString();
          var noteDuration = noteJson.get("duration").getAsDouble();
          val noteStart = entryStart + noteJson.get("start")!!.getAsDouble();

          noteDuration = (if (noteDuration > entryDuration) entryDuration else noteDuration)/1000//divided by 1000 to transform in seconds

          val translatedNote = this.translateNote(note);
          score += "i$id $noteStart $noteDuration $translatedNote\n";
        }
      }


    }
  }

  public fun generate(): String {
    return score;
  }

  fun translateNote(note: String): String{
    return "440";
  }
}
