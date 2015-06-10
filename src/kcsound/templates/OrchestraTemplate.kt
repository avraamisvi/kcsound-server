package kcsound.templates

import kcsound.composition.*;

public class OrchestraTemplate {

  var header: String="sr = 44100\nksmps = 32\nnchnls = 2\n0dbfs  = 1\n\n";
  var globals: String="";
  var instruments: String="";

  public fun addGlobal(global: String) {
    this.globals += "$global\n";
  }

  public fun addInstrument(instrument: String, id: Int) {

    instruments += "\ninstr $id\n $instrument\n endin \n";
  }

  public fun generate(): String {
    return header + globals + instruments;
  }
}
