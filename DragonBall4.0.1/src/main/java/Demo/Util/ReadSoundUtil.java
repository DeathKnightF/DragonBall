package Demo.Util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ReadSoundUtil {
	private static volatile PlaySound playSound;
	static String backgroundMusicPath;
	static String explodeSoundPath;
	static {
			backgroundMusicPath="Sound/music/backgroundMusic.mp3";
			explodeSoundPath="Sound/sound/explodeSound.wav";
	}
	private ReadSoundUtil() {
		
	}
	static class PlaySound extends Thread{
		Player player;
		
		BufferedInputStream bis;
		volatile boolean isClosed=false;
		PlaySound(){
			
		}
		@Override
		public void run() {
			while(!isClosed) {
				try {
					bis=new BufferedInputStream(new FileInputStream(backgroundMusicPath));
	//				bis=new BufferedInputStream(ReadSoundUtil.class.getResourceAsStream(backgroundMusicPath));
					player=new Player(bis);
					player.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		void loop() {
			if(isClosed) {//����������
				playSound=null;
				ReadSoundUtil.loop();
				return;
			}
			isClosed=false;
			this.start();
		}
		
		void close() {
			isClosed=true;
			player.close();
		}
	}
	
	private static PlaySound getPlaySound() {
		if(playSound!=null)
			return playSound;
		synchronized (ReadSoundUtil.class) {
			if(playSound==null) {
				playSound=new PlaySound();
			}
		}
		return playSound;
	}
	public static void loop() {
		PlaySound ps=getPlaySound();
		ps.loop();
	}
	public static void close() {
		PlaySound ps=getPlaySound();
		ps.close();
	}
	
	public static boolean isClosed() {
		PlaySound ps=getPlaySound();
		return ps.isClosed;
	}
}
