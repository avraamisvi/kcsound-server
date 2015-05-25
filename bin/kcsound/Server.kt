package kcsound

public class Server {
	
	fun start() {
		println("teste");
	}
	
	fun compile(csoundString: String): Composition {
		return Composition();
	}
	
	fun play(composition: Composition): Player {
		return Player();
	}
	
	fun getStatus(player: Player): Stats {
		return Stats();
	}
}