package week4.day2.Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Architect_Certifications {

	public static void main(String[] args) {

		// To Download and set the path 
		WebDriverManager.chromedriver().setup();

		// To Launch the chromebrowser
		WebDriver driver = new ChromeDriver();

		//To Launch the URL
		driver.get("https://login.salesforce.com/");

		//To Maximise the window
		driver.manage().window().maximize();

		//To add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//To Enter the username as " ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");

		//To  Enter the password as " Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password#123");

		//To click on the login button
		driver.findElement(By.id("Login")).click();

		//To click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//button[@title='Learn More']")).click();

		//To Switch to the next window using Windowhandles
		Set<String> windowHandles = driver.getWindowHandles();

		List<String> windows = new ArrayList<String>(windowHandles);

		driver.switchTo().window(windows.get(1));

		//To click on the confirm button in the redirecting page
		Thread.sleep(3000);

		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		//To Click Learning on ShadowDom
		Shadow dom=new Shadow(driver); 

		dom.findElementByXPath("//span[text()='Learning']").click();

		//To mouse hover on Learning On Trailhead 
		Thread.sleep(3000);

		WebElement learningOnTrailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");

		Actions builder = new Actions(driver);

		builder.moveToElement(learningOnTrailHead).perform();

		builder.scrollToElement(learningOnTrailHead).perform();

		//To Click on Salesforce Certifications 
		WebElement salesForce = dom.findElementByXPath("//a[text()='Salesforce Certification']");

		//driver.executeScript("arguments[0].click();", salesForce);

		salesForce.click();

		//To Choose Your Role as Salesforce Architect
		WebElement salesForceArchitect = driver.findElement(By.xpath("(//div[@class='roleMenu-item_text'])[2]"));

		salesForceArchitect.click();

		//To Get the Text(Summary) of Salesforce Architect
		Thread.sleep(5000);

		WebElement text = driver.findElement(By.xpath("//div[@class='cert-site_text slds-text-align--center Lh(1.5) Fz(16px) slds-container--center slds-p-bottom--large']"));

		System.out.println("The Summary of Sales Architect is : ");
		System.out.println(text.getText());

		//To Get the List of Salesforce Architect Certifications Available 
		List<WebElement> salesForceList = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));

		System.out.println("No. of SalesForce Certifications available : " + salesForceList.size());

		System.out.println("List of Salesforce Architect Certifications : ");

		for(WebElement webelement : salesForceList )
		{
			System.out.println(webelement.getText());
		}

		//To Click on Application Architect 
		driver.findElement(By.linkText("Application Architect")).click();

		//To Get the List of Certifications available
		List<WebElement> cert = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));

		System.out.println("No.of Certifications available in Application Architect : "+ cert.size());

		System.out.println("List of Certifications available on Application Architect : ");

		for(WebElement webelement : cert)
		{
			System.out.println(webelement.getText());
		}

	}

}
