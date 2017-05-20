package util.page.grafana;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import util.page.Page;
import static org.junit.Assert.assertEquals;

public class GrafanaMainPage extends Page {
	
	@FindBy(how=How.NAME, using="username")
	private WebElement username;
	
	@FindBy(how=How.ID, using="inputPassword")
	private WebElement inputPassword;
	

	
	@FindBy(how=How.XPATH, using="(//button[@type='submit'])[1]")
	WebElement loginButton;

	public GrafanaMainPage(WebDriver driver) {
		super(driver);

		driver.get("http://localhost:3000/login");
		System.out.println(driver.getPageSource());
		PageFactory.initElements(driver, this);
	}
	
	public GrafanaHomeDashboard login() {
		this.username.clear();
		this.username.sendKeys("admin");
		
		this.inputPassword.clear();
		this.inputPassword.sendKeys("admin");
		
		loginButton.click();
		
		return new GrafanaHomeDashboard(driver);
	}

	public void checkPage() {
		assertEquals(driver.getTitle(),"Grafana");
		
	}

}
