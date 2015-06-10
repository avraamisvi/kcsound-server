package kcsound.templates

import kcsound.composition.*;

public class OrchestraTemplate {

  var header: String=
                  """
                  sr = 44100
                  ksmps = 32
                  nchnls = 2
                  0dbfs  = 1

                  """;
  var globals: String="";
  var instruments: String="";

  public fun addGlobal(global: String) {
    this.globals += "$global \n";
  }

  public fun addInstrument(instrument: String, id: Int) {

    instruments += "instr $id\n $instrument\n endin \n";
  }

  public fun generate(): String {
    return header + globals + instruments;
  }
}
