package Demo.Object.EnemyObject;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import Demo.Data.Data;
import Demo.Object.Shell;
import Demo.Object.Container.EnemyShellsQueue;
import Demo.Util.IOC;
import Demo.Util.ReadImageUtil;

public class BOSS extends Enemy{
	private int imgNum=1;
	public BOSS(int kind,boolean islive) {
		super.x=Data.BOSS_POSITION_X;
		super.y=Data.BOSS_POSITION_Y;
		super.width=Data.BOSS_WIDTH;
		super.height=Data.BOSS_HEIGHT;
		super.speed=Data.BOSS_SPEED;
		super.frozen_speed=Data.BOSS_SPEED/2;
		super.degree=0;
		super.alive=islive;
		this.kind=kind;
		this.isBOSS=true;
		this.enemyShells=IOC.getApplicationContext().getBean(EnemyShellsQueue.class);
		this.form=0;
		initBoss();
	}
	public void initBoss() {
		if(kind==0) {
			width/=2;
		}
		if(kind==2) {
			degree=Math.random()*3/4*Math.PI+Math.PI/4;
			speed*=2;
		}
		super.blood=Data.BOSS_BLOOD[kind];
		super.max_blood=super.blood;
		super.exp=super.blood;
		this.imgNum=Data.BOSS_FORM_NUM;
		super.images=new Image[imgNum];
		Iterator<Image> it=ReadImageUtil.bossImgs[kind].iterator();
		for(int i=0;it.hasNext();i++) {
			images[i]=it.next();
		}
		image=images[0];
	}
	@Override
	public void drawEnemyPlane(Graphics g) {
		if(alive) {
//			if(y>Data.WINDOW_HEIGHT+100||y<-100||x<-200||x>Data.WINDOW_WIDTH) {//出界
//				x=(int)(Math.random()*Data.WINDOW_WIDTH/3+Data.WINDOW_WIDTH/3);
//				y=(int)(Math.random()*Data.WINDOW_HEIGHT/4);
//				degree=Math.random()*2*Math.PI;
//			}else 
//			{
				speed=form==1?Data.BOSS_SPEED*2:Data.BOSS_SPEED;
				x+=speed*Math.cos(degree);
				y+=speed*Math.sin(degree);
//			}
			if(x<=0||x>=Data.WINDOW_WIDTH-width)
				degree=Math.PI-degree;
			if(y<=0||y>=Data.WINDOW_HEIGHT-height)
				degree=-degree;
			if(Math.random()<Data.BOSS_CHANGE_PROBABILITY) {
				form=(int)(Math.random()*imgNum);
				image=images[form];
			}
			fire();
			g.drawImage(image, x, y, width, height, null);
		}
	}
	
	private void fire() {
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
