package kcsound

import com.google.gson.Gson

public data class Message(val data: String, val type: Int)

public class ProcessMessage {
	
	fun process(msg: String): Message {
		val gson = Gson();
		
		val ret: Message = gson.fromJson(msg, Message::class);
		
		return Message("",1);
	}
}
