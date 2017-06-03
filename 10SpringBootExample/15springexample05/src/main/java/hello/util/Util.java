package hello.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Util {
	
	public static List<String> consumeMemory() {
    	List<String> list  = new ArrayList<String>();
    	for(int i=0; i<200000; i++)
    		list.add("eeeeeeeeeeeeeeeeeee"+i);
    	return list;
	}

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
