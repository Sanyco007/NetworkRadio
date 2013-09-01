package player;

public class Start {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		MP3Player player = new MP3Player();
		GUI gui = new GUI();
		gui.Initialize(player);
		gui.open();
		try {
			player.stop();
		}
		catch (Exception ex) {
			System.out.println("Inside the exception");
		}
	}

}
