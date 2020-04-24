package Demo.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import Demo.Data.Protocol;
import Demo.Service.UserService;

public class TCPServer implements Runnable{
	static final int PORT=8888;
	BufferedReader br;
	ServerSocket server;
	HashMap<Integer,String> map;
	public TCPServer() {
		map=new HashMap<Integer, String>();
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws IOException {
		System.out.println("The server starts successfully!");
		server=new ServerSocket(PORT);
	}

	@Override
	public void run() {
		Socket socket = null;
		while(true) {
			try {
				System.out.println("Wait to connect...");
				socket=server.accept();
				new Thread(new ServerThread(socket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Serving users
	class ServerThread implements Runnable{
		BufferedReader br;
		BufferedWriter bw;
		Socket socket;
		public ServerThread(Socket socket) {
			try {
				br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.socket=socket;
		}
		@Override
		public void run() {
			String data="";
			System.out.println("loading data...");
			try {
				while(!(data=br.readLine()).startsWith(Protocol.TCPSERVER_DISCONNECT_FLAG)) {
					System.out.println(data);
					updateOrQueryDatabase(data);
				}
				String[] parts=data.split(Protocol.FILLER);
				map.remove(Integer.parseInt(parts[1]));
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				System.out.println("A user disconnected!");
				close();
			}
		}
		
		private void close() {
			try {
				if(br!=null)
					br.close();
				if(bw!=null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void updateOrQueryDatabase(String data) {
			String[] parts=data.split(Protocol.FILLER);
			int id;
			switch(parts[0]) {
				case Protocol.SIGN_IN_FLAG:
					id=UserService.signIn(parts[1], parts[2]);
					if(id>0) {
						map.put(id,parts[1]);
					}
					write((Protocol.ID_FLAG+Protocol.FILLER+id));
					return;
				case Protocol.SIGN_UP_FLAG:
					id=UserService.signUp(parts[1], parts[2]);
					if(id>0) {
						map.put(id,parts[1]);
					}
					write((Protocol.ID_FLAG+Protocol.FILLER+id));
					return;
				case Protocol.QUERY_COINS_FLAG:
					write((Protocol.COINS_FLAG+Protocol.FILLER+UserService.getCoins(Integer.parseInt(parts[1]))));
					return;
				case Protocol.QUERY_TOP_SCORE_FLAG:
					write((Protocol.TOP_SCORE_FLAG+Protocol.FILLER+UserService.getTopScore(Integer.parseInt(parts[1]))));
					return;
				case Protocol.QUERY_TOP_SCORE_BOSS_FLAG:
					write((Protocol.TOP_SCORE_BOSS_FLAG+Protocol.FILLER+UserService.getTopScoreBoss(Integer.parseInt(parts[1]))));
					return;
				case Protocol.UPDATE_COINS_FLAG:
					UserService.updateCoins(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					return;
				case Protocol.UPDATE_TOP_SCORE_FLAG:
					UserService.updateTopScore(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					return;
				case Protocol.UPDATE_TOP_SCORE_BOSS_FLAG:
					UserService.updateTopScoreBoss(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					return;
				default:
					return;
			}
		}
		private void write(String data) {
			try {
				bw.write(data);
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				System.out.println("Failed to return data:"+data);
				e.printStackTrace();
			}
		}
	}
}
