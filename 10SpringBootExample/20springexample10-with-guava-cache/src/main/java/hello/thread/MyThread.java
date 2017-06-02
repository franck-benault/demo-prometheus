package hello.thread;


import hello.Metrics;
import hello.util.Util;

public class MyThread extends Thread {
	
	private static final int TEMP_FILE_OK_RATE = 97;

	public void run() {
		
		Util.sleepInSeconde(Util.randomInt(10));

		synchronized(Metrics.nbTemporaryFile) {
			Metrics.nbTemporaryFile.labels("handlerA").inc(1);
		}
		
		Util.sleepInSeconde(Util.randomInt(10));

		synchronized(Metrics.nbTemporaryFile) {
			if (Util.randomInt(100)<=TEMP_FILE_OK_RATE) {
				Metrics.nbTemporaryFile.labels("handlerA").dec(1);
			}
		}
	}

}
