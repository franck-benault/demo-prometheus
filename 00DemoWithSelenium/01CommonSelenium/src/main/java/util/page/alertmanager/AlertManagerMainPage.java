package util.page.alertmanager;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import util.page.Page;

public class AlertManagerMainPage extends Page {
	
	
	@FindBy(how=How.LINK_TEXT, using="Silences")
	private WebElement silencesLink;

	public AlertManagerMainPage(WebDriver driver) {
		super(driver);

		driver.get("http://localhost:9093/#/alerts");
		//PageFactory.initElements(driver, this);
	}
	
	public void checkPage() {
		assertEquals(driver.getTitle(),"AlertManager – Prometheus");
	}

	public AlertManagerSilencesPage getSilencesPage() {
		driver.get("http://localhost:9093/#/silences");
		return new AlertManagerSilencesPage(driver);
	}

}
