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

fun main(args : Array<String>) {
	
	val frame: JFrame = JFrame();
	val client = EmptyClient(URI("ws://localhost:8887"), Draft_10());
	client.connect();
	
	frame.setSize(400, 400);
	val btnStop = JButton("Stop");
	
	btnStop.addActionListener(ActionListener(){
	    fun actionPerformed(e: ActionEvent) {
	        
	    }		
	})
	
	frame.getContentPane().add(btnStop);
	frame.pack();
	

	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.show()
}