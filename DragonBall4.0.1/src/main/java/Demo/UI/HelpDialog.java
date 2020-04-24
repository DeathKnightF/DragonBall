package Demo.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;


import Demo.Data.Data;
import Demo.Util.ReadImageUtil;
import javax.swing.JButton;
import javax.swing.ImageIcon;
	/**
	 * 主页面点击帮助按钮跳出的窗口，介绍基本玩法
	 */
public class HelpDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public HelpDialog() {
		setBounds(Data.DIALOG_POSITION_X, Data.DIALOG_POSITION_Y,
				Data.DIALOG_WIDTH, Data.DIALOG_HEIGHT);
		setUndecorated(true);
		getContentPane().setLayout(null);
		
		JButton confirmButton = new JButton();
		confirmButton.setIcon(new ImageIcon(("Imgs/backgroundImg/confirmImg.png")));
		confirmButton.setBounds(Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_POSITION_X,
				Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_POSITION_Y,
				Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_WIDTH,
				Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_HEIGHT);
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(confirmButton);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(ReadImageUtil.helpBackgroundImg, 0, 0, Data.DIALOG_WIDTH, Data.DIALOG_HEIGHT,null);
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		g.setColor(Color.yellow);
		g.drawString("MOVE:          ↑", 100, 100);
		g.drawString("            ← ↓ →", 128, 130);
		g.drawString("FIRE:          SPACE", 100, 200);
		g.drawString("SKILLS:        Q W E R      ", 100, 300);
		g.drawString("The longer you press W, the damage is heighter", 100, 400);
		g.drawString("Pause:           p", 100,500);
		g.drawImage(ReadImageUtil.confirmImg, Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_POSITION_X,
				Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_POSITION_Y,
				Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_WIDTH,
				Data.CHOOSE_BOSS_BACK_TO_MAIN_MENU_BUTTON_HEIGHT, null);
	}
}
