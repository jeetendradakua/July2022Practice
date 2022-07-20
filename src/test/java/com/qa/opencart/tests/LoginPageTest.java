package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
	System.out.println("page title : "+ actTitle);
	Assert.assertEquals(actTitle,Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		System.out.println("page url :" +actUrl);
		Assert.assertEquals(actUrl,Constants.LOGIN_PAGE_FRACTION_URL);
	}
	

	@Test
	public void forgetPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		
	}
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	 Assert.assertTrue(accPage.isAccountsPageHeaderExist());
	}
	
	@Test
	public void isRegisterLinkExist() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
}
