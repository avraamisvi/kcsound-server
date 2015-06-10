package kcsound.composition

import java.util.HashMap
import java.util.ArrayList

public class Group {
  var id: Int=-1;
  var name: String="";
  var instruments: HashMap<String, String> = HashMap();
  var entries: ArrayList<GroupEntry> = ArrayList();
}
