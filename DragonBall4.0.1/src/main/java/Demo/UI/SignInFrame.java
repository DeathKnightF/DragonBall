package Demo.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Demo.Data.Data;
import Demo.Data.Protocol;
import Demo.Net.Reader;
import Demo.Util.ReadPlayerDataUtil;
import Demo.Util.ReadSoundUtil;

public class SignInFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameTextField;
	private JTextField passWordTextField;
	public SignInFrame() {
		setTitle("version:  Dragon Ball 4.0.0");
		setBounds(Data.WINDOW_POSITION_X, Data.WINDOW_POSITION_Y+Data.WINDOW_HEIGHT/4, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT/2);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i=JOptionPane.showConfirmDialog(passWordTextField,"Do you want to close the game?","",JOptionPane.YES_NO_OPTION);
				if(i==JOptionPane.YES_OPTION) {
					System.exit(0);
				}else {
					return;
				}
			}
		});
		
		contentPane=new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel backgroundLabel=new JLabel();
		backgroundLabel.setIcon(new ImageIcon("Imgs/backgroundImg/SignInFrame.jpg"));
		backgroundLabel.setBounds(-400, 0, Data.WINDOW_WIDTH+400, Data.WINDOW_HEIGHT/2);
		//将label放入frame的底层
	    getLayeredPane().add(backgroundLabel,Integer.MIN_VALUE);
