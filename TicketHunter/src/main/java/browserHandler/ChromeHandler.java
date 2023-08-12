package browserHandler;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.*;

public class ChromeHandler extends BrowserHandler{
	
	public ChromeHandler(String stageName, String rowName, String url, String lastName, String firstName, String email, String phone)
	{
		super(stageName, rowName, url, lastName, firstName, email, phone);
		setup();
	}
	
	protected void setup()
	{
		String user = System.getProperty("user.name");
		String userDataPath = String.format("user-data-dir=C:\\Users\\%s\\AppData\\Local\\Google\\Chrome\\User Data", user);
		System.out.printf("The userDataPath: %s\n", userDataPath);
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-notification");
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments(userDataPath);
		chromeOptions.addArguments("profile-directory=Default");
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking", "enable-automation"));
		this.m_webDriver = new ChromeDriver(chromeOptions);
	}
}
 