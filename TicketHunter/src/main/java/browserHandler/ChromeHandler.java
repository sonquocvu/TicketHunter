package browserHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.*;

public class ChromeHandler extends BrowserHandler{
	
	public ChromeHandler()
	{
		super();
		setup();
		System.out.println("Initilinze ChromeHandler");
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
		this.m_chromeDriver = new ChromeDriver(chromeOptions);
	}
	
	public void start()
	{
		// Access web page
		boolean isExceptionCatched = false;
		while (true)
		{
			try
			{
				isExceptionCatched = false;
				this.m_chromeDriver.get("https://ticketbox.vn/event/kich-thanh-xa-bach-xa-86983?opm=tbox.searchlistcategory.list.7");
				waiting_machine(this.m_chromeDriver, 3, By.xpath("//h3[contains(text(), 'Lịch sự kiện')]"));
			}
			catch (Exception e)
			{
				isExceptionCatched = true;
				System.out.println("Cannot access web page due to timeout. Let's try again.");
			}
			finally
			{
				if (!isExceptionCatched)
				{
					System.out.println("Access web page successfully.");
					break;
				}
			}
		}
		
		// Look for opening event to buy ticket
		List<WebElement> events;
		while (true)
		{
			events = this.m_chromeDriver.findElements(By.xpath("//a[contains(@data-href, '/event/')]"));
			if (events.size() > 0)
			{
				System.out.println("Find out opening events. Let's select event in the next move.");
				break;
			}
			else
			{
				try
				{
					this.m_chromeDriver.navigate().refresh();
					waiting_machine(this.m_chromeDriver, 10, By.xpath("//h3[contains(text(), 'Lịch sự kiện')]"));
				}
				catch (Exception e)
				{
					System.out.println("Cannot refresh web page due to timeout.");
				}
			}
		}
		
		// Select random event to buy ticket
		while (true)
		{
			int event_pos = getRandomValue(0, events.size());
			try
			{
				isExceptionCatched = false;
				events.get(event_pos).click();
				waiting_machine(this.m_chromeDriver, 5, By.xpath("//a[contains(text(), 'Nhập mã giảm giá')]"));
			}
			catch (Exception e)
			{
				isExceptionCatched = true;
				System.out.println("Cannot select event due to timeout. Let's pick another event.");
			}
			finally
			{
				if (!isExceptionCatched)
				{
					System.out.println("Select event successfully. Let's select seats in the next move.");
					break;
				}
			}
		}
		
		//this.m_chromeDriver.quit();
	}
	
	private
		WebDriver m_chromeDriver;

}
 