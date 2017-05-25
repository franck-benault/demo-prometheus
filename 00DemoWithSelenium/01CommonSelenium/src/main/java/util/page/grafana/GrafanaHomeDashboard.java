package util.page.grafana;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import util.page.Page;

public class GrafanaHomeDashboard extends Page {
	

	


	public GrafanaHomeDashboard(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		
	}

	public void checkPage() {
		System.out.println(driver.getPageSource());
		assertTrue(driver.getPageSource().contains("Home"));
		assertEquals(driver.getTitle(),"Grafana");
	}

	public GrafanaAddDataSource addDataSource() {
		return new GrafanaAddDataSource(
				"http://localhost:3000/datasources/new?gettingstarted" , driver);

		
	}
	
	
	
}
