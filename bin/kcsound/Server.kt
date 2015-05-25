package kcsound

public class Server {
	
	fun start() {
		println("teste");
	}
	
	fun compile(): Composition {
		return Composition();
	}
	
	fun play(): Player {
		return Player();
	}
	
	fun getStatus(): Stats {
		return Stats();
	}
}