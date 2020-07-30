
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TemplatesPage {

	/**
	 * @param args
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\omprakash\\Desktop\\Mahesh\\Work\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://venngage.com/");
		driver.manage().window().maximize();
		driver.findElement(By.id("menu-item-350")).click();

		WebDriverWait w = new WebDriverWait(driver, 15);
		w.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div.templates__tag--Y-VO2.templates__premium--1Bdnc")));

		driver.findElement(By.xpath(
				"//*[@id=\"see all templates\"]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div/center/div[1]/a/button"));
		Actions a = new Actions(driver);
		a.moveToElement(driver
				.findElement(By.cssSelector("div.templates__hoverSettings--1IH_r.undefined.templates__hidden--3pfUu")))
				.build().perform();

		WebElement toolTipElement = driver.findElement(By.xpath("//*[@id=\"see all templates\"]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div/center/div[1]/a/button"));
		//WebElement toolTipElement =driver.findElement(By.xpath("//*[@id=\"see all templates\"]/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div/center/div[1]/a/button"));
		String ToolTipText = toolTipElement.getText();
		Assert.assertEquals("CREATE", ToolTipText); //Noticed that CREATE button is visible

		a.moveToElement(driver.findElement(By.xpath(
				"//*[@id=\"see all templates\"]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div/center/div[1]/a/button")))
				.build().perform();
		List<WebElement> TextButton = driver.findElements(By.cssSelector("button.templates__signUp--2DvEL"));

		for (int i = 0; i < TextButton.size(); i++) {
			String word = TextButton.get(i).getText();
			if (word.contains("SIGN UP")) {
				String ToolTipText2 = driver.findElement(By.xpath("//button[text()='SIGN UP']")).getText();
				Assert.assertEquals("SIGN UP", ToolTipText2); //Noticed that button changed to SIGN UP
			}
		}

		driver.findElement(By.xpath(
				"//*[@id=\"see all templates\"]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div/center/div[1]/a/button"))
				.click();

		driver.findElement(By.id("user_first_name")).sendKeys("QA");
		driver.findElement(By.id("user_last_name")).sendKeys("Test");
		int count = getCount();
		driver.findElement(By.id("user_email")).sendKeys("qa.niceday" + count + "@venngage.com");
		count++;
		putCount(count);
		driver.findElement(By.id("user_password")).sendKeys("123456 ");
		driver.findElement(By.id("btn_register")).click();
		driver.findElement(By.id("onboarding-option-med-business")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("onboarding-option-finance")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("step-0")));
		driver.findElement(By.id("step-0"));
		driver.findElement(By.cssSelector("button.skip-tour-button")).click();
		driver.findElement(By.id("account-button")).click();
		driver.findElement(By.xpath("//*[@id=\"account-button\"]/ul/li[1]")).click();
		WebElement Verification = driver.findElement(By.cssSelector("h1.common-title"));
		String VerifyRegistration = Verification.getText();
		Assert.assertEquals("Manage Your Profile", VerifyRegistration); // Verified that registration is successful
		driver.close();
	}

	public static int getCount() {

		int count = 36;
		try {
			if (!new File(System.getProperty("user.dir") + "\\MailCount.txt").exists())
				return count;
			else {
				BufferedReader br = new BufferedReader(
						new FileReader(new File(System.getProperty("user.dir") + "\\MailCount.txt")));
				String s = br.readLine();
				count = Integer.parseInt(s);
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void putCount(int count) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File(System.getProperty("user.dir") + "\\MailCount.txt")));
			bw.write(Integer.toString(count));
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
