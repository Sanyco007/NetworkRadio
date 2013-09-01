import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {

	public OutputStream musicStream;
	public DataOutputStream textStream;
	public boolean succses = true;
	public boolean sendTitle = false;
	public int missed = 0;
	
	public Connection(Socket music, Socket text) {
		try {
			music.setSoTimeout(3000);
			musicStream = music.getOutputStream();
			textStream = new DataOutputStream(text.getOutputStream());
		}
		catch (IOException e) {
			succses = false;
		}
	}
	
}