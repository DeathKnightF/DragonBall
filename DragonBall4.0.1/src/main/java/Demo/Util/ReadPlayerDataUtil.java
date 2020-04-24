package Demo.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import Demo.Data.Data;

/**
 * 从本地读取玩家信息
 * @author Administrator
 *
 */
public class ReadPlayerDataUtil {
	private static volatile BufferedReader br;
	private static volatile BufferedWriter bw;
	private static int coin=0;
	private static int topGrade=0;
	private static int topGradeBoss=0;
	private ReadPlayerDataUtil() {
		
	}
	private static BufferedWriter getBufferedWriter() throws FileNotFoundException {
		if(bw!=null)
			return bw;
		synchronized (ReadPlayerDataUtil.class) {
			if(bw==null)
				bw=new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(Data.FILE_PATH)));
		}
		return bw;
	}
	private static BufferedReader getBufferedReader() throws FileNotFoundException {
		if(br!=null)
			return br;
		synchronized (ReadPlayerDataUtil.class) {
			if(br==null)
				br=new BufferedReader(
						new InputStreamReader(
								new FileInputStream(Data.FILE_PATH)));
		}
		return br;
	}
	public static void close() {
		try {
			if(br!=null)
				br.close();
			if(bw!=null)
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean writeToFile() {
		BufferedWriter bw;
		try {
			bw=getBufferedWriter();
			bw.write(new String(""+coin+"\n"));
			bw.write(new String(""+topGrade+"\n"));
			bw.write(new String(""+topGradeBoss+"\n"));
			bw.flush();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	public static boolean readFromFile() {
		BufferedReader br;
		try {
			br=getBufferedReader();
			String value=br.readLine();
			coin=Integer.parseInt(value);
			value=br.readLine();
			topGrade=Integer.parseInt(value);
			value=br.readLine();
			topGradeBoss=Integer.parseInt(value);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	public static int getCoin() {
		return coin;
	}
	public static void setCoin(int coin) {
		ReadPlayerDataUtil.coin = coin;
	}
	public static int getTopGrade() {
		return topGrade;
	}
	public static void setTopGrade(int topGrade) {
		ReadPlayerDataUtil.topGrade = topGrade;
	}
	public static int getTopGradeBoss() {
		return topGradeBoss;
	}
	public static void setTopGradeBoss(int topGradeBoss) {
		ReadPlayerDataUtil.topGradeBoss = topGradeBoss;
	}
	
}
