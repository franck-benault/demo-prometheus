package util.page.grafana;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import org.openqa.selenium.WebDriver;

import util.page.Page;

public class GrafanaHomeDashboard extends Page {

	public GrafanaHomeDashboard(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void checkPage() {
		System.out.println(driver.getPageSource());
		assertTrue(driver.getPageSource().contains("Home"));
		assertEquals(driver.getTitle(),"Grafana");
	}
	
	
	
}
