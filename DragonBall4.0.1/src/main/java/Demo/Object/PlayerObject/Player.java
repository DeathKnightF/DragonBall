package Demo.Object.PlayerObject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.stereotype.Component;
import Demo.Util.Clock;
import Demo.Util.IOC;
import Demo.Util.ReadImageUtil;
import Demo.Data.*;
import Demo.Object.GameObject;
import Demo.Object.LightWave;
import Demo.Object.Shell;
import Demo.Object.Container.LightWaveQueue;
import Demo.Object.Container.PlayerShellsQueue;

@Component
public class Player extends GameObject{
	protected boolean up,down,left,right,launchShells=true,isLaunchingW=false,isLaunchingQ=false;
	protected int upgradeBlood,upgradeSpeed,upgradeHarm;
	protected int scoreFromBoss=0;
	protected int grade;
	protected int harm;
	//the number of enemys which are killed by player
	protected int kills,coin;
	protected Image[] images;
	//the position of mouse
	protected int target_x;
	protected int target_y;
	//the time of duration 
	protected int WTime=0;
	protected int QTime=0;
	protected int pressQTimeBefore=-100000;//The last time to use the skill Q
	protected int pressWTimeBefore=-100000;//The last time to use the skill W
	protected int pressETimeBefore=-100000;//The last time to use the skill E
	protected int pressRTimeBefore=-100000;//The last time to use the skill R
	
	protected ConcurrentLinkedQueue<Shell> playerShells;
	protected ConcurrentLinkedQueue<LightWave> lightWaves;
	
	PlayerPosition playerPosition=IOC.getApplicationContext().getBean(PlayerPosition.class);
	public Player(
			PlayerInitData playerInitData) {
		super.x=Data.PLAYER_START_X;
		super.y=Data.PLAYER_START_Y;
		target_x=x;
		target_y=y;
		super.width=Data.PLAYER_WIDTH;
		super.height=Data.PLAYER_HEIGHT;
		super.speed=playerInitData.getSpeed();
		super.frozen_speed=speed/2;
		super.alive=true;
		super.blood=playerInitData.getBlood();
		super.exp=0;
		super.max_blood=super.blood;
		super.kind=playerInitData.getKind();
		this.playerShells=IOC.getApplicationContext().getBean(PlayerShellsQueue.class);
		this.lightWaves=IOC.getApplicationContext().getBean(LightWaveQueue.class);
		this.images=ReadImageUtil.getPlayerPlaneImgs(playerInitData.getKind());
		this.grade=0;
		this.harm=playerInitData.getHarm();
		this.upgradeBlood=playerInitData.getUpgradeBlood();
		this.upgradeHarm=playerInitData.getUpgradeHarm();
		this.upgradeSpeed=playerInitData.getUpgradeSpeed();
	}
	
	public Player() {
		
	}
	
