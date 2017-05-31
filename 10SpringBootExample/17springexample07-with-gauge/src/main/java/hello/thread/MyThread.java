package hello.thread;

import java.util.concurrent.TimeUnit;

import hello.Metrics;
import hello.Util;

public class MyThread extends Thread {
	
	private static final int TEMP_FILE_OK_RATE = 97;

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

			
			if (Util.randomInt(100)<=TEMP_FILE_OK_RATE) {
				Metrics.nbTemporaryFile.labels("handlerA").dec(1);
			}
		}
	}

}
