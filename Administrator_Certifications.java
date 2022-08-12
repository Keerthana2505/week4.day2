package week4.day2.Assignment;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Administrator_Certifications {

	public static void main(String[] args) {
		//Download and set the path
		WebDriverManager.chromedriver().setup();

		//Launch the ChromeBrowser
		ChromeDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Load the URL
		driver.get("https://login.salesforce.com/");

		//Maximize the window
		driver.manage().window().maximize();

		//Enter the username as "ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");

		//Enter the password as "Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password#123");

		//click on the login button
		driver.findElement(By.id("Login")).click();

		//click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		//Click confirm on Confirm redirect

		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		Thread.sleep(3000);
		//convert to list
		List<String> lsWindowHandles = new ArrayList<String>(windowHandles);		

		//Switch to the next window using Windowhandles
		driver.switchTo().window(lsWindowHandles.get(1));

		//click on the confirm button in the redirecting page
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		//Click Resources and mouse hover on Learning On Trailhead
		Shadow dom=new Shadow(driver);
		dom.findElementByXPath("//span[text()='Learning']").click();
		Thread.sleep(3000);

		//Learning on Trailhead
		Actions builder = new Actions(driver);	
		WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		builder.moveToElement(trailHead).perform();

		builder.scrollToElement(trailHead).perform();		

		//Clilck on Salesforce Certifications
		WebElement salesCert = dom.findElementByXPath("//a[text()='Salesforce Certification']");		
		driver.executeScript("arguments[0].click();", salesCert);		

		//Click on Ceritification Administrator
		driver.findElement(By.linkText("Administrator")).click();

		//Navigate to Certification - Administrator Overview window
		List<WebElement> certifications = driver.findElements(By.xpath("(//div[@class='trailMix-card-body']/div[2])/a"));
		System.out.println(certifications.size());
		for (WebElement webElement : certifications) {
			String text = webElement.getText();
			System.out.println(text);
		}

	}

}
