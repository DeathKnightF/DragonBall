package Demo.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;

import Demo.Data.Data;
import Demo.Data.Protocol;
import Demo.Net.Reader;
import Demo.Net.TCPClient;
import Demo.Util.ReadImageUtil;
import Demo.Util.ReadPlayerDataUtil;
import Demo.Util.ReadSoundUtil;


@SuppressWarnings({ "serial" })
public class MainMenuFrame extends Frame {

	public MainMenuFrame() {
		setBounds(Data.WINDOW_POSITION_X, Data.WINDOW_POSITION_Y, 
				Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
		setUndecorated(true);
		JButton startButton = new JButton();
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoosePlayerPlaneFrame cppf=new ChoosePlayerPlaneFrame(0,0);
				cppf.setVisible(true);
				setVisible(false);
			}
		});
		startButton.setBorderPainted(false);
		startButton.setBounds(Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT);
		add(startButton);
		
		JButton BossButton = new JButton();
		BossButton.setBounds(Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT);
		BossButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChooseBossFrame cbf=new ChooseBossFrame();
				cbf.setVisible(true);
				setVisible(false);
			}
		});
		add(BossButton);
		
		
		JButton helpButton = new JButton();
		helpButton.setBounds(Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*2,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT);
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpDialog hd=new HelpDialog();
				hd.setVisible(true);
			}
		});
		add(helpButton);
		
		JButton soundControlButton = new JButton();
		soundControlButton.setBounds(Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*3,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT);
		soundControlButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				if(ReadSoundUtil.isClosed()) {
					ReadSoundUtil.loop();
				}else {
					ReadSoundUtil.close();
				}
			}
		});
		add(soundControlButton);
		
		JButton productionTeamButton = new JButton();
		productionTeamButton.setBounds(Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*4,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT);
		productionTeamButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductionTeamDialog ptd=new ProductionTeamDialog();
				ptd.setVisible(true);
			}
		});
 		add(productionTeamButton);
		
 		JButton setButton = new JButton();
 		setButton.setBounds(Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*5,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT);
 		setButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				new SetDialog().setVisible(true);
				
			}
		});
 		add(setButton);
 		
 		JButton closeButton = new JButton();
		closeButton.setBounds(Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*6,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT);
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Data of player output to file: "+ReadPlayerDataUtil.writeToFile());
				ReadPlayerDataUtil.close();
				if(!Reader.isVisitorSignIn())
					try {
						TCPClient.send(Protocol.TCPSERVER_DISCONNECT_FLAG+Protocol.FILLER+Reader.getId());//��������Ͽ�����
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally {
						System.exit(0);
					}
				System.exit(0);
			}
		});
		add(closeButton);
		//防止窗口闪烁
		JLabel focus=new JLabel();
		focus.requestFocus();
		add(focus);
		new DrawThread().start();
	}
	private int bgNum=(int)(Math.random()*5);
	private boolean hasFlushedCoins=false;
	private int coins=0;
	@Override
	public void paint(Graphics g) {
		g.drawImage(ReadImageUtil.mainMenuFrameBg[bgNum], 0, 0, 
				Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT, null);
		g.drawImage(ReadImageUtil.coinsImg, Data.COINS_IMG_POSITION_X, Data.COINS_IMG_POSITION_Y,
				Data.COINS_IMG_WIDTH, Data.COINS_IMG_HEIGHT, null);
		g.setFont(new Font( "SansSerif",Font.PLAIN, Data.FONT_SIZE+20));
		g.setColor(Color.YELLOW);
		if(!hasFlushedCoins) {
			hasFlushedCoins=true;
			if(Reader.isVisitorSignIn())
				coins=ReadPlayerDataUtil.getCoin();
			else
				coins=Reader.getCoins();
			System.out.println(coins);
		}
		g.drawString(""+coins, Data.COINS_IMG_POSITION_X+Data.COINS_IMG_WIDTH+Data.FONT_SIZE
					, Data.COINS_IMG_POSITION_Y+Data.FONT_SIZE+10);
		g.drawImage(ReadImageUtil.startButtonImg, Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT, null);
		g.drawImage(ReadImageUtil.bossButtonImg, Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT, null);
		g.drawImage(ReadImageUtil.helpButtonImg, Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*2,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT, null);
		g.drawImage(ReadImageUtil.soundControlButtonImg, Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*3,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT, null);
		g.drawImage(ReadImageUtil.productionTeamButtonImg, Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*4,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT, null);
		g.drawImage(ReadImageUtil.setButton, Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*5,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT, null);
		g.drawImage(ReadImageUtil.closeButtonImg,Data.MAIN_MENU_FRAME_BUTTON_POSITION_X,
				Data.MAIN_MENU_FRAME_BUTTON_POSITION_START_Y+Data.MAIN_MENU_FRAME_BUTTONS_INTERVAL*6,
				Data.MAIN_MENU_FRAME_BUTTON_WIDTH,Data.MAIN_MENU_FRAME_BUTTON_HEIGHT, null);
	}
	
	class DrawThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();
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
