package kcsound.composition

import java.io.File
import com.google.gson.JsonObject

public class GroupEntry {
  var id: Int?=null;
  var start: Double?=null;
  var duration: Double?=null;
}

public class Group {
  var id: Int?=null;
  var name: String?=null;
  var instruments: HashMap<String, Int>?=null;
  var entries: Array<GroupEntry>?=null;
}

/**
* This represents a score.
**/
public class Score {
  public var groups: Array<Group>?=null;
}
