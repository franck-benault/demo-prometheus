package hello;

import java.util.Random;

public class Util {

	public static int randomInt(int maxValue) {
    	Random rn = new Random();
    	return rn.nextInt(maxValue) + 1;
	}
	
}