	public void drawPlayerPlane(Graphics g) {
		if(alive) {
			if(state!= StateEnum.general){
				duration--;
				if(duration<=0) {
					state=StateEnum.general;
					duration=0;
				}
				switch(state) {
					case unbeatable:
						move(speed);
						g.drawImage(images[grade], x, y, width, height, null);
						break;
					case freezing:
						move(frozen_speed);
						g.drawImage(images[grade], x, y, width, height, null);
						g.drawImage(ReadImageUtil.propStateImgs.get(0), x, y, width, height, null);
						break;
					case swimmy:
						//player can't move
						move(0);
						g.drawImage(images[grade], x, y, width, height, null);
						g.drawImage(ReadImageUtil.propStateImgs.get(1), x, y, width, height/2, null);
						return;
					default:
						move(speed);
						g.drawImage(images[grade], x, y, width, height, null);
						break;
				}
			}else {
				move(speed);
				g.drawImage(images[grade], x, y, width, height, null);
			}
			//draw shield when player is unbeatable
			if(state==StateEnum.unbeatable)
				g.drawImage(ReadImageUtil.circleShield, x, y, width, height, null);
			fire();
		}
	}
	/*
	 * changing the position of player
	 */
	private void move(int speed) {
		if(left&&x>=speed)
			x-=speed;
		if(right&&x<=Data.WINDOW_WIDTH-Data.PLAYER_WIDTH-speed)
			x+=speed;
		if(up&&y>=30+speed)
			y-=speed;
		if(down&&y<=Data.WINDOW_HEIGHT-Data.PLAYER_HEIGHT-speed) 
			y+=speed;
		playerPosition.setX(x);
		playerPosition.setY(y);
	}
	/*
	 * player can upgrade when its experience meets the conditions
	 */
	public void upgrade() {
		if(exp>=Data.PLAYER_UPGRADE_EXP[grade]) {
			exp-=Data.PLAYER_UPGRADE_EXP[grade];
			grade++;
			upgrade();
			max_blood+=upgradeBlood;
			blood+=upgradeBlood;
			speed+=upgradeSpeed;
			harm+=upgradeHarm;
		}
	}
	public void upgrade(int exp) {
		this.exp+=exp;
		if(this.exp>=Data.PLAYER_UPGRADE_EXP[grade]) {
			this.exp-=Data.PLAYER_UPGRADE_EXP[grade];
			grade++;
			upgrade();
			max_blood+=upgradeBlood;
			blood+=upgradeBlood;
			speed+=upgradeSpeed;
			harm+=upgradeHarm;
		}
	}
	
