package Demo.Util;

import Demo.Data.Data;


public class Clock{
	private static volatile InnerClock ic;
	private Clock() {
		
	}
	static class InnerClock extends Thread{
		public int time;
		private InnerClock() {
			time=0;
			this.start();
		}
		@Override
		public void run() {
			while(true) {
				try {
					Lock.getSemaphore().acquire();
					if(Lock.isSuspend()) {
						Lock.getSemaphore().release();
						Object lock=Lock.getLock();
						synchronized (lock) {
							lock.wait();
						}
					}else
						Lock.getSemaphore().release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				Lock.stop();
				time++;
				try {
					sleep(Data.REFALSH_INTERVAL);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private static InnerClock getInnerClock() {
		if(ic!=null)
			return ic;
		synchronized (Clock.class) {
			if(ic==null)
				ic=new InnerClock();
		}
		return ic;
	}
	public static int getTime() {
		return getInnerClock().time;
	}
}
