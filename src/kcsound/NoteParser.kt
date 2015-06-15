package kcsound

import java.util.HashMap

/*
  Parses notes to its frequencies.
*/
public object NoteParser {

  val notes: HashMap<String, String> = generate();

  fun parse(note: String): String {//used by piano
    return notes.get(note);
  }
}

fun freq(n: Double): String {
  val a = 1.0594630943592953;
  val f0=8.1757989156;
  val result = f0 * Math.pow(a, n);

  return "$result";
}

fun generate(): HashMap<String, String> {
	var num = 0;
	var notes: HashMap<String, String> = HashMap();
	var octave = -5;
	var result = 0;
	var i = 0.0;

	while(octave <= 5) {

		notes["C"+octave] = freq(i);
		notes["C#Db"+octave] = freq(++i);
		notes["D"+octave] = freq(++i);
		notes["D#/Eb"+octave] = freq(++i);
		notes["E"+octave] = freq(++i);
		notes["F"+octave] = freq(++i);
		notes["F#/Gb"+octave] = freq(++i);
		notes["G"+octave] = freq(++i);
		notes["G#/Ab"+octave] = freq(++i);
		notes["A"+octave] = freq(++i);
		notes["A#/Bb"+octave] = freq(++i);
		notes["B"+octave] = freq(++i);
    i++;

		octave++
	}

	return notes;
}
