package Demo.Role;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import Demo.Data.Data;
import Demo.Data.StateEnum;
import Demo.Object.GameObject;
import Demo.Object.LightWave;
import Demo.Object.SpecialEffects;
import Demo.Object.Container.EnemyShellsQueue;
import Demo.Object.Container.LightWaveQueue;
import Demo.Object.Container.PlayerShellsQueue;
import Demo.Object.Container.PropsQueue;
import Demo.Object.Container.SpecialEffectsQueue;
import Demo.Object.EnemyObject.Enemy;
import Demo.Object.PlayerObject.Player;
import Demo.Util.IOC;
import Demo.Object.Prop;
import Demo.Object.Shell;

public class Crash {
	static ConcurrentLinkedQueue<Shell> playerShells;
	static ConcurrentLinkedQueue<Enemy> enemys;
	static ConcurrentLinkedQueue<Shell> enemyShells;
	static ConcurrentLinkedQueue<LightWave> lightWaves;
	static ConcurrentLinkedQueue<SpecialEffects> effects;
	static ConcurrentLinkedQueue<Prop> props;
	static {
		playerShells=IOC.getApplicationContext().getBean(PlayerShellsQueue.class);
		enemyShells=IOC.getApplicationContext().getBean(EnemyShellsQueue.class);
		lightWaves=IOC.getApplicationContext().getBean(LightWaveQueue.class);
		effects=IOC.getApplicationContext().getBean(SpecialEffectsQueue.class);
	}
	public static void crash(Player player,
			ConcurrentLinkedQueue<Enemy> enemys,
			PropsQueue props)
//			,Graphics g)
	{
		Crash.enemys=enemys;
		Crash.props=props;
		//清理过时数据
		while(!effects.isEmpty()&&(effects.peek()==null||!effects.peek().live)) 
			effects.poll();
		while(!props.isEmpty()&&(props.peek()==null||!props.peek().isAlive())) 
			props.poll();
		while(!enemys.isEmpty()&&(enemys.peek()==null||!enemys.peek().isAlive()))
			enemys.poll();
		while(!playerShells.isEmpty()&&(playerShells==null||!playerShells.peek().isAlive()))
			playerShells.poll();
		while(!enemyShells.isEmpty()&&(enemyShells.peek()==null||!enemyShells.peek().isAlive()))
			enemyShells.poll();
		//光波
		Crash.crash(enemys,enemyShells,lightWaves,effects,player);
		//判断玩家是否吃到道具
		Crash.crash(props,effects,player);
		//判断敌机是否与玩家或者玩家导弹相碰
		Crash.crash(player, playerShells, enemys,enemyShells, effects);
		//判断敌机导弹是否与玩家飞机相碰
		Crash.crash(player, enemyShells, effects);
	}
	/**
	 * 判断敌机是否与玩家和玩家导弹发生碰撞
	 * @param player
	 * @param playerShells
	 * @param enemyPlanes
	 * @param effects
	 * @param g
	 */
	private static void crash(Player player,ConcurrentLinkedQueue<Shell> playerShells,
			ConcurrentLinkedQueue<Enemy> enemyPlanes,
			ConcurrentLinkedQueue<Shell> enemyShells,
			ConcurrentLinkedQueue<SpecialEffects> effects)
	{
		Rectangle playerRect=player.getRectangle();
		Iterator<Enemy> enemyPlanesIt=enemyPlanes.iterator();
		while(enemyPlanesIt.hasNext()) {
			Enemy tempE=enemyPlanesIt.next();
			if(!tempE.isAlive()) 
				continue;
			Rectangle tempERect=tempE.getRectangle();
			if(tempERect.intersects(playerRect)) {
				if(tempE.isBOSS()) {
					if(player.getState()==StateEnum.unbeatable)
						continue;
					player.setBlood(player.getBlood()-Data.BOSS_HARM);
				}else {
					tempE.setAlive(false);
					if(player.getState()!=StateEnum.unbeatable)
						player.setBlood(player.getBlood()-10);
					effects.add(new SpecialEffects(tempE.getX(), tempE.getY(), tempE.getWidth(), tempE.getHeight(),0));
					player.setKills(player.getKills()+1);
					player.upgrade(tempE.getMax_blood());
				}
				if(player.getBlood()<=0) 
					player.setAlive(false);
			}
			Iterator<Shell> playerShellsIt=playerShells.iterator();
			while(playerShellsIt.hasNext()) {
				Shell tempS=playerShellsIt.next();
				if(!tempS.isAlive())
					continue;
				if(tempS.getKind()==4) {//ԭ�ӵ�
					crash(tempS, player, playerShells, enemyPlanes, enemyShells, effects);
					continue;
				}
				Rectangle tempSRect=tempS.getRectangle();
				if(tempSRect.intersects(tempERect)) {
					tempS.setAlive(false);
					tempE.setBlood(tempE.getBlood()-tempS.getHarm());
					if(tempE.getBlood()<=0) {
						player.upgrade(tempE.getMax_blood());
						player.setKills(player.getKills()+1);
						tempE.setAlive(false);
						effects.add(new SpecialEffects(tempE.getX(), tempE.getY(), tempE.getWidth(), tempE.getHeight(),0));
					}
				}
			}
//			tempE.drawEnemyPlane(g);
		}
//		Iterator<Shell> playerShellsIt=playerShells.iterator();
//		while(playerShellsIt.hasNext()) 
//			playerShellsIt.next().drawPlayerShell(g);
	}
	/**
	 * 判断敌方导弹是否击中玩家
	 * @param player
	 * @param enemyShells
	 * @param effects
	 * @param g
	 */
	private static void crash(Player player,
			ConcurrentLinkedQueue<Shell> enemyShells,
			ConcurrentLinkedQueue<SpecialEffects> effects) {
		Rectangle playerRect=player.getRectangle();
		Iterator<Shell> enemyShellsIt=enemyShells.iterator();
		while(enemyShellsIt.hasNext()) {
			Shell tempS=enemyShellsIt.next();
			if(!tempS.isAlive())
				continue;
			Rectangle tempSRect=tempS.getRectangle();
			if(tempSRect.intersects(playerRect)) {//�з������������
				tempS.setAlive(false);
				if(player.getState()!=StateEnum.unbeatable) {//û�л���
					player.setBlood(player.getBlood()-tempS.getHarm());
					judgeShellImpact(tempS, player,effects);
				}
				if(player.getBlood()<=0) {
					player.setAlive(false);
					effects.add(new SpecialEffects(player.getX(), player.getY(), player.getWidth(), player.getHeight(),0));
				}
			}
//			tempS.drawEnemyShell(g);
		}
	}
	/**
	 * 判断玩家是否吃到道具
	 * @param player
	 * @param props
	 * @param g
	 */
	private static void crash(ConcurrentLinkedQueue<Prop> props,
			ConcurrentLinkedQueue<SpecialEffects> effects,
			Player player) {
		Rectangle playerRect=player.getRectangle();
		Iterator<Prop> propsIt=props.iterator();
		while(propsIt.hasNext()) {
			Prop tempP=propsIt.next();
			if(!tempP.isAlive())
				continue;
			Rectangle tempPRect=tempP.getRectangle();
			if(tempPRect.intersects(playerRect)) {
				tempP.use();
				tempP.setAlive(false);
				if(tempP.getKind()==1)
					effects.add(new SpecialEffects(tempP.getX(), tempP.getY(), tempP.getWidth(), tempP.getHeight(), 4));
			}
//			else
//				tempP.drawProp(g);
		}
	}
	/**
	 *原子弹 
	 * @param atomicBomb
	 * @param player
	 * @param playerShells
	 * @param enemyPlanes
	 * @param enemyShells
	 * @param effects
	 */
	private static void crash(Shell atomicBomb,Player player,
			ConcurrentLinkedQueue<Shell> playerShells,
			ConcurrentLinkedQueue<Enemy> enemyPlanes,
			ConcurrentLinkedQueue<Shell> enemyShells,
			ConcurrentLinkedQueue<SpecialEffects> effects) {
		if(atomicBomb.getY()>350)
			return;
		atomicBomb.setAlive(false);
		playerShells.clear();
		enemyShells.clear();
		Iterator<Enemy> it=enemyPlanes.iterator();
		while(it.hasNext()) {
			Enemy tempE=it.next();
			if(tempE.isBOSS()&&tempE.isAlive()) {
				if(!withInTheLimitOf(atomicBomb, tempE, Data.EXPLODE_RANGE_OF_ATOMIC_BOMB))//���ڱ�ը��Χ��
					continue;
				tempE.setBlood(tempE.getBlood()-player.getHarm()*10);
				if(tempE.getBlood()<=0) {
					tempE.setAlive(false);
					effects.add(new SpecialEffects(tempE.getX(), tempE.getY(), tempE.getWidth(), tempE.getHeight(), 0));
				}
			}else {
				if(!withInTheLimitOf(atomicBomb, tempE, Data.EXPLODE_RANGE_OF_ATOMIC_BOMB))//���ڱ�ը��Χ��
					continue;
				tempE.setAlive(false);
				player.upgrade(tempE.getMax_blood());
			}
		}
		effects.add(new SpecialEffects(atomicBomb.getX()-100, atomicBomb.getY()-200, 300, 300, 2));
	}
	/**
	 * 范围性伤害（光波）
	 * @param player
	 * @param enemyPlanes
	 * @param enemyShells
	 * @param lightWaves
	 */
	private static void crash(ConcurrentLinkedQueue<Enemy> enemyPlanes,
			ConcurrentLinkedQueue<Shell> enemyShells,
			ConcurrentLinkedQueue<LightWave> lightWaves,
			ConcurrentLinkedQueue<SpecialEffects> explodes,
			Player player) {
		Iterator<LightWave> wavesIt=lightWaves.iterator();
		while(wavesIt.hasNext()) {
			LightWave templ=wavesIt.next();
			if(!templ.isAlive())
				continue;
			Rectangle tempLightRect=templ.getRectangle();
			for(Shell shell:enemyShells)
				if(shell.isAlive()&&tempLightRect.intersects(shell.getRectangle()))
					shell.setAlive(false);
			for(Enemy plane:enemyPlanes)
				if(plane.isAlive()&&tempLightRect.intersects(plane.getRectangle())) {
					plane.setBlood(plane.getBlood()-templ.getHarm());
					if(plane.getBlood()<0) {
						plane.setAlive(false);
						player.upgrade(plane.getMax_blood());
						explodes.add(new SpecialEffects(plane.getX(), plane.getY(), plane.getWidth(), plane.getHeight(), 0));
					}
				}
//			templ.draw(player, g);
		}
	}
	//判断两个对象之间的距离是否小于dist
	private static boolean withInTheLimitOf(GameObject g1,GameObject g2,int dist) {
		return (int) Math.ceil(Math.sqrt(Math.pow(g1.getX()-g2.getX(), 2)+Math.pow(g1.getY()-g2.getY(), 2)))<=dist?true:false;
	}
	//判断导弹的技能效果
	private static void judgeShellImpact(Shell temp,Player player,
			ConcurrentLinkedQueue<SpecialEffects> effects) {
		if(player.getState()==StateEnum.unbeatable)
			return;
		if(temp.isBoss()) {
			switch(temp.getKind()) {
				case 0://boss1
					if(temp.getForm()==3) {
						player.setState(StateEnum.swimmy);
						player.setDuration(Data.ENEMY_SHELL_BAD_STATE_DURATION);
					}
					break;
				case 1://boss2
					if(temp.getForm()==1) {
						player.setState(StateEnum.freezing);
						player.setDuration(Data.ENEMY_SHELL_BAD_STATE_DURATION);
					}
					if(temp.getForm()==3)
					{
						effects.add(new SpecialEffects(temp.getX(), temp.getY(), player.getWidth(), player.getHeight(),1));
					}
					break;
			}
		}else {
			if(temp.getKind()==1||temp.getKind()==5) {
				player.setState(StateEnum.freezing);
				player.setDuration(Data.ENEMY_SHELL_BAD_STATE_DURATION);
			}
		}
	}
}
