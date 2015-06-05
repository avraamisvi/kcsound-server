package kcsound

public enum class MessageType(val code: Int) {
	PLAY:  MessageType(0),
	STOP:  MessageType(1),
	STATS: MessageType(3),
	UNKNOWN: MessageType(-1)
}
