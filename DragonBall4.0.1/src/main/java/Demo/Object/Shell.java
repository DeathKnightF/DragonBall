package Demo.Object;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import Demo.Data.Data;
import Demo.Object.PlayerObject.PlayerPosition;
import Demo.Util.IOC;
import Demo.Util.ReadImageUtil;

public class Shell extends GameObject{
	private int harm;
	private boolean rebound;//�������߽��Ƿ񷴵�
	private int kind;
	private int form;//BOSS��������
	/**
	 * ��ҷɻ�����������
	 * @param x
	 * @param y
	 * @param degree
	 */
	public Shell(int x,int y,int harm,double degree,int kind) {
		super.x=x;
		super.y=y;
		super.degree=degree;
		super.image=ReadImageUtil.getPlayerShellImg(kind);
		super.width=Data.PLAYER_SHELL_WIDTH;
		super.height=Data.PLAYER_SHELL_HEIGHT;
		super.speed=Data.PLAYER_SHELL_SPEED;
		super.alive=true;
		this.harm=harm;
		this.kind=kind;//4ԭ�ӵ�
		initPlayerShell();
	}
	/**
	 * һ��л�����������
	 * @param x
	 * @param y
	 * @param degree
	 * @param kind
	 */
	public Shell(int x,int y,double degree,int kind,int round) {
		super.x=x;
		super.y=y;
		super.degree=degree;
		super.image=ReadImageUtil.getEnemyShellImg(kind);
		super.width=Data.ENEMY_SHELL_WIDTH;
		super.height=Data.ENEMY_SHELL_HEIGHT;
		super.speed=Data.ENEMY_SHELL_SPEED;
		super.alive=true;
		this.harm=Data.ENEMY_SHELL_HARM*(round+1);
		this.kind=kind;
	}
	/**
	 * BOSS
	 * @param x
	 * @param y
	 */
	public Shell(int x,int y,int kind,int form) {
		super.x=x;
		super.y=y;
		super.width=Data.BOSS_SHELL_WIDTH;
		super.height=Data.BOSS_SHELL_HEIGHT;
		super.speed=Data.BOSS_SHELL_SPEED;
		super.degree=Math.random()*2*Math.PI;
		super.alive=true;
		super.isBoss=true;
		this.kind=kind;
		this.form=form;
		this.harm=Data.BOSS_SHELL_HARM;
		super.images=new Image[Data.BOSS_FORM_NUM];
		Iterator<Image> it=ReadImageUtil.bossShellImgs[kind].iterator();
		for(int i=0;i<Data.BOSS_FORM_NUM&&it.hasNext();i++) {
			images[i]=it.next();
		}
		initBossShell();
	}

	
	public void initPlayerShell() {
		switch(kind) {
			case 0:
				
				break;
			case 1:
	
				break;
			case 2:
	
				break;
			case 3:
	
				break;
			case 4:
				super.image=ReadImageUtil.atomicBombImg;
				break;
			case 5:
				super.image=ReadImageUtil.bulletImg;
				super.degree=Math.random()*Math.PI*2;
				super.width/=2;
				super.height/=2;
		}
	}
	
	private PlayerPosition playerPosition=IOC.getApplicationContext().getBean(PlayerPosition.class);
	public void initBossShell() {
		super.width=Data.BOSS_ONE_SHELL_WIDTH;
		super.height=Data.BOSS_ONE_SHELL_WIDTH;
		super.image=super.images[form];
		
		switch(kind) {
			case 0://boss1
				switch(form) {
					case 0:	
//						super.degree=Math.random()*Math.PI;
						this.rebound=true;
						break;
					case 1:
						if(playerPosition.getX()>x)//追踪玩家位置
							super.degree=-2*Math.PI+Math.atan((y-playerPosition.getY()+0.0)/(x-playerPosition.getX()==0?0.0001:x-playerPosition.getX())); 
						else
							super.degree=Math.PI+Math.atan((y-playerPosition.getY()+0.0)/(x-playerPosition.getX()==0?0.0001:x-playerPosition.getX())); 
						super.speed=Data.BOSS_SHELL_SPEED*4;
						break;
					case 2:
						if(Math.random()<0.5) {
							super.alive=false;
							return;
						}
						super.speed=Data.BOSS_SHELL_SPEED/2;
						this.harm*=8;
						break;
					case 3:
						this.harm*=2;
						super.speed=(int) (Data.BOSS_SHELL_SPEED*1.5);
//						super.degree=Math.random()*Math.PI;
						break;
					default:
				}
				break;
			case 1:
				switch(form) {
					case 0:
						super.speed*=5;
						break;
					case 1:
						super.speed/=2;
						break;
					case 2:
						if(playerPosition.getX()>x)//追踪玩家位置
							super.degree=-2*Math.PI+Math.atan((y-playerPosition.getY()+0.0)/(x-playerPosition.getX()==0?0.0001:x-playerPosition.getX())); 
						else
							super.degree=Math.PI+Math.atan((y-playerPosition.getY()+0.0)/(x-playerPosition.getX()==0?0.0001:x-playerPosition.getX())); 
						rebound=true;  
						break;
					case 3:
						x=Data.WINDOW_WIDTH;
						y=(int) (Data.WINDOW_HEIGHT-Math.random()*600);
//						degree=Math.PI;
						harm*=5;
						break;
					default:
				}
				break;
			case 2:
				degree=Math.random()*2*Math.PI;
				image=images[0];
				width/=2;
				height/=2;
				switch(form) {
					case 0:
					
						break;
					case 1:
					
						break;
					case 2:
					
						break;
					case 3:
					
						break;
					default:
				}
				break;
			case 3:
				switch(form) {
					case 0:
					
						break;
					case 1:
					
						break;
					case 2:
					
						break;
					case 3:
					
						break;
					default:
				}
				break;
			default:
		}
	}
	
	
	
	

	public void drawPlayerShell(Graphics g) {
		alive=alive&isStillAlive();
		if(alive) {
			x+=speed*Math.cos(degree);
			y-=speed*Math.sin(degree);
			g.drawImage(image, x, y, width, height, null);
		}
	}

	public void drawEnemyShell(Graphics g) {
		alive=alive&isStillAlive();
		if(alive) {
			x+=speed*Math.cos(degree);
			y+=speed*Math.sin(degree);
			if(rebound) {
				if(x<=0||x>=Data.WINDOW_WIDTH-width)
					degree=Math.PI-degree;
				if(y>=Data.WINDOW_HEIGHT-height)
					degree=-super.degree;
			}
			g.drawImage(image, x, y, width, height, null);
		}
	}
	/**
	 * 导弹是否出界，是否碰撞
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isStillAlive() {
		if(x<0||x>Data.WINDOW_WIDTH||y<-1000||y>Data.WINDOW_HEIGHT)
			return false;
		return true;
	}
	public int getHarm() {
		return harm;
	}
	public void setHarm(int harm) {
		this.harm = harm;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getForm() {
		return form;
	}
	public void setForm(int form) {
		this.form = form;
	}

}
