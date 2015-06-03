package kcsound

import java.io.File
import com.google.gson.JsonObject

public class InstrumentEntry {
	var id:Int?=null;
  var duration: Double;
}

/**
* This represents an instrument.
**/
public class Orchestra {

  var header: String?=null;
  var instruments: Array<JsonObject>?=null;
}
