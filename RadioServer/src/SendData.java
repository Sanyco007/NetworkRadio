import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SendData extends Thread {
	
	private final int MAX_MISSED = 10;
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while (true) {
			try {
				if (interrupted()) {
					return;
				}
				if (Resources.files.size() == 0 || Resources.clients.size() == 0) {
					Thread.sleep(500);
					continue;
				}
				File file = new File(Resources.files.get(0));
				Resources.files.remove(0);
				String song = file.getName();
				for (int i = 0; i < Resources.clients.size(); i++) {
					if (Resources.clients.get(i).succses) {
						Resources.clients.get(i).textStream.write(song.getBytes());
					}
					else {
						Resources.clients.remove(i--);
					}
				}
				FileInputStream fs = new FileInputStream(file);
				long length = file.length();
				byte[] data = new byte[40960];
				int offset = 0;
				while (fs.available() > 0) {
					int count = 40960;
					if (length - offset < count) {
						count = (int)(length - offset);
						data = new byte[count];
					}
					fs.read(data, 0, count);
					offset += count;
					for (int i = 0; i < Resources.clients.size(); i++) {
						if (Resources.clients.get(i).missed > MAX_MISSED) {
							Resources.clients.remove(i--);
							System.out.println("remove #" + i);
						}
					}
					SendPiece[] send = new SendPiece[Resources.clients.size()];
					for (int i = 0; i < send.length; i++) {
						send[i] = new SendPiece();
						send[i].initialize(Resources.clients.get(i).musicStream, data);
					}
					for (int i = 0; i < send.length; i++) {
						send[i].start();
					}
					sleep(1000);
					for (int i = 0; i < send.length; i++) {
						if (!send[i].send) {
							send[i].stop();
							Resources.clients.get(i).missed++;
						}
					}
				}
				Thread.sleep(500);
			} 
			catch (IOException e) {
				//ignore
			}
			catch (InterruptedException e) {
				return;
			}
		}
	}
	
}

class SendPiece extends Thread {
	
	private OutputStream musicStream;
	private byte[] data;
	public boolean send = false;
	
	public void initialize(OutputStream musicStream, byte[] data) {
		this.musicStream = musicStream;
		this.data = data;
	}
	
	@Override
	public void run() {
		try {
			musicStream.write(data);
			send = true;
		} 
		catch (IOException e) {
			System.out.println("close by prog");
			return;
		}
	}
}