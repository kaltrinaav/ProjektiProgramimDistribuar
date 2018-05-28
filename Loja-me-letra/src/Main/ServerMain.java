package Main;

import javax.swing.JOptionPane;

import GUI.Permbajtja_lojes;
import GUI.SuitImages;
import Network.Server;

public class ServerMain {

	public static void main(String[] Args) {

		Args = new String[1];
		Args[0] = "-Server";
		if (Args.length != 0 && Args[0].equals("-Server")) {
			try {

				Server s = new Server(12345);
				Thread serverThread = new Thread(s);
				serverThread.start();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else {
			String PlayerName = JOptionPane.showInputDialog("Shtyp emrin tuaj");

			// String PlayerName = "Player1";
			String IPAddress = "localhost";

			try {
				SuitImages.BufferImages();
				Permbajtja_lojes mainWindow = new Permbajtja_lojes(IPAddress, 12345, PlayerName);
				mainWindow.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

}
