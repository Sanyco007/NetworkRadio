package player;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import javazoom.jl.player.Player;

public class MP3Player extends Thread {

	public static Player player = null;
	
	@Override
	public void run() {
		try {
			InetAddress ipAddress = InetAddress.getByName("127.0.0.1");
			Socket socket = new Socket(ipAddress, 11000);
			InputStream stream = socket.getInputStream();
	        BufferedInputStream bis = new BufferedInputStream(stream, 65536);
	        player = new Player(bis);
	        player.play();
		}
		catch(Exception ex) {
			System.out.println("Óïñ: " + ex);
		}
	}
	
}