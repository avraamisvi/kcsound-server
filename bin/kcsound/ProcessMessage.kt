package kcsound

import com.google.gson.Gson

public class Message {
	var data: String?=null;
    var type: Int?=null;
}

public class ProcessMessage {
	
	fun process(msg: String): Message {
		val gson = Gson();
		
		val ret: Message = gson.fromJson(msg, javaClass<Message>());
		
		return ret;
	}
}
