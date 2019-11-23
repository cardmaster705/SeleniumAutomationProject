package qualityStreamTutorial;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {

	private WebDriver driver;
	String usuario;
	String pwd;
	String information;
	Scanner sc = new Scanner(System.in);
	
	//Locators
	By registerLinkLocator = By.linkText("REGISTER");
	By registerPageLocator = By.xpath("//img[@src='/images/masts/mast_register.gif']");
	By registerBtnLocator = By.name("register");
	
	By user = By.id("email");
	By password = By.name("password");
	By confirm = By.name("confirmPassword");
	By getInformation = By.xpath("//div[@class='footer']");
	
	public void capturar() {
		System.out.println("Cual es el id?");
		usuario = sc.nextLine();
		System.out.println("cual es el nombre");
		pwd=sc.nextLine();
	}
	
	@Before
	public void setUp(){
		capturar();
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver_78.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://newtours.demoaut.com/");
	}
	
	@Test
	public void testGooglePage(){
		driver.findElement(registerLinkLocator).click();
		if(driver.findElement(registerPageLocator).isDisplayed()){
			driver.findElement(user).sendKeys(usuario);
			driver.findElement(password).sendKeys(pwd);
			driver.findElement(confirm).sendKeys(pwd);
			driver.findElement(registerBtnLocator).click();
			information = driver.findElement(getInformation).getText();
		}
		else {
			System.out.println("Register page was not found");
		}
	}
	
	@After
	public void tearDown(){
		System.out.println("la informacion que debe ir en el txt es:");
		System.out.println(usuario);
		System.out.println(pwd);
		System.out.println(information);
		driver.close();
	}
}
