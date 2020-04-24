package Demo.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Demo.Data.Data;
import Demo.Data.PlayerInitData;
import Demo.Object.Container.PropsQueue;
import Demo.Object.PlayerObject.Player;
import Demo.Role.Init;
import Demo.Role.InitBossMode;
import Demo.Util.ReadImageUtil;

import javax.swing.JButton;
import javax.swing.ImageIcon;
/**
 * Choosing the plane
 *
 */
public class ChoosePlayerPlaneFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private int currentChoice=0;
	private PlayerInitData[] playerPlanes;
	/**
	 * Create the frame.
	 */
	public ChoosePlayerPlaneFrame(int mode,int choice) {
		
		setUndecorated(true);
		setBounds(Data.WINDOW_POSITION_X, Data.WINDOW_POSITION_Y, 
				Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
		getContentPane().setLayout(null);
		
		playerPlanes=PlayerInitData.getPlayerPlanes();
		
		JButton confirmButton = new JButton();
		confirmButton.setIcon(new ImageIcon(("Imgs/backgroundImg/confirmImg.png")));
		confirmButton.setBounds(Data.CHOOSE_PLAYER_PLANE_FRAME_CONFIRM_BUTTON_POSITION_X,
				Data.CHOOSE_PLAYER_PLANE_FRAME_CONFIRM_BUTTON_POSITION_Y,
				Data.DIALOG_BUTTON_WIDTH,Data.DIALOG_BUTTON_HEIGHT);
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Player player=new Player( playerPlanes[currentChoice]);
				PropsQueue props=new PropsQueue();
				switch(mode) {
				case 0://general pattern
					new Init(player,props,null,0).start();
					break;
				case 1://boss pattern
					new InitBossMode(choice,player,props).start();
					break;
				}
				
				setVisible(false);
			}
		});
		getContentPane().add(confirmButton);
		
		JButton leftButton = new JButton();
		leftButton.setIcon(new ImageIcon(("Imgs/backgroundImg/leftButton.png")));
		leftButton.setBounds(Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_LEFT_BUTTON_POSITION_X
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_POSITION_Y
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_WIDTH
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_HEIGHT);
		leftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentChoice>0)
					currentChoice--;
				else
					currentChoice=3;
				repaint();
			}
		});
		getContentPane().add(leftButton);
		
		JButton rightButton = new JButton();
		rightButton.setIcon(new ImageIcon(("Imgs/backgroundImg/rightButton.png")));
		rightButton.setBounds(Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_RIGHT_BUTTON_POSITION_X
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_POSITION_Y
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_WIDTH
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_HEIGHT);
		rightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentChoice<3)
					currentChoice++;
				else
					currentChoice=0;
				repaint();
			}
		});
		getContentPane().add(rightButton);
		
		
	}

	@Override
	public void paint(Graphics g) {
		//draw background
		g.drawImage(ReadImageUtil.choosePlayerPlaneBackgroundImg,0, 0, 
				Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT,null);
		//draw the images of three buttons
		g.drawImage(ReadImageUtil.confirmImg, Data.CHOOSE_PLAYER_PLANE_FRAME_CONFIRM_BUTTON_POSITION_X,
				Data.CHOOSE_PLAYER_PLANE_FRAME_CONFIRM_BUTTON_POSITION_Y,
				Data.DIALOG_BUTTON_WIDTH,Data.DIALOG_BUTTON_HEIGHT, null);
		g.drawImage(ReadImageUtil.leftButtonImg, Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_LEFT_BUTTON_POSITION_X
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_POSITION_Y
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_WIDTH
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_HEIGHT,null);
		g.drawImage(ReadImageUtil.rightButtonImg, Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_RIGHT_BUTTON_POSITION_X
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_POSITION_Y
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_WIDTH
				,Data.CHOOSE_PLAYER_PLANE_FRAME_TURN_BUTTON_HEIGHT,null);
		//draw the image of plane
		g.drawImage(playerPlanes[currentChoice].getImg(), Data.CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_POSITION_X
				,Data.CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_POSITION_Y
				,Data.CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_WIDTH
				,Data.CHOOSE_PLAYER_PLANE_FRAME_PLANE_IMG_HEIGHT,null);
		g.setFont(new Font("����", Font.PLAIN, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FONT_SIZE));
		g.setColor(Color.YELLOW);
		//show the data of these plane
		g.drawString(" ATK :", Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_POSITION_X, 
				Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FIRST_POSITION_Y);
		g.drawString("  HP :", Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_POSITION_X, 
				Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FIRST_POSITION_Y+Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_INTERVAL);
		g.drawString("SPEED:", Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_POSITION_X, 
				Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_FIRST_POSITION_Y+Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_WORD_INTERVAL*2);
		int harmLength=playerPlanes[currentChoice].getHarm()*Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_STANDARD_WIDTH/Data.PLAYER_SHELL_HARM;
		int bloodLength=playerPlanes[currentChoice].getBlood()*Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_STANDARD_WIDTH/Data.PLAYER_BLOOD;
		int speedLength=playerPlanes[currentChoice].getSpeed()*Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_STANDARD_WIDTH/Data.PLAYER_SPEED;
		g.drawImage(ReadImageUtil.expStripImg, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_POSITION_X
				, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_FIRST_POSITION_Y
				, harmLength
				, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_HEIGHT
				, null);
		g.drawImage(ReadImageUtil.expStripImg, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_POSITION_X
				, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_FIRST_POSITION_Y+Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_INTERVAL
				, bloodLength
				, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_HEIGHT 
				, null);
		g.drawImage(ReadImageUtil.expStripImg, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_POSITION_X
				, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_FIRST_POSITION_Y+Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_INTERVAL*2
				, speedLength
				, Data.CHOOSE_PLAYER_PLANE_FRAME_DATA_STRIP_HEIGHT
				, null);
	}
}
