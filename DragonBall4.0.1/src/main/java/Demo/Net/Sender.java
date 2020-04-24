package Demo.Net;

import java.io.IOException;

import Demo.Data.Protocol;

public class Sender {
	

	public static void queryCoins(int id) {
		String data=Protocol.QUERY_COINS_FLAG+Protocol.FILLER+id;
		try {
			TCPClient.send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void queryTopScore(int id) {
		String data=Protocol.QUERY_TOP_SCORE_FLAG+Protocol.FILLER+id;
		try {
			TCPClient.send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void queryTopScoreBoss(int id) {
		String data=Protocol.QUERY_TOP_SCORE_BOSS_FLAG+Protocol.FILLER+id;
		try {
			TCPClient.send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateCoins(int id,int addition) {
		String data=Protocol.UPDATE_COINS_FLAG+Protocol.FILLER+id+Protocol.FILLER+addition;
		try {
			TCPClient.send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateTopScore(int id,int score) {
		String data=Protocol.UPDATE_TOP_SCORE_FLAG+Protocol.FILLER+id+Protocol.FILLER+score;
		try {
			TCPClient.send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateTopScoreBoss(int id,int score) {
		String data=Protocol.UPDATE_TOP_SCORE_BOSS_FLAG+Protocol.FILLER+id+Protocol.FILLER+score;
		try {
			TCPClient.send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
