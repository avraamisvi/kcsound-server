import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;
import javax.swing.JFrame
import javax.swing.JButton
import java.awt.event.ActionListener
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import javax.swing.JPanel

public class ActionStop(client: EmptyClient) : ActionListener {
	
	val client: EmptyClient = client;
	
	public override fun actionPerformed(e: ActionEvent) {
	   client.stop();
	}
}

public class ActionPlay(client: EmptyClient) : ActionListener {
	
	val client: EmptyClient = client;
	
	public override fun actionPerformed(e: ActionEvent) {
	   client.play();
	}
}

fun main(args : Array<String>) {
	
	val frame: JFrame = JFrame();
	val panel: JPanel = JPanel();
	val clientStop = EmptyClient(URI("ws://localhost:8887"), Draft_10());
	val clientPlay = EmptyClient(URI("ws://localhost:8887"), Draft_10());
	
	clientPlay.connect();
	clientStop.connect();
	
	frame.setSize(400, 100);
	val btnStop = JButton("Stop");
	val btnPlay = JButton("Play");
	
	btnPlay.addActionListener(ActionPlay(clientPlay));
	btnStop.addActionListener(ActionStop(clientStop));
		
	frame.getContentPane().add(panel);
	panel.add(btnPlay);
	panel.add(btnStop);
//	frame.pack();

	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.show()
}