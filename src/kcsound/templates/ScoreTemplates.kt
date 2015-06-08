package kcsound.templates

import com.google.gson.JsonObject;

public class ScoreTemplate {

  var score: String="";
  var instruments: JsonObject?=null;

  public fun addGlobal(group: Group) {
    for(entry: GroupEntry in group.entries) {

      var entryStart = entry.start;
      var entryDuration = entry.duration;

      for(key: String in entry.instruments.keys()) {

        val id = entry.instruments.get(key);
        val instr = instruments.get(entry.instruments.get(key))

        for(pianoNoteEntry: Set<Map.Entry<String, JsonElement>> in instr.get("piano").getAsJsonObject().entrySet()) {

          val noteJson = pianoNoteEntry.getValue() as JsonObject;

          val note = noteJson.get("note").getAsString();
          var noteDuration = noteJson.get("note").getAsDouble();
          val noteStart = entryStart + noteJson.get("start").getAsDouble();

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
