package Demo.Util;

import java.util.concurrent.Semaphore;

public class Lock {
	private static Semaphore semaphore;
	private static boolean suspend;
	private static Object lock;
	static {
		semaphore=new Semaphore(1);
		lock=new Object();
		suspend=false;
	}
	private Lock() {
		
	}

	public static Object getLock() {
		return lock;
	}
	public static boolean isSuspend() {
		return suspend;
	}
	public static Semaphore getSemaphore() {
		return semaphore;
	}	
	public static void setSuspend(boolean suspend) {
		Lock.suspend=suspend;
	}
//	public static void stop() {
//		try {
////			Lock.getSemaphore().acquire();
//			if(Lock.isSuspend()) {
////				Lock.getSemaphore().release();
//				Object lock=Lock.getLock();
//				synchronized (lock) {
//					lock.wait();
//				}
//			}
////			else
////				Lock.getSemaphore().release();
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//	}
//	public static void restart() {
////		try {
////			Lock.getSemaphore().acquire();
////			Lock.getSemaphore().release();
////		} catch (InterruptedException e1) {
////			e1.printStackTrace();
////		}
//		Lock.setSuspend(false);
//		Object lock=Lock.getLock();
//		synchronized (lock) {
//			lock.notifyAll();
//		}
//	}
}
