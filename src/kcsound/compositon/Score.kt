package kcsound.composition

import java.io.File
import com.google.gson.JsonObject

public class Group {
	public var instruments:Array<Int>? = null;
  public var groupName: String?=null;

  public var start: Double = 0.0;
  public var duration: Double = 0.0;
}

/**
* This represents a score.
**/
public class Score {

  public var groups: Array<Group>?=null;
}
