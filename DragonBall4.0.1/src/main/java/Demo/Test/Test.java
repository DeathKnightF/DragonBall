package Demo.Test;

import java.io.IOException;

import Demo.Net.Reader;

public class Test {

	public static void main(String[] args) {
//		try {
//			System.out.println(Reader.signIn("a", "a"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		double t=86*2+85*2+79*2+82+93*6+82*3.5+66*3+85+80*3+80*2+82+97*2+83*5.5+88*4+87*3+90*0.5+80*0.5+97*2.5+80+60*2+
				91*3+64*2+83*6+81*5+97*4.5+71*2+93*4+80*3+87*4+92*3.5+74*3+81+81+87*3+85*0.5+87*2.5;
		double i=2+2+2+1+6+3.5+3+1+3+2+1+2+5.5+4+3+0.5+0.5+2.5+1+2+
				3+2+6+5+4.5+2+4+3+4+3.5+3+1+1+3+0.5+2.5;
	
		System.out.println(t+" "+i+" "+t/i);
	}

}
