package player;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class TextConection extends Thread{

	private boolean connect = false;
	private DataInputStream input;
	private Label label;
	private Label songTitle;
	
	public void initialize(Label label, Label songTitle) {
		this.label = label;
		this.songTitle = songTitle;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				if (!connect) {
					InetAddress ipAddress = InetAddress.getByName("127.0.0.1");
					Socket socket = new Socket(ipAddress, 11001);
					InputStream stream = socket.getInputStream();
					input = new DataInputStream(stream);
					connect = true;
				}
				if (input.available() > 0) {
					final byte[] buffer = new byte[65536];
					int len = input.read(buffer);
					int start = 0;
					if (buffer[0] == 1) start = 1;
					final String text = new String(buffer, start, len);
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							if (buffer[0] == 1) {
								label.setText(text);
								label.pack();
							}
							else {
								songTitle.setText(text);
								songTitle.pack();
							}
						}
					});
				}
				Thread.sleep(500);
			} 
			catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}