package Demo.Object.EnemyObject;

import java.awt.Graphics;
import java.util.concurrent.ConcurrentLinkedQueue;

import Demo.Data.Data;
import Demo.Object.GameObject;
import Demo.Object.Shell;
import Demo.Object.Container.EnemyShellsQueue;
import Demo.Util.IOC;
import Demo.Util.ReadImageUtil;

public class Enemy extends GameObject{
	
	protected boolean isBOSS;
	protected ConcurrentLinkedQueue<Shell> enemyShells;
	protected int round;
	public Enemy() {
		
	}
	public Enemy(int x,int y,double degree,int kind,int round) {
		super.x=x;
		super.y=y;
		super.width=Data.ENEMY_PlANE_WIDTH;
		super.height=Data.ENEMY_PlANE_HEIGHT;
		super.speed=Data.ENEMY_PLANE_SPEED;
		super.frozen_speed=Data.ENEMY_PLANE_SPEED/2;
		super.degree=degree;
		super.alive=true;
		super.image=ReadImageUtil.getEnemyPlaneImg(kind);
		super.max_blood=(int) (Data.ENEMY_PLANE_BLOOD*Math.pow(2, round));
		super.blood=max_blood;
		super.exp=super.blood;
		super.kind=kind;
		this.round=round;
		this.enemyShells=IOC.getApplicationContext().getBean(EnemyShellsQueue.class);
	}

	
	public void drawEnemyPlane(Graphics g) {
		alive&=y>Data.WINDOW_HEIGHT+100||y<-1000||x<-600||x>Data.WINDOW_WIDTH+600?false:true;
		if(alive) {
			speed=form==1?Data.BOSS_SPEED*2:Data.BOSS_SPEED;
			x+=speed*Math.cos(degree);
			y+=speed*Math.sin(degree);
			if((x<=0&&kind!=3)||x>=Data.WINDOW_WIDTH-width)
					degree=Math.PI-degree;
			if(y>=Data.WINDOW_HEIGHT-height||(kind==3&&y<=10))
				degree=-degree;
			fire();
			g.drawImage(image, x, y, width, height, null);
		}
	}
	private void fire() {
		if(!isBOSS) {
			if(Math.random()<Data.ENEMY_PLANE_FIRE_PROBABILITY)
				enemyShells.add(new Shell(x+width/2,y+height,Math.random()*Math.PI,kind,round));
		}else {
			if(enemyShells!=null&&Math.random()<Data.BOSS_FIRE_PROBABILITY) {
				if(kind==2) {
					for(int i=0;i<10;i++)
						enemyShells.add(new Shell(x+width/2,y+100,kind,form));
				}else {
					enemyShells.add(new Shell(x+width/2,y+height-50,kind,form));
				}
			}		
		}
	}
	public boolean isBOSS() {
		return isBOSS;
	}
	
}
