package kcsound.templates

import kcsound.composition.*;

public class OrchestraTemplate {

  //var header: String="";
  var globals: String="";
  var instruments: String="";

  /*init {
    val header =  """
                    sr = 44100
                    ksmps = 32
                    nchnls = 2
                    0dbfs  = 1
                  """;
  }*/

  public fun addGlobal(global: String) {
    this.globals += "$global \n";
  }

  public fun addInstrument(instrument: String, id: Int) {

    instruments += "instr $id\n $instrument\n";
  }

  public fun generate(): String {
    return globals + instruments;//header +
  }
}
