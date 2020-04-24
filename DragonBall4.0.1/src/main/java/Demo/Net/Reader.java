package Demo.Net;
import java.io.IOException;
import java.net.UnknownHostException;

import Demo.Data.Protocol;

public class Reader {
	private static volatile boolean isVisitorSignIn;
	private static volatile int id=1;
	private static volatile int coins;
	private static volatile int top_score;
	private static volatile int top_score_boss;
	
	public static boolean isVisitorSignIn() {
		return isVisitorSignIn;
	}
	public static void setVisitorSignIn(boolean isVisitSignIn) {
		Reader.isVisitorSignIn = isVisitSignIn;
	}

	public static int signUp(String user_name,String password) throws IOException {
		String data=Protocol.SIGN_UP_FLAG+Protocol.FILLER+user_name+Protocol.FILLER+password;
		TCPClient.send(data);
		id=help();
		return id;
	}

	public static int signIn(String user_name,String password) throws IOException {
		String data=Protocol.SIGN_IN_FLAG+Protocol.FILLER+user_name+Protocol.FILLER+password;
		TCPClient.send(data);
		id=help();
		return id;
	}
	public static int getId() {
		return id;
	}
	public static int getCoins() {
		Sender.queryCoins(id);
		try {
			coins=help();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return coins;
	}
	public static int getTop_score() {
		Sender.queryTopScore(id);
		try {
			top_score=help();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return top_score;
	}
	public static int getTop_score_boss() {
		Sender.queryTopScoreBoss(id);
		try {
			top_score_boss=help();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return top_score_boss;
	}
	private static int help() throws UnknownHostException{
		String data=TCPClient.read();
		System.out.println(data);
		return Integer.parseInt(data.split(Protocol.FILLER)[1]);
	}
}
