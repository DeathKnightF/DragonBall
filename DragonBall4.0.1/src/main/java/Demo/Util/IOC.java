package Demo.Util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOC {
	private static volatile ApplicationContext ioc;
	private IOC() {
		
	}
	public static ApplicationContext getApplicationContext() {
		if(ioc!=null)
			return ioc;
		synchronized (IOC.class) {
			if(ioc==null)
				ioc=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		}
		return ioc;
	}
}
