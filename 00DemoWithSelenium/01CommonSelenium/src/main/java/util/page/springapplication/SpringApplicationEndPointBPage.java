package util.page.springapplication;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import util.page.Page;

public class SpringApplicationEndPointBPage extends Page {
	
	private static final String url = "http://localhost:8080/endpointB";

	public SpringApplicationEndPointBPage(WebDriver driver) {
		super(driver);
		driver.get(url);
		
		int nbTries =1;
		//springboot
		while(driver.getPageSource().contains("404") && nbTries<4) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.get(url);
			nbTries++;
		}
	}


}
