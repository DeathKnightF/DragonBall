package Demo.Server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Demo.Data.Data;
public class ServerFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private TCPServer server;
	public ServerFrame() throws UnknownHostException {
		setBounds(Data.WINDOW_POSITION_X, Data.WINDOW_POSITION_Y, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		InetAddress addr=InetAddress.getLocalHost();
		setTitle("ThunderFighter's Server"+addr.getHostAddress());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i=JOptionPane.showConfirmDialog(null,"Do you want to close this server?","",JOptionPane.YES_NO_OPTION);
				if(i==JOptionPane.YES_OPTION) {
					System.exit(0);
				}else {
					return;
				}
			}
		});

		getContentPane().setLayout(null);
		JLabel label=new JLabel("Online User");
		setFont(new Font("SansSerif",Font.BOLD,Data.FONT_SIZE*2));
		label.setBounds(Data.WINDOW_WIDTH*3/4, Data.WINDOW_POSITION_Y, Data.WINDOW_WIDTH/8, Data.WINDOW_HEIGHT/8);
		getContentPane().add(label);
		textArea=new JTextArea();
		textArea.setBounds(Data.WINDOW_WIDTH*3/4, Data.WINDOW_POSITION_Y+100, Data.WINDOW_WIDTH/4, Data.WINDOW_HEIGHT-200);
		scrollPane=new JScrollPane(textArea);
		scrollPane.setBounds(Data.WINDOW_WIDTH*3/4, Data.WINDOW_POSITION_Y+100, Data.WINDOW_WIDTH/4, Data.WINDOW_HEIGHT-200);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPane);
		
		
		server=new TCPServer();
		JButton reflashButton=new JButton("Reflush");
		reflashButton.setBounds(Data.WINDOW_WIDTH*3/4, Data.WINDOW_POSITION_Y+Data.WINDOW_HEIGHT-Data.WINDOW_HEIGHT/10,
				Data.WINDOW_WIDTH/6, Data.WINDOW_HEIGHT/20);
		reflashButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				for(int id:server.map.keySet())
					textArea.append(""+id+" :"+server.map.get(id));
			}
		});
		getContentPane().add(reflashButton);
		
		
		new Thread(server).start();
		
	}
}
