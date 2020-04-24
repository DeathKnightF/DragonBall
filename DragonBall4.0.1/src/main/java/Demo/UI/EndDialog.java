package Demo.UI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import Demo.Data.Data;
import Demo.Net.Reader;
import Demo.Net.Sender;
import Demo.Object.EnemyObject.Enemy;
import Demo.Util.ReadImageUtil;
import Demo.Util.ReadPlayerDataUtil;


/**
 * when player die showing this frame
 *
 */
public class EndDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	
	private Image background;
	private int kills,coins,score;
	private boolean win;
	private boolean isBossMode;
	private Enemy BOSS;
	private int liveTime;
	public EndDialog(int kills,
			int scoreFromBoss,
			int coin,
			Date startTime,
			boolean win,
			boolean isBossMode,
			Enemy BOSS) {
		
		setBounds(Data.DIALOG_POSITION_X, Data.DIALOG_POSITION_Y,
				Data.DIALOG_WIDTH, Data.DIALOG_HEIGHT);
		setUndecorated(true);
		
		background=ReadImageUtil.endDialogImg;
		this.kills=kills;
		this.coins=coin;
		this.win=win;
		this.isBossMode=isBossMode;
		this.BOSS=BOSS;
		getContentPane().setLayout(null);
		
		JButton button=new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MainMenuFrame().setVisible(true);
				setVisible(false); 
			}
		});
		button.setIcon(new ImageIcon(("Imgs/backgroundImg/confirmImg.png")));
		button.setBounds( Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_POSITION_X,
				Data.WINDOW_HEIGHT*3/4,
				Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_WIDTH,
				Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_HEIGHT);
		getContentPane().add(button);
		Date endTime=new Date();
		liveTime=(int)((endTime.getTime()-startTime.getTime())/1000);
		score=liveTime+kills*10+scoreFromBoss;
		coins+=score/10;
	}
	@Override
	public void paint(Graphics g) {
		if(background!=null)
			g.drawImage(background, 0, 0, Data.DIALOG_WIDTH, Data.DIALOG_HEIGHT, null);
		if(!win)
			g.drawImage(ReadImageUtil.failureImg, 230, 0, 100, 170, null);
		g.setFont(new Font("楷体", Font.BOLD,40));
		int topScore=0;
		if(isBossMode) {
			if(!Reader.isVisitorSignIn()) {
				try {
					topScore=Reader.getTop_score_boss();
				}catch(Exception e) {
					System.out.println("Loading from server failed");
				}
				if(topScore<score) {
					topScore=score;
					Sender.updateTopScoreBoss(Reader.getId(), topScore);
				}
			}else {
				topScore=ReadPlayerDataUtil.getTopGradeBoss();
				topScore=Math.max(topScore, score);
				ReadPlayerDataUtil.setTopGradeBoss(Math.max(ReadPlayerDataUtil.getTopGradeBoss(), score));
			}
			g.drawString(" time :"+liveTime,Data.END_DIALOG_POSITION_X,Data.END_DIALOG_FIRST_POSITION_Y);
			g.drawString(" grade:", Data.END_DIALOG_POSITION_X,Data.END_DIALOG_FIRST_POSITION_Y+Data.END_DIALOG_INTERVAL);
			g.drawString(" "+score, Data.END_DIALOG_POSITION_X,
					Data.END_DIALOG_FIRST_POSITION_Y+Data.END_DIALOG_INTERVAL*2);
			g.drawString(""+(BOSS.getMax_blood()-BOSS.getBlood())/10, Data.END_DIALOG_COIN_POSITION_X,
					Data.END_DIALOG_COIN_POSITION_Y);//show coins
			if(!Reader.isVisitorSignIn())
				g.drawString(""+topScore,Data.WINDOW_WIDTH*5/8,Data.WINDOW_HEIGHT*10/11);
			else
				g.drawString(""+ReadPlayerDataUtil.getTopGradeBoss(),Data.WINDOW_WIDTH*5/8,Data.WINDOW_HEIGHT*10/11);
		}else {
			if(!Reader.isVisitorSignIn()) {
				try {
					topScore=Reader.getTop_score();
				}catch(Exception e) {
					System.out.println("Loading top score from server failed!");
				}
				if(topScore<score) {
					topScore=score;
					Sender.updateTopScore(Reader.getId(), topScore);
				}
			}else {
				topScore=ReadPlayerDataUtil.getTopGrade();
				topScore=Math.max(topScore, score);
				ReadPlayerDataUtil.setTopGrade(Math.max(ReadPlayerDataUtil.getTopGrade(), score));
			}
			g.drawString(" kills:"+kills, Data.END_DIALOG_POSITION_X,Data.END_DIALOG_FIRST_POSITION_Y);
			g.drawString(" time :"+liveTime,Data.END_DIALOG_POSITION_X,
					Data.END_DIALOG_FIRST_POSITION_Y+Data.END_DIALOG_INTERVAL);
			g.drawString(" score:", Data.END_DIALOG_POSITION_X,
					Data.END_DIALOG_FIRST_POSITION_Y+Data.END_DIALOG_INTERVAL*2);
			g.drawString(" "+score, Data.END_DIALOG_POSITION_X,
					Data.END_DIALOG_FIRST_POSITION_Y+Data.END_DIALOG_INTERVAL*3);
			g.drawString(""+coins, Data.END_DIALOG_COIN_POSITION_X,
					Data.END_DIALOG_COIN_POSITION_Y);
			if(!Reader.isVisitorSignIn())
				g.drawString(""+topScore,Data.WINDOW_WIDTH*5/8,Data.WINDOW_HEIGHT*10/11);
			else
				g.drawString(""+ReadPlayerDataUtil.getTopGrade(),Data.WINDOW_WIDTH*5/8,Data.WINDOW_HEIGHT*10/11);
		}
		if(!Reader.isVisitorSignIn())
			Sender.updateCoins(Reader.getId(), coins);
		ReadPlayerDataUtil.setCoin(ReadPlayerDataUtil.getCoin()+coins);
	}

}
