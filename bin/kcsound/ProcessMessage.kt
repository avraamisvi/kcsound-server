package kcsound

import com.google.gson.*

public trait Message {
	val type: MessageType;
}

//class LocalMessage {
//	var data: String?=null;
//    var type: MessageType?=null;
//}

public class PlayMessage(composition: Composition) : Message {
    override val type: MessageType = MessageType.PLAY;
	val composition: Composition = composition;
}

public class StopMessage() : Message {
    override val type: MessageType = MessageType.STOP;
}

public class UnknownMessage() : Message {
    override val type: MessageType = MessageType.UNKNOWN;
}

public class ProcessMessage {
	
	fun process(msg: String): Message {
		val gson = Gson();
		
		val ret: JsonElement = gson.fromJson(msg, javaClass<JsonElement>());
		
		return when (MessageType.valueOf(ret.getAsJsonObject().get("type").getAsString())) {
		 
			  MessageType.PLAY -> {
			  	gson.fromJson(ret, javaClass<PlayMessage>());
			  }
				
			  MessageType.STOP -> {
			  	gson.fromJson(ret, javaClass<StopMessage>());
			  }
			
			  else -> {
			    UnknownMessage()
			  }
		}
	}
}
