package hello.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Util {

	public static int randomInt(int maxValue) {
    	Random rn = new Random();
    	return rn.nextInt(maxValue) + 1;
	}
	
	public static void sleepInMilliSeconde(int nbMilliSeconde) {
		try {
			TimeUnit.MILLISECONDS.sleep(Util.randomInt(nbMilliSeconde));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void sleepInSeconde(int nbSeconde) {
		try {
			TimeUnit.SECONDS.sleep(Util.randomInt(nbSeconde));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
