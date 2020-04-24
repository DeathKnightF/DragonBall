package Demo.Role;

import java.awt.EventQueue;
import java.util.Date;
import Demo.Object.Container.EnemyQueue;
import Demo.Object.Container.PropsQueue;
import Demo.Object.EnemyObject.BOSS;
import Demo.Object.EnemyObject.Enemy;
import Demo.Object.PlayerObject.Player;
import Demo.UI.GameFrame;

public class InitBossMode {
	Player player;
	int kind;
	EnemyQueue enemys;
	PropsQueue props;
	public InitBossMode(int kind,Player player,PropsQueue props) {
		this.kind=kind;
		this.player=player;
		enemys=new EnemyQueue();
		this.props=props;
	}
	
	public void start() {

		Date startTime=new Date();
		Enemy Boss;
		if(kind==3) {
			Boss = new BOSS(2,true);
//			enemiesQueue.add(new BOSS( 0, true));
		}else if(kind==4) {
			Boss = new BOSS(2,true);
//			enemiesQueue.add(new BOSS( 0, true));
//			enemiesQueue.add(new BOSS( 1, true));
		}else
			Boss = new BOSS(kind,true);
		enemys.add(Boss);
		GameFrame frame=new GameFrame(player,
				enemys,
				props,
				Boss,
				kind,
				startTime,
				true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new CreatePropThread( 200, 0, player,props).start();
		new CreatePropThread( 200, 1, player,props).start();
	}
	
}
