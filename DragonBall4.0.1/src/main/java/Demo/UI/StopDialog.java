package Demo.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import Demo.Data.Data;
import Demo.Object.EnemyObject.Enemy;
import Demo.Object.PlayerObject.Player;
import Demo.Util.Lock;
import Demo.Util.ReadImageUtil;
import Demo.Util.ReadSoundUtil;

public class StopDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	GameFrame gameFrame;
	Player player;
	Enemy BOSS;
	
	public StopDialog(
			GameFrame gameFrame,
			Player player,
			Enemy BOSS) {
		setBounds(Data.DIALOG_POSITION_X, Data.DIALOG_POSITION_Y, 
				Data.DIALOG_WIDTH, Data.DIALOG_HEIGHT);
		setUndecorated(true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		this.gameFrame=gameFrame;
		this.player=player;
		this.BOSS=BOSS;
		
		JButton continueButton = new JButton();
		continueButton.setIcon(new ImageIcon(("Imgs/backgroundImg/continueImg.png")));
		continueButton.setBounds(31, 276, 190, 60);
		continueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
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
			}
		});
		getContentPane().add(continueButton);
		
		JButton backToMainMenuButton = new JButton();
		backToMainMenuButton.setBounds(31, 377, 190, 70);
		Image temp=ReadImageUtil.backToMainMenuImg.getScaledInstance(backToMainMenuButton.getWidth(),
				backToMainMenuButton.getHeight(), Image.SCALE_DEFAULT);
		ImageIcon icon=new ImageIcon(temp);
		backToMainMenuButton.setIcon(icon);
		backToMainMenuButton.setIcon(new ImageIcon(("Imgs/backgroundImg/backToMainMenu.png")));
		
		backToMainMenuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenuFrame().setVisible(true);
				setVisible(false);
				gameFrame.dispose();
			}
		});
		getContentPane().add(backToMainMenuButton);
		
		JButton soundControlButton = new JButton();
		soundControlButton.setBounds(31, 495, 190, 60);
		soundControlButton.setIcon(new ImageIcon(("Imgs/backgroundImg/soundControlButton.png")));
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
		getContentPane().add(soundControlButton);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(ReadImageUtil.menuDialogBackgroundImg, 0, 0, Data.DIALOG_WIDTH+200, Data.DIALOG_HEIGHT,  null);
		g.drawImage(ReadImageUtil.menuDialogImg, 0, 0, Data.DIALOG_WIDTH, Data.DIALOG_HEIGHT, null);
		g.drawImage(ReadImageUtil.continueButtonImg, 31, 276, 190, 60, null);
		g.drawImage(ReadImageUtil.backToMainMenuImg, 31, 377, 190, 70, null);
		g.drawImage(ReadImageUtil.soundControlButtonImg, 31, 495, 190, 60, null);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("SansSerif",Font.BOLD,30));
		g.drawString("Harm  :"+player.getHarm(), Data.DIALOG_WIDTH/2-100, 250);
		g.drawString("Kills :"+player.getKills(), Data.DIALOG_WIDTH/2-100, 300);
		if(BOSS.isAlive())
			g.drawString("Blood of Boss:"+BOSS.getBlood(), Data.DIALOG_WIDTH/2-100, 350);
	}
	

}
