package Demo.Net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Demo.Data.Data;
import Demo.Data.Protocol;

public class TCPClient {
	private static int id;
	private static volatile Socket socket;
	private static volatile BufferedWriter bw;
	private static volatile BufferedReader br;
	private TCPClient() {
		
	}
	private static Socket getSocket() throws UnknownHostException, IOException {
		System.out.println(""+Data.Target_IP+" "+Data.Port);
		if(socket!=null)
			return socket;
		synchronized (TCPClient.class) {
			if(socket==null)
				socket=new Socket(Data.Target_IP,Data.Port);
		}
		
		return socket;
	}
	private static BufferedWriter getBufferedWriter() throws UnknownHostException, IOException {
		if(bw!=null)
			return bw;
		synchronized (TCPClient.class) {
			if(bw==null)
				bw=new BufferedWriter(new OutputStreamWriter(getSocket().getOutputStream()));
		}
		return bw;
	}
	private static BufferedReader getBufferedReader() throws UnknownHostException, IOException {
		if(br!=null)
			return br;
		synchronized (TCPClient.class) {
			if(br==null)
				br=new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
		}
		return br;
	}
	public static boolean send(String data) throws IOException {
		BufferedWriter bw=getBufferedWriter();
		bw.write(data);
		bw.newLine();
		bw.flush();
		if(data.startsWith(Protocol.TCPSERVER_DISCONNECT_FLAG))
			close();
		return true;
	}
	public static String read() throws UnknownHostException{
		BufferedReader br;
		String data=null;
		try {
			br=getBufferedReader();
			data=br.readLine();
		}  catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	private static void close() {
		try {
			if(socket!=null)
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		TCPClient.id = id;
	}

}