//		contentPane.add(backgroundLabel);
		
		
		JLabel userNameLabel=new JLabel("Username");
		userNameLabel.setFont(new Font( "SansSerif", Font.PLAIN,Data.FONT_SIZE));
		userNameLabel.setBounds(Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH/2, Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_Y,
				Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH, Data.SIGNIN_FRAME_TEXT_FIELD_HEIGHT);
		userNameLabel.setForeground(Color.yellow);
		getLayeredPane().add(userNameLabel,0);

		JLabel passWordLabel=new JLabel("Password");
		passWordLabel.setFont(new Font( "SansSerif", Font.BOLD,Data.FONT_SIZE));
		passWordLabel.setBounds(Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH/2, Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_Y+Data.SIGNIN_FRAME_TEXT_FIELD_HEIGHT*3/2,
				Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH, Data.SIGNIN_FRAME_TEXT_FIELD_HEIGHT);
		passWordLabel.setForeground(Color.yellow);
		getLayeredPane().add(passWordLabel,1);
		
		userNameTextField=new JTextField(20);
		userNameTextField.setBounds(Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_X, Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_Y,
				Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH, Data.SIGNIN_FRAME_TEXT_FIELD_HEIGHT);
		userNameTextField.setBackground(null);
		userNameTextField.setOpaque(false);
		userNameTextField.setBorder(null);
		userNameTextField.requestFocus();
		contentPane.add(userNameTextField);
		
		passWordTextField=new JTextField(20);
		passWordTextField.setBounds(Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_X, Data.SIGNIN_FRAME_TEXT_FIELD_POSITION_Y+Data.SIGNIN_FRAME_TEXT_FIELD_HEIGHT*3/2,
				Data.SIGNIN_FRAME_TEXT_FIELD_WIDTH, Data.SIGNIN_FRAME_TEXT_FIELD_HEIGHT);
		passWordTextField.setBackground(null);
		passWordTextField.setOpaque(false);
		passWordTextField.setBorder(null);
		contentPane.add(passWordTextField);
		
		JButton signInButton=new JButton("Sign In");
		signInButton.setBounds(Data.SIGNIN_FRAME_BUTTON_FIRST_POSITION_X, Data.SIGNIN_FRAME_BUTTON_POSTION_Y, Data.SIGNIN_FRAME_BUTTON_WIDTH, Data.SIGNIN_FRAME_BUTTON_HEIGHT);
		signInButton.setBorder(null);
		signInButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userNameTextField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(passWordTextField, "The username cannot be empty!");
					userNameTextField.requestFocus();
					return;
				}
				if(passWordTextField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(passWordTextField, "The password cannot be empty!");
					passWordTextField.requestFocus();
					return;
				}
				int state=0;
				try {
					state=Reader.signIn(userNameTextField.getText().trim(), passWordTextField.getText().trim());
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(passWordTextField, "Failed to connect with server ,please use Singer Player!");
					return;
				}
				if(state==Protocol.SERVER_ERROR) {
					JOptionPane.showMessageDialog(passWordTextField, "Something wrong with server ,please use Singer Player!");
					return;
				}
				if(state==Protocol.USER_NAME_OR_PASSWORD_ERROR) {
					JOptionPane.showMessageDialog(passWordTextField, "The user name or password error!");
					userNameTextField.requestFocus();
					return;
				}
				dispose();
				EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						try {
							MainMenuFrame frame = new MainMenuFrame();
							ReadSoundUtil.loop();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
//		contentPane.add(signInButton);
		getLayeredPane().add(signInButton,0);
		
		JButton signUpButton=new JButton("Sign Up");
		signUpButton.setBounds(Data.SIGNIN_FRAME_BUTTON_FIRST_POSITION_X+Data.SIGNIN_FRAME_BUTTON_INTERVAL, Data.SIGNIN_FRAME_BUTTON_POSTION_Y, Data.SIGNIN_FRAME_BUTTON_WIDTH, Data.SIGNIN_FRAME_BUTTON_HEIGHT);
		signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userNameTextField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(passWordTextField, "The username cannot be empty!");
					userNameTextField.requestFocus();
					return;
				}
				if(passWordTextField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(passWordTextField, "The password cannot be empty!");
					passWordTextField.requestFocus();
					return;
				}
				int state=0;
				try {
					state=Reader.signUp(userNameTextField.getText().trim(), passWordTextField.getText().trim());
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(passWordTextField, "Failed to connect with server ,please use Singer Player!");
					return;
				}
				if(state==Protocol.SERVER_ERROR) {
					JOptionPane.showMessageDialog(passWordTextField, "Something wrong with server ,please use Singer Player!");
					return;
				}
				if(state==Protocol.USER_NAME_IS_REPETITION) {
					JOptionPane.showMessageDialog(passWordTextField, "This user name has been used, please enter again!");
					userNameTextField.setText("");
					userNameTextField.requestFocus();
					return;
				}
				dispose();
				EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						try {
							MainMenuFrame frame = new MainMenuFrame();
							ReadSoundUtil.loop();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
//		contentPane.add(signUpButton);
		getLayeredPane().add(signUpButton,0);
		
		JButton visitorSignInButton=new JButton("Singer Player");
		visitorSignInButton.setBounds(Data.SIGNIN_FRAME_BUTTON_FIRST_POSITION_X+Data.SIGNIN_FRAME_BUTTON_INTERVAL*2, Data.SIGNIN_FRAME_BUTTON_POSTION_Y, Data.SIGNIN_FRAME_BUTTON_WIDTH, Data.SIGNIN_FRAME_BUTTON_HEIGHT);
		visitorSignInButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Reader.setVisitorSignIn(true);
				dispose();
				EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						try {
							ReadPlayerDataUtil.readFromFile();
							MainMenuFrame frame = new MainMenuFrame();
							ReadSoundUtil.loop();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
//		contentPane.add(visitorSignInButton);
		getLayeredPane().add(visitorSignInButton,0);
		
		JButton setButton=new JButton("Set");
		setButton.setBounds(Data.SIGNIN_FRAME_BUTTON_FIRST_POSITION_X+Data.SIGNIN_FRAME_BUTTON_INTERVAL*3, Data.SIGNIN_FRAME_BUTTON_POSTION_Y, Data.SIGNIN_FRAME_BUTTON_WIDTH, Data.SIGNIN_FRAME_BUTTON_HEIGHT);
		setButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SetServerIPFrame().setVisible(true);
			}
		});
		getLayeredPane().add(setButton,0);
	}
}
