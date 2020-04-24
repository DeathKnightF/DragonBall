package Demo.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Demo.Data.Data;

public class SetServerIPFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ipTextField;
	public SetServerIPFrame() {
		setBounds(Data.WINDOW_POSITION_X, Data.WINDOW_POSITION_Y+Data.WINDOW_HEIGHT/4, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT/2);
		setUndecorated(true);
		contentPane=new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel backgroundLabel=new JLabel();
		backgroundLabel.setIcon(new ImageIcon("Imgs/backgroundImg/setFrameBackground.jpg"));
		backgroundLabel.setBounds(-600, 0, Data.WINDOW_WIDTH+600, Data.WINDOW_HEIGHT/2+250);
		//将label放入frame的底层
	    getLayeredPane().add(backgroundLabel,Integer.MIN_VALUE);
	    
	    JLabel ipLabel=new JLabel("服务器ip地址");
		ipLabel.setFont(new Font( "SansSerif", Font.PLAIN,Data.FONT_SIZE-5));
		ipLabel.setBounds(Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH/2, Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_Y,
				Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH, Data.SIGNIN_FRAME_TEXT_FIELD_HEIGHT);
		ipLabel.setForeground(Color.yellow);
		getLayeredPane().add(ipLabel,0);
		
		ipTextField=new JTextField(20);
		ipTextField.setBounds(Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_X, Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_Y,
				Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH, Data.SIGNIN_FRAME_TEXT_FIELD_HEIGHT);
		ipTextField.requestFocus();
		contentPane.add(ipTextField);
		
		JButton conformButton=new JButton("确定");
		conformButton.setBounds(Data.SIGNIN_FRAME_BUTTON_FIRST_POSITION_X+Data.SIGNIN_FRAME_BUTTON_INTERVAL, Data.SIGNIN_FRAME_BUTTON_POSTION_Y, Data.SIGNIN_FRAME_BUTTON_WIDTH, Data.SIGNIN_FRAME_BUTTON_HEIGHT);
		conformButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!ipTextField.getText().trim().equals(""))
					Data.Target_IP=ipTextField.getText().trim();
				dispose();
			}
		});
		getLayeredPane().add(conformButton,0);
		
		JButton backButton=new JButton("返回");
		backButton.setBounds(Data.SIGNIN_FRAME_BUTTON_FIRST_POSITION_X+Data.SIGNIN_FRAME_BUTTON_INTERVAL*2+70, Data.SIGNIN_FRAME_BUTTON_POSTION_Y, Data.SIGNIN_FRAME_BUTTON_WIDTH, Data.SIGNIN_FRAME_BUTTON_HEIGHT);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getLayeredPane().add(backButton,0);
	}
}