	/*
	 * these two Booleans are used for slowing down the speed of fire
	 */
	private boolean fire_interval1=true;
	private boolean fire_interval2=true;
	private void fire() {
		
		if(launchShells&&fire_interval1&&fire_interval2) {
				switch(grade) {
					case 0:
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-16,y,harm,Math.PI/2,kind));
						break;
					case 1:
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-30,y,harm,Math.PI/2,kind));
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2,y,harm,Math.PI/2,kind));
						break;
					case 2:
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-40,y,harm,Math.PI/2,kind));
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-15,y,harm,Math.PI/2,kind));
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2+10,y,harm,Math.PI/2,kind));
						break;
					default:
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-45,y,harm,Math.PI/2,kind));
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-28,y,harm,Math.PI/2,kind));
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-7,y,harm,Math.PI/2,kind));
						playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2+10,y,harm,Math.PI/2,kind));
				}
			}
			fire_interval1=!fire_interval1;
			if(fire_interval1)
				fire_interval2=!fire_interval2;
			//skill Q is launching
			if(isLaunchingQ) {
				QTime--;
				if(QTime<0) {
					launchShells=true;
					isLaunchingQ=false;
					return;
				}
				playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-35,y,harm,Math.PI/2,5));
				playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-10,y,harm,Math.PI/2,5));
				playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2+15,y,harm,Math.PI/2,5));
				playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-45,y,harm,Math.PI/2,5));
				playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-28,y,harm,Math.PI/2,5));
				playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-7,y,harm,Math.PI/2,5));
				playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2+10,y,harm,Math.PI/2,5));
			}
	}
	/**
	 * 	launching skills
	 */
	public void pressQ() {
		if(!isLaunchingW&&Clock.getTime()-pressQTimeBefore>Data.SKILL_Q_COOLING_TIME) {
			pressQTimeBefore=Clock.getTime();
			launchShells=false;
			isLaunchingQ=true;
			QTime=Data.SKILL_Q_NUM;
		}
	}
	public void pressW() {
		if(Clock.getTime()-pressWTimeBefore>Data.SKILL_W_COOLING_TIME) {
			pressWTimeBefore=Clock.getTime();
			isLaunchingW=true;
			launchShells=false;
			return;
		}
		if(isLaunchingW) {
			isLaunchingW=false;
			launchShells=true;
			WTime=(Clock.getTime()-pressWTimeBefore)/7;
			lightWaves.add(new LightWave(WTime, this));
		}
	}
	public void pressE() {
		if(Clock.getTime()-pressETimeBefore>Data.SKILL_E_COOLING_TIME) {
			pressETimeBefore=Clock.getTime();
			state=StateEnum.unbeatable;
			duration=Data.SKILL_E_DURATION;
		}
	}
	public void pressR() {
		if(!isLaunchingW&&Clock.getTime()-pressRTimeBefore>Data.SKILL_R_COOLING_TIME) {
			pressRTimeBefore=Clock.getTime();
			playerShells.add(new Shell(x+Data.PLAYER_WIDTH/2-16,y,harm*10,Math.PI/2,4));
		}
	}
	
	/**
	 * 
	 *changing positing and state of player by using mouse
	 */
	public void changePositionAndState(MouseEvent e) {
		target_x=e.getX();
		target_y=e.getY();
		if(target_x-10<=x&&target_x+30>=x||target_x>x)
			left=false;
		else
			left=true;
		if(target_x-10<=x&&target_x+30>=x||target_x<x) 
			right=false;
		else 
			right=true;
		
		if(target_y-10<=y&&target_y+30>=y||target_y>y)
			up=false;
		else
			up=true;
		if(target_y-10<=y&&target_y+30>=y||target_y<y) 
			down=false;
		else 
			down=true;
	}
	
	public void changePositionAndState(KeyEvent e) {
		switch( e.getKeyCode()) {
			case KeyEvent.VK_UP:
				up=true;
				down=false;
				break;
			case KeyEvent.VK_DOWN:
				down=true;
				up=false;
				break;
			case KeyEvent.VK_LEFT:
				left=true;
				right=false;
				break;
			case KeyEvent.VK_RIGHT:
				right=true;
				left=false;
				break;
			case KeyEvent.VK_SPACE:
				if(!isLaunchingW)
					launchShells=true;
				break;
			case KeyEvent.VK_Q:
				pressQ();
				break;
			case KeyEvent.VK_W:
				if(isLaunchingW)
					break;
				pressW();
				break;	
			case KeyEvent.VK_E:
				pressE();
				break;	
			case KeyEvent.VK_R:
				pressR();
				break;
		}
	}
	public void cancelPositionAndState(KeyEvent e) {
		switch( e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
		case KeyEvent.VK_SPACE:
			launchShells=false;
			break;
		case KeyEvent.VK_Q:
			launchShells=true;
		
		case KeyEvent.VK_W:
			if(!isLaunchingW)
				break;
			isLaunchingW=false;
			launchShells=true;
			WTime=(Clock.getTime()-pressWTimeBefore)/7;
//			System.out.println(WTime);
			lightWaves.add(new LightWave(WTime, this));
			break;
		}
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getHarm() {
		return harm;
	}

	public void setHarm(int harm) {
		this.harm = harm;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public Image[] getImages() {
		return images;
	}

	public void setImages(Image[] images) {
		this.images = images;
	}

	public int getUseTime() {
		return WTime;
	}

	public void setUseTime(int useTime) {
		this.WTime = useTime;
	}

	public boolean isLaunchingLaser() {
		return isLaunchingW;
	}

	public int getPressQTimeBefore() {
		return pressQTimeBefore;
	}

	public int getPressWTimeBefore() {
		return pressWTimeBefore;
	}

	public int getPressETimeBefore() {
		return pressETimeBefore;
	}

	public int getPressRTimeBefore() {
		return pressRTimeBefore;
	}

	public int getScoreFromBoss() {
		return scoreFromBoss;
	}

	public void setScoreFromBoss(int scoreFromBoss) {
		this.scoreFromBoss = scoreFromBoss;
	}

	public int getUpgradeBlood() {
		return upgradeBlood;
	}

	public void setUpgradeBlood(int upgradeBlood) {
		this.upgradeBlood = upgradeBlood;
	}

	public int getUpgradeSpeed() {
		return upgradeSpeed;
	}

	public void setUpgradeSpeed(int upgradeSpeed) {
		this.upgradeSpeed = upgradeSpeed;
	}

	public int getUpgradeHarm() {
		return upgradeHarm;
	}

	public void setUpgradeHarm(int upgradeHarm) {
		this.upgradeHarm = upgradeHarm;
	}
	
}
