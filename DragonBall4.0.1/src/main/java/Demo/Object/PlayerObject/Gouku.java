package Demo.Object.PlayerObject;

import Demo.Data.PlayerInitData;
import Demo.Object.Container.PlayerShellsQueue;
import Demo.Util.IOC;

public class Gouku extends Player{
	
	public Gouku(PlayerInitData playerData) {
		super( playerData);
		
	}
	
	public Gouku() {
		super();
		playerShells=IOC.getApplicationContext().getBean(PlayerShellsQueue.class);
	}
	
}
