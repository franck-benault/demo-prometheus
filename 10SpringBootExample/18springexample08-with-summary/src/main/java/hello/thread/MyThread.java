package hello.thread;

import hello.Metrics;
import hello.MyProperties;
import hello.util.Util;

import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {
	
	private MyProperties myProperties;
	
	public MyThread(MyProperties myProperties) {
		this.myProperties = myProperties;
	}

	public void run() {
		
		try {
			TimeUnit.SECONDS.sleep(Util.randomInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized(Metrics.nbTemporaryFile) {
			Metrics.nbTemporaryFile.labels("handlerA").inc(1);
		}
		
		try {
			TimeUnit.SECONDS.sleep(Util.randomInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized(Metrics.nbTemporaryFile) {

			
			if (Util.randomInt(100)<=myProperties.getTemp_file_ok_rate()) {
				Metrics.nbTemporaryFile.labels("handlerA").dec(1);
			}
		}
	}

}
