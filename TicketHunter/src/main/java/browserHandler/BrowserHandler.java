package browserHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.HashMap;

abstract class BrowserHandler {
	
	BrowserHandler(WebDriver driver, HashMap<String, String> infor)
	{
		this.m_driver = driver;
		this.m_infor = infor;
	}
	
	abstract void setup();
	
	public void waiting_machine(By element, Duration timeout)
	{
		WebDriverWait wait = new WebDriverWait(this.m_driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	private
		WebDriver m_driver;
		HashMap<String, String> m_infor;
}
