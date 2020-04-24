package Demo.Server;

import java.net.UnknownHostException;

public class StartServer {

	public static void main(String[] args) {
		try {
			new ServerFrame().setVisible(true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
