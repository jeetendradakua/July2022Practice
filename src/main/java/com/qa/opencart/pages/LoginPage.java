package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private by locators:
	
	private By emailId = By.xpath("//*[@id='input-email']");
	private By password = By.xpath("//*[@id='input-password']");
	private By loginBtn  = By.xpath("//input[@value='Login']");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	
	//2. public page const .. (Initialize driver by creating a constructor)
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver ;
		eleUtil = new ElementUtil(driver);
	}
	
	
	
	
	
	//3. public page actions :
		
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	public String getLoginPageUrl() {
		return eleUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_FRACTION_URL);
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwd);
	}
	
	public AccountsPage doLogin(String un, String pwd) {
		eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		return new AccountsPage(driver);
	}
	
	public boolean isRegisterLinkExist() {
		return eleUtil.waitForElementToBeVisible(registerLink, Constants.DEFAULT_TIME_OUT).isDisplayed();
				
	}
	
	public RegistrationPage navigateToRegisterPage() {
		if(isRegisterLinkExist()) {
			eleUtil.doClick(registerLink);
			return new RegistrationPage(driver);
		}
		return null;
	}





	private RegistrationPage RegistrationPage(WebDriver driver2) {
		// TODO Auto-generated method stub
		return null;
	}





	
}
