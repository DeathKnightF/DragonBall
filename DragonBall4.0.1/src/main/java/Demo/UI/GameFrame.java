package Demo.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JButton;
import javax.swing.JLabel;
import Demo.Data.Data;
import Demo.Object.LightWave;
import Demo.Object.Prop;
import Demo.Object.SpecialEffects;
import Demo.Object.Container.EnemyQueue;
import Demo.Object.Container.EnemyShellsQueue;
import Demo.Object.Container.LightWaveQueue;
import Demo.Object.Container.PlayerShellsQueue;
import Demo.Object.Container.PropsQueue;
import Demo.Object.Container.SpecialEffectsQueue;
import Demo.Object.EnemyObject.Enemy;
import Demo.Object.PlayerObject.Player;
import Demo.Object.Shell;
import Demo.Role.Crash;
import Demo.Role.Init;
import Demo.Util.Clock;
import Demo.Util.IOC;
import Demo.Util.Lock;
import Demo.Util.ReadImageUtil;

public class GameFrame extends Frame{

	private static final long serialVersionUID = 1L;
	GameFrame gameFrame;
	private static Image backgroundImg11;
	private static Image backgroundImg12; 
	private int background_y1=0;
	private int background_y2=-Data.WINDOW_HEIGHT;
	private Date startTime;
	public int round;
	public boolean isBossMode;
	private Player player;
	private Enemy BOSS;
	private ConcurrentLinkedQueue<Shell> playerShells;
	private ConcurrentLinkedQueue<LightWave> lightWaves;
	private ConcurrentLinkedQueue<Enemy> enemys;
	private ConcurrentLinkedQueue<Shell> enemyShells;
	private ConcurrentLinkedQueue<SpecialEffects> effects;
	private ConcurrentLinkedQueue<Prop> props;
	
	
	public GameFrame(Player player,
			EnemyQueue enemys,
			PropsQueue props,
			Enemy BOSS,
			int round,
			Date startTime,
			boolean isBossMode
			) {
		this.player=player;
		this.playerShells=IOC.getApplicationContext().getBean(PlayerShellsQueue.class);
		this.playerShells.clear();
		this.lightWaves=IOC.getApplicationContext().getBean(LightWaveQueue.class);
		this.lightWaves.clear();
		this.enemys=enemys;
		this.enemyShells=IOC.getApplicationContext().getBean(EnemyShellsQueue.class);
		this.enemyShells.clear();
		this.props=props;
		this.effects=IOC.getApplicationContext().getBean(SpecialEffectsQueue.class);
		this.effects.clear();
		this.startTime=startTime;
		this.BOSS=BOSS;
		this.round=round;
		this.isBossMode=isBossMode;
		this.gameFrame=this;
		
		setUndecorated(true);
		setBounds(Data.WINDOW_POSITION_X, Data.WINDOW_POSITION_Y, 
				Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
		setVisible(true);
		
		JButton stopButton=new JButton();
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					StopDialog stopDialog = new StopDialog(gameFrame,player,BOSS);
					stopDialog.setVisible(true);
					Lock.getSemaphore().acquire();
					Lock.setSuspend(true);
					Lock.getSemaphore().release();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		stopButton.setBounds(Data.MENU_BUTTON_X, Data.MENU_BUTTON_Y,
				Data.MENU_BUTTON_WIDTH, Data.MENU_BUTTON_HEIGHT);
		add(stopButton);
		
		JButton QButton=new JButton();
		QButton.setBounds(Data.SKILL_BUTTON_POSITION_X, Data.SKILL_BUTTON_POSITION_Y, 
				Data.SKILL_BUTTON_WIDTH, Data.SKILL_BUTTON_HEIGHT);
		QButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.pressQ();
				requestFocus();
			}
			
		});
		add(QButton);
		
		JButton WButton=new JButton();
		WButton.setBounds(Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL, Data.SKILL_BUTTON_POSITION_Y, 
				Data.SKILL_BUTTON_WIDTH, Data.SKILL_BUTTON_HEIGHT);
		WButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.pressW();
				requestFocus();
			}
			
			
		});
		add(WButton);
		
		JButton EButton=new JButton();
		EButton.setBounds(Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL*2, Data.SKILL_BUTTON_POSITION_Y, 
				Data.SKILL_BUTTON_WIDTH, Data.SKILL_BUTTON_HEIGHT);
		EButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.pressE();
				requestFocus();
			}
			
		});
		add(EButton);
		
		JButton RButton=new JButton();
		RButton.setBounds(Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL*3, Data.SKILL_BUTTON_POSITION_Y, 
				Data.SKILL_BUTTON_WIDTH, Data.SKILL_BUTTON_HEIGHT);
		RButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player.pressR();
				requestFocus();
			}
			
		});
		add(RButton);
		
		backgroundImg11=backgroundImg12=ReadImageUtil.getBackgroundImg(round);//����ͼƬ
		
		try {
			Lock.getSemaphore().acquire();
			Lock.setSuspend(false);
			Lock.getSemaphore().release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Object lock=Lock.getLock();
		synchronized (lock) {
			lock.notifyAll();
		}
//		Lock.restart();
		addKeyListener(new KeyMonitor());
		
		JLabel label=new JLabel();
		label.setBounds(Data.WINDOW_POSITION_X, Data.WINDOW_POSITION_Y, 
				Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
		label.addMouseMotionListener(new MouseMonitor());
		add(label);
		
		requestFocus();
		new DrawThread().start();
		new HandleDataThread().start();
	}
	
	
	@Override
	public  void paint(Graphics g) {

		if(background_y1>=Data.WINDOW_HEIGHT)
			background_y1=-Data.WINDOW_HEIGHT;
		if(background_y2>=Data.WINDOW_HEIGHT)
			background_y2=-Data.WINDOW_HEIGHT;
		background_y1+=Data.BACKGROUND_SPEED_Y;
		background_y2+=Data.BACKGROUND_SPEED_Y;
		g.drawImage(backgroundImg11, 0, background_y1, Data.WINDOW_WIDTH,
						Data.WINDOW_HEIGHT, null);
		g.drawImage(backgroundImg12, 0, background_y2, Data.WINDOW_WIDTH,
						Data.WINDOW_HEIGHT, null);
		//头像框
		g.drawImage(ReadImageUtil.headPortraitFrameImg, 0, 0, Data.HEAD_PORTRAIT_FRAME_WIDTH,
				Data.HEAD_PORTRAIT_FRAME_HEIGHT, null);
		//
		g.drawImage(ReadImageUtil.headPortraitImg, Data.HEAD_PORTRAIT_X, Data.HEAD_PORTRAIT_Y,
				Data.HEAD_PORTRAIT_WIDTH, Data.HEAD_PORTRAIT_HEIGHT, null);
		//
		g.drawImage(ReadImageUtil.menuImg, Data.MENU_BUTTON_X, Data.MENU_BUTTON_Y,
				Data.MENU_BUTTON_WIDTH, Data.MENU_BUTTON_HEIGHT, null);
		//
		g.drawImage(ReadImageUtil.fireworkImg, Data.SKILL_BUTTON_POSITION_X, Data.SKILL_BUTTON_POSITION_Y, 
				Data.SKILL_BUTTON_WIDTH, Data.SKILL_BUTTON_HEIGHT, null);
		if(Clock.getTime()-player.getPressQTimeBefore()<Data.SKILL_Q_COOLING_TIME) {//Q������ȴ��ʾ
			int cooling_time=Data.SKILL_Q_COOLING_TIME-Clock.getTime()+player.getPressQTimeBefore();
			int height=cooling_time*Data.SKILL_BUTTON_HEIGHT/Data.SKILL_Q_COOLING_TIME;
			g.drawImage(ReadImageUtil.coolingImg, Data.SKILL_BUTTON_POSITION_X, 
					Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT-height, 
				Data.SKILL_BUTTON_WIDTH, height, null);
			g.setFont(new Font("SansSerif", Font.PLAIN, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FONT_SIZE*2));
			g.setColor(Color.GREEN);
			g.drawString(""+cooling_time/Data.REFALSH_INTERVAL, Data.SKILL_BUTTON_POSITION_X, 
					Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT/2);
		}
		
		g.drawImage(ReadImageUtil.laserImg, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL,
				Data.SKILL_BUTTON_POSITION_Y, 
				Data.SKILL_BUTTON_WIDTH, Data.SKILL_BUTTON_HEIGHT, null);
		if(Clock.getTime()-player.getPressWTimeBefore()<Data.SKILL_W_COOLING_TIME) {//w������ȴ��ʾ
			int cooling_time=Data.SKILL_W_COOLING_TIME-Clock.getTime()+player.getPressWTimeBefore();
			int height=cooling_time*Data.SKILL_BUTTON_HEIGHT/Data.SKILL_W_COOLING_TIME;
			g.drawImage(ReadImageUtil.coolingImg, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL, 
					Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT-height, 
				Data.SKILL_BUTTON_WIDTH, height, null);
			g.setFont(new Font("SansSerif", Font.PLAIN, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FONT_SIZE*2));
			g.setColor(Color.blue);
			g.drawString(""+cooling_time/Data.REFALSH_INTERVAL, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL, 
					Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT/2);
		}
		if(player.isLaunchingLaser()) {
			int useTime=Clock.getTime()-player.getPressWTimeBefore()<Data.SKILL_W_MAX_TIME?(Clock.getTime()-player.getPressWTimeBefore()):Data.SKILL_W_MAX_TIME;
			int height=useTime*Data.SKILL_BUTTON_HEIGHT/Data.SKILL_W_MAX_TIME;
				g.drawImage(ReadImageUtil.storePower,  Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL, 
						Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT-height, 
						Data.SKILL_BUTTON_WIDTH, height, null);
		}
		
		g.drawImage(ReadImageUtil.shield, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL*2,
				Data.SKILL_BUTTON_POSITION_Y, 
				Data.SKILL_BUTTON_WIDTH,
				Data.SKILL_BUTTON_HEIGHT, null);
		if(Clock.getTime()-player.getPressETimeBefore()<Data.SKILL_E_COOLING_TIME) {
			int cooling_time=Data.SKILL_E_COOLING_TIME-Clock.getTime()+player.getPressETimeBefore();
			int height=cooling_time*Data.SKILL_BUTTON_HEIGHT/Data.SKILL_E_COOLING_TIME;
			g.drawImage(ReadImageUtil.coolingImg, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL*2, 
					Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT-height, 
				Data.SKILL_BUTTON_WIDTH, height, null);
			g.setFont(new Font("SansSerif", Font.PLAIN, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FONT_SIZE*2));
			g.setColor(Color.YELLOW);
			g.drawString(""+cooling_time/Data.REFALSH_INTERVAL, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL*2, 
					Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT/2);
		}
		
		g.drawImage(ReadImageUtil.atomicBombButtonImg, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL*3,
				Data.SKILL_BUTTON_POSITION_Y, 
				Data.SKILL_BUTTON_WIDTH, Data.SKILL_BUTTON_HEIGHT, null);
		if(Clock.getTime()-player.getPressRTimeBefore()<Data.SKILL_R_COOLING_TIME) {//r������ȴ��ʾ
			int cooling_time=Data.SKILL_R_COOLING_TIME-Clock.getTime()+player.getPressRTimeBefore();
			int height=cooling_time*Data.SKILL_BUTTON_HEIGHT/Data.SKILL_R_COOLING_TIME;
			g.drawImage(ReadImageUtil.coolingImg, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL*3, 
					Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT-height, 
				Data.SKILL_BUTTON_WIDTH, height, null);
			g.setFont(new Font("SansSerif", Font.PLAIN, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FONT_SIZE*2));
			g.setColor(Color.RED);
			g.drawString(""+cooling_time/Data.REFALSH_INTERVAL, Data.SKILL_BUTTON_POSITION_X+Data.SKILL_BUTTON_INTERVAL*3, 
					Data.SKILL_BUTTON_POSITION_Y+Data.SKILL_BUTTON_HEIGHT/2);
		}
		
		
		
		
		int bloodWidth=player.getBlood()>0?player.getBlood()*Data.BLOOD_STRIP_MAX_WIDTH/player.getMax_blood():0;
		g.drawImage(ReadImageUtil.bloodStripImg, Data.BLOOD_STRIP_X+bloodWidth, Data.BLOOD_STRIP_Y,
				Data.BLOOD_STRIP_MAX_WIDTH-bloodWidth, Data.BLOOD_STRIP_HEIGHT, null);
		g.setFont(new Font("SansSerif",Font.CENTER_BASELINE,Data.FONT_SIZE-10));
		g.setColor(Color.yellow);
		g.drawString(" HP", Data.BLOOD_CHARACERS_POSITION_X, Data.BLOOD_CHARACERS_POSITION_Y-Data.FONT_SIZE/2);
		
		
		g.drawImage(ReadImageUtil.expStripImg, Data.EXP_STRIP_X, Data.EXP_STRIP_Y,
				Data.EXP_STRIP_MAX_WIDTH, Data.EXP_STRIP_HEIGHT, null);
		int currentExpWidth=Data.EXP_STRIP_MAX_WIDTH*player.getExp()/Data.PLAYER_UPGRADE_EXP[player.getGrade()];
		g.drawImage(ReadImageUtil.bloodStripImg, Data.EXP_STRIP_X+currentExpWidth, Data.EXP_STRIP_Y, 
				Data.EXP_STRIP_MAX_WIDTH-currentExpWidth, Data.EXP_STRIP_HEIGHT, null);
		g.drawString("EXP", Data.EXP_CHARACERS_POSITION_X, Data.EXP_CHARACERS_POSITION_Y-Data.FONT_SIZE/2);
		g.setColor(Color.MAGENTA);
		g.drawString(""+player.getExp()+" / "+Data.PLAYER_UPGRADE_EXP[player.getGrade()], Data.EXP_STRIP_X, Data.EXP_STRIP_Y+Data.FONT_SIZE/2);
		g.setColor(Color.white);
		g.drawString(""+player.getBlood()+" / "+player.getMax_blood(), Data.BLOOD_STRIP_X, Data.BLOOD_STRIP_Y+Data.FONT_SIZE/2);
		
		player.drawPlayerPlane(g);
		Iterator<?> it=enemys.iterator();
		BOSS.drawEnemyPlane(g);
		while(it.hasNext()) {
			Enemy e=(Enemy) it.next();
			e.drawEnemyPlane(g);
		}
		it=props.iterator();
		while(it.hasNext()) {
			Prop p=(Prop)it.next();
			p.drawProp(g);
		}
		it=enemyShells.iterator();
		while(it.hasNext()) {
			Shell s=(Shell)it.next();
			s.drawEnemyShell(g);
		}
		it=playerShells.iterator();
		while(it.hasNext()) {
			Shell s=(Shell)it.next();
			s.drawPlayerShell(g);
		}
		it=lightWaves.iterator();
		while(it.hasNext()) {
			LightWave l=(LightWave) it.next();
			l.draw(player, g);
		}
		it=effects.iterator();
		while(it.hasNext()) {
			SpecialEffects s=(SpecialEffects) it.next();
			s.drawExplode(g);
		}
		
		if(BOSS!=null&&BOSS.isAlive()) {
			g.drawImage(ReadImageUtil.bossBloodFrameImg, Data.BOSS_HEAD_PORTRAIT_X, Data.BOSS_HEAD_PORTRAIT_Y,
					Data.BOSS_HEAD_PORTRAIT_WIDTH, Data.BOSS_HEAD_PORTRAIT_HEIGHT, null);
			int BossBloodStripWidth=Data.BOSS_BLOOD_STRIP_MAX_WIDTH*BOSS.getBlood()/BOSS.getMax_blood();
			g.drawImage(ReadImageUtil.bossBloodStripImg, Data.BOSS_BLOOD_STRIP_X, Data.BOSS_BLOOD_STRIP_Y,
					BossBloodStripWidth, Data.BOSS_BLOOD_STRIP_HEIGHT, null);
			g.drawImage(BOSS.getImage(), Data.BOSS_HEAD_PORTRAIT_IMG_X, Data.BOSS_HEAD_PORTRAIT_IMG_Y,
					Data.BOSS_HEAD_PORTRAIT_IMG_WIDTH, Data.BOSS_HEAD_PORTRAIT_IMG_HEIGHT, null);
		}
		
		Iterator<SpecialEffects> effectsIt=effects.iterator();
		while(effectsIt.hasNext())
			((SpecialEffects) effectsIt.next()).drawExplode(g);
		
		if(!player.isAlive()||isBossMode&&(!BOSS.isAlive()||BOSS.getBlood()<=0)) {
			try {
				player.setScoreFromBoss(player.getScoreFromBoss()+BOSS.getMax_blood()-BOSS.getBlood());
				this.dispose();
				EndDialog dialog = new EndDialog(player.getKills(),player.getScoreFromBoss(),player.getCoin(),startTime,
						player.isAlive(),isBossMode,BOSS);
				dialog.setVisible(true);
				Lock.getSemaphore().acquire();
				Lock.setSuspend(true);
				Lock.getSemaphore().release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(Clock.getTime()%100==0)
			System.out.println("Enemy :"+enemys.size()+"  player shells:"+playerShells.size()+"  Enemy shells:"+enemyShells.size());
		if(!isBossMode&&!BOSS.isAlive()&&BOSS.getBlood()<=0) {
			player.setScoreFromBoss(player.getScoreFromBoss()+BOSS.getMax_blood());
			new Init(player,(PropsQueue)props,startTime,round+1).start();
			this.dispose();
		}
	}


	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_P) {
				try {
					StopDialog stopDialog = new StopDialog(gameFrame,player,BOSS);
					stopDialog.setVisible(true);
//					Lock.getSemaphore().acquire();
					Lock.setSuspend(true);
//					Lock.getSemaphore().release();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return;
			}
			player.changePositionAndState(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			player.cancelPositionAndState(e);
		}
	}

	class MouseMonitor extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			player.changePositionAndState(e);
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			player.changePositionAndState(e);
		}
	}
	

	class DrawThread extends Thread{
		@Override
		public void run() {
			while(true) {
				try {
					Lock.getSemaphore().acquire();
					if(Lock.isSuspend()) {
						Lock.getSemaphore().release();
						Object lock=Lock.getLock();
						synchronized (lock) {
							lock.wait();
						}
						requestFocus();
					}else
						Lock.getSemaphore().release();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				repaint();
				try {
					sleep(Data.REFALSH_INTERVAL);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	class HandleDataThread extends Thread{
		@Override
		public void run() {
			while(true) {
				try {
					Lock.getSemaphore().acquire();
					if(Lock.isSuspend()) {
						Lock.getSemaphore().release();
						Object lock=Lock.getLock();
						synchronized (lock) {
							lock.wait();
						}
						requestFocus();
					}else
						Lock.getSemaphore().release();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Crash.crash(player,enemys,(PropsQueue)props);
				try {
					sleep(Data.REFALSH_INTERVAL/2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	private Image offScreenImage=null;
	public void update(Graphics g) {
		synchronized (g) {
			if(offScreenImage==null)
				offScreenImage=this.createImage(Data.WINDOW_WIDTH,Data.WINDOW_HEIGHT);
			Graphics gOff=offScreenImage.getGraphics();
			paint(gOff);
			g.drawImage(offScreenImage, 0, 0, null);
		}
	}

	
}
