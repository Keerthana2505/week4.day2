package week4.day2.Assignment;

import java.util.ArrayList;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		// To Download and set the path 
		WebDriverManager.chromedriver().setup();

		// To Launch the chromebrowser
		ChromeDriver driver = new ChromeDriver();

		//To Launch the URL
		driver.get("https://www.nykaa.com/");

		//To Maximise the window
		driver.manage().window().maximize();

		//To add wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		//To Mouseover on Brands and Search L'Oreal Paris 
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).perform();
		WebElement ele = driver.findElement(By.linkText("L'Oreal Paris"));
		wait.until(ExpectedConditions.elementToBeClickable(ele)).click();

		//To Check the title contains L'Oreal Paris(Hint-GetTitle) 
		if (driver.getTitle().contains("L'Oreal Paris"))
		{
			System.out.println("Nykaa page contains L'Oreal Paris products");
		}
		else
		{
			System.out.println("page is not displayed");
		}

		//Click sort By and select customer top rated 
		WebElement sort = driver.findElement(By.xpath("//span[@class='sort-name']"));
		wait.until(ExpectedConditions.elementToBeClickable(sort)).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();

		//Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		WebElement shampoo = driver.findElement(By.xpath("//span[text()='Shampoo']"));
		wait.until(ExpectedConditions.elementToBeClickable(shampoo)).click();

		//Click->Concern->Color Protection 
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		//Check whether the Filter is applied with Shampoo 
		List<WebElement> filterValue = driver.findElements(By.xpath("//div[@class='css-1emjbq5']"));
		System.out.println("Filters applied are :");
		for(int i=0; i<filterValue.size(); i++)
		{
			String text = filterValue.get(i).getText();
			System.out.println(text);  
		}

		//Click on L'Oreal Paris Colour Protect Shampoo 
		driver.findElement(By.xpath("//div[@class='css-43m2vm']")).click();

		//GO to the new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));

		//Select size as 175ml
		WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		size.click();
		Select select = new Select(size);
		select.selectByVisibleText("175ml");

		//Print the MRP of the product 
		String text = driver.findElement(By.xpath("//span[@class='css-1jczs19']")).getText();
		System.out.println("MRP: " + text);

		//Click on ADD to BAG 
		driver.findElement(By.xpath("//button[@class=' css-12z4fj0']")).click();

		//Go to Shopping Bag 
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();

		//To move to frame
		driver.switchTo().frame(0);

		//Print the Grand Total amount
		Thread.sleep(5000);
		String total = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText(); 
		System.out.println("Grand Total : "+ total);

		//Click Proceed 
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

		//Click on Continue as Guest 
		driver.switchTo().window(windows.get(1));

		driver.findElement(By.xpath("//button[@class='btn full big']")).click();

		// Check if this grand total is the same in step 14 
		String text2 = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']//div//span")).getText();
		if(total.contains(text2))
		{
			System.out.println("The Grand Total Values are same"+ text2);  
		}
		else
		{
			System.out.println("Grand Total Values are different");
		}

		//Close all windows
		driver.quit();

	}

}
