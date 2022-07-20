package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	/**
	 * This method is used to initialize the webdriver on the basis of given browser
	 * @param browserName
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name is :" + browserName);
		highlight = prop.getProperty("highlight").trim();
	  optionsManager = new OptionsManager(prop);
	
	  if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver(optionsManager.getChromeOptions());
	tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		
	}else if (browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		
	}else if (browserName.equalsIgnoreCase("safari")) {
		
		driver = new SafariDriver();	
	 tlDriver.set(new SafariDriver());	
	}else {
		System.out.println("please pass the right browser name :"+ browserName);
	}
	  getDriver().manage().deleteAllCookies();
	  getDriver().manage().window().maximize();
	  getDriver().get(prop.getProperty("url"));
	return  getDriver();
	
	}
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * This method is used to initiliazie the properties on the basis of given enviroment 
	 * QA,DEV,STAGE
	 * @return
	 */
	
	public Properties init_prop() {
		prop =new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
		
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;
	}

	
	public static String getScreenshot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}
	
	
	
	
	
	
}
