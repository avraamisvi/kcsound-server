package kcsound

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.xeustechnologies.jcl.JarClassLoader
import org.xeustechnologies.jcl.JclObjectFactory
import java.io.File
import java.util.*
import kcsound.instruments.*;

public object InstrumentsManager {

	val jcl: JarClassLoader = JarClassLoader();
	val instruments: HashMap<String, JsonObject> = HashMap();
	val factory = JclObjectFactory.getInstance();

	init {
		load();
	}

	fun load() {
			val list = File("instruments").listFiles();

			for(fp:File in list) {
					fp.loadInstruments(jcl, instruments);
			}
	}

	fun createInstrument(name: String): Instrument {
		println("instrument name: $name");
    return factory.create(jcl, instruments.get(name).get("instrumentClass").getAsString()) as Instrument;
	}
}

public fun File.loadInstruments(jcl: JarClassLoader, insts: HashMap<String, JsonObject>): Boolean {

    if (this.isDirectory()) {//is an instrument folder

        val children = this.listFiles()
        if(children != null) {
            for (child in children) {
                if (!child.isDirectory()) {

                    if(child.getName().substringAfterLast('.').endsWith("jar",true)) {//instrument code

                        jcl.add(child.inputStream());

                    } else if(child.getName().substringAfterLast('.').endsWith("js",true)) {//instrument def

												val instr = Gson().fromJson(child.reader(), javaClass<JsonObject>());
                        insts.put(instr.get("name").getAsString(), instr);
                    }
                }
            }
        }

        return true;
    }

    return false
}
