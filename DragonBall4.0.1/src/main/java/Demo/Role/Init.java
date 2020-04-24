package Demo.Role;

import java.awt.EventQueue;
import java.util.Date;
import Demo.Object.Container.EnemyQueue;
import Demo.Object.Container.PropsQueue;
import Demo.Object.EnemyObject.BOSS;
import Demo.Object.EnemyObject.Enemy;
import Demo.Object.PlayerObject.Player;
import Demo.UI.GameFrame;
import Demo.Util.ReadMapData;

public class Init {
	Player player;
	EnemyQueue enemys;
	PropsQueue props;
	Enemy Boss ;
	Date startTime;
	int round;
	public Init(Player player,
			PropsQueue props,
			Date startTime,
			int round) {
		this.player=player;
		this.startTime=startTime;
		this.round=round;
		this.enemys=new EnemyQueue();
		this.enemys.clear();
		this.props=props;
	}
	public void start() {
			if(round==0) {
				startTime=new Date();
			}
			Boss=new BOSS(round,false);
			enemys.add(Boss);
			GameFrame frame=new GameFrame(
					player,
					enemys,
					props,
					Boss,
					round,
					startTime,
					false
					);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			new Thread(()->{
				CreateEnemyThread[] threads=new CreateEnemyThread[6];
				int[][] temp = null;
				temp = ReadMapData.readMap();
				for(int j=0;j<6;j++) {
					threads[j]=new CreateEnemyThread(
							(EnemyQueue) enemys,
							temp[round][j],
							j,
							round);
					threads[j].start();
				}
				while(true) {
					boolean judge=true;
					for(int i=0;i<6;i++)
						if(threads[i]!=null&&threads[i].isAlive()) {
							judge=false;
							break;
						}
					if(!judge)
						continue;
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Boss.setAlive(true);
					enemys.add(Boss);
					break;
				}
			}) .start();
			
			for(int i=0;i<2;i++)
				new CreatePropThread( 300, i, player,props).start();;
	}

}
