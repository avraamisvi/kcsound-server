package kcsound.templates

import kcsound.composition.*;

public class OrchestraTemplate {

  var header: String="sr = 44100\nksmps = 32\nnchnls = 2\n0dbfs  = 1\n\n";
  var globs: String="";
  var instrs: String="";

  public fun addGlobal(global: String) {
    this.globs =  "$globs $global\n";
  }

  public fun addInstrument(instrument: String, id: Int) {
    this.instrs = "$instrs\ninstr $id\n $instrument\nendin\n";
  }

  public fun generate(): String {
    return "$header$globs$instrs";
  }
}
