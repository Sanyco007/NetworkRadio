import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SearchConnection extends Thread {
	
	private ServerSocket music = null;
	private ServerSocket text = null;
	
	@Override
	public void run() {
		try {
			music = new ServerSocket(11000);
			music.setSoTimeout(5000);
			text = new ServerSocket(11001);
			text.setSoTimeout(5000);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				if (interrupted()) {
					music.close();
					text.close();
					Resources.clients.clear();
					System.out.println("Search thread interrupted!");
					return;
				}
				Socket musicSocket = music.accept();
				Socket textSocket = text.accept();
				Resources.clients.add(new Connection(musicSocket, textSocket));
				Thread.sleep(500);
			}
			catch (IOException | InterruptedException e) {
				//неудачная попытка подключения клиента
			}
		}
	}
	
}