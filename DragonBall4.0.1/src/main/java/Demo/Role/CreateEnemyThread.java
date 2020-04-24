package Demo.Role;


import java.util.concurrent.ConcurrentLinkedQueue;

import Demo.Data.Data;
import Demo.Object.Container.EnemyQueue;
import Demo.Object.EnemyObject.Enemy;
import Demo.Util.Lock;

public class CreateEnemyThread extends Thread{
	private ConcurrentLinkedQueue<Enemy> enemys;
	private int num;
	private int kind;
	private int round;
	public CreateEnemyThread(EnemyQueue enemys,
			int num,
			int kind,
			int round) {
		this.num=num;
		this.kind=kind;
		this.round=round;
		this.enemys=enemys;
	}
	@Override
	public void run() {
		for(int j=0;j<num;j++) {
			try {
				Lock.getSemaphore().acquire();
				if(Lock.isSuspend()) {
					Lock.getSemaphore().release();
					Object lock=Lock.getLock();
					synchronized (lock) {
						lock.wait();
					}
				}else
					Lock.getSemaphore().release();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
//			Lock.stop();	
			if(kind==0) {
				int x=(int)(Math.random()*(Data.WINDOW_WIDTH-200)+100);
				enemys.add(new Enemy(x, 
						0,
						Math.PI/2,//degree
						0,//kind 0-5
						round));
				enemys.add(new Enemy(x, 
						-80,
						Math.PI/2,//degree
						0,//kind 0-5
						round));
				enemys.add(new Enemy(x, 
						-160,
						Math.PI/2,//degree
						0,//kind 0-5
						round));
				enemys.add(new Enemy(x, 
						-240,
						Math.PI/2,//degree
						0,//kind 0-5
						round));
				enemys.add(new Enemy(x, 
						-300,
						Math.PI/2,//degree
						0,//kind 0-5
						round));
				
			}else if(kind==1) {
				int x=(int)(Math.random()*(Data.WINDOW_WIDTH-200)+100);
				enemys.add(new Enemy(x, 
						0,
						Math.PI/2,//degree
						2,//kind 0-5
						round));
				enemys.add(new Enemy(x-Data.ENEMY_PlANE_WIDTH, 
						-20,
						Math.PI/2,//degree
						1,//kind 0-5
						round));
				enemys.add(new Enemy(x+Data.ENEMY_PlANE_WIDTH, 
						-20,
						Math.PI/2,//degree
						1,//kind 0-5
						round));
				enemys.add(new Enemy(x-2*Data.ENEMY_PlANE_WIDTH, 
						-40,
						Math.PI/2,//degree
						1,//kind 0-5
						round));
				enemys.add(new Enemy(x+2*Data.ENEMY_PlANE_WIDTH, 
						-40,
						Math.PI/2,//degree
						1,//kind 0-5
						round));
			}else if(kind==3) {
				int y=(int)(Math.random()*Data.WINDOW_HEIGHT/3-Data.WINDOW_HEIGHT/6)+Data.WINDOW_HEIGHT/2;
				enemys.add(new Enemy(0, 
						y,
						Math.PI/3,//degree
						3,//kind 0-5
						round));
				enemys.add(new Enemy(-Data.ENEMY_PlANE_WIDTH, 
						y-Data.ENEMY_PlANE_HEIGHT,
						Math.PI/3,//degree
						3,//kind 0-5
						round));
				enemys.add(new Enemy(-Data.ENEMY_PlANE_WIDTH*2, 
						y-Data.ENEMY_PlANE_HEIGHT*2,
						Math.PI/3,//degree
						3,//kind 0-5
						round));

			}else
			enemys.add(new Enemy((int)(Math.random()*(Data.WINDOW_WIDTH-200)+100), //x
					(int)(Math.random()*Data.WINDOW_HEIGHT/5), //y
					Math.random()*Math.PI,//degree
					kind,//kind 0-5
					round));
			try {
				sleep((int)(Math.random()*4000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
