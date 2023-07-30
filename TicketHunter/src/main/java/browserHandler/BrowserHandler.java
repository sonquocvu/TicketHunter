package browserHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.ThreadLocalRandom;

import java.time.Duration;
import java.util.HashMap;

public abstract class BrowserHandler {
	
	public BrowserHandler()
	{
		System.out.println("Initialize BrowserHandler");
	}
	
	public int getRandomValue(int Min, int Max)
	{
		return ThreadLocalRandom.current().nextInt(Min, Max);
	}
	
	public void waiting_machine(WebDriver driver, long timeoutInSecond, By element)
	{
		Duration timeout = Duration.ofSeconds(timeoutInSecond);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	abstract protected void setup();
	
	abstract public void start();
}
