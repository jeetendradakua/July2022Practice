package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accountsPageTitleTest() {
		String actAccountPageTitle = accPage.getAccountsPageTitle();
	System.out.println("Acc Page Title :" +actAccountPageTitle  );
	Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageHeaderTest() {
		Assert.assertTrue(accPage.isAccountsPageHeaderExist());
	}
	
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Test
	public void accSectionsTest() {
		List<String>actSecList = accPage.getAccountsPageSectionsList();
	     System.out.println("Accounts Sections list =" +actSecList);
	    Assert.assertEquals(actSecList, Constants.ACCOUNTS_SECTIONS_LIST);  
	
	
	
	}
	
	@Test
	public void searchHeaderTest() {
	searchResultsPage =	accPage.doSearch("Macbook");
	String actSearchHeader = searchResultsPage.getResultsPageHeaderValue();
	Assert.assertTrue(actSearchHeader.contains("Macbook"));
	}
	
	@Test
	public void searchProductTest() {
	searchResultsPage =	accPage.doSearch("Imac");
	int actProductCount = searchResultsPage.getProductSearchCount();
	Assert.assertEquals(actProductCount, Constants.IMAC_PRODUCT_COUNT);
	}
	
	@Test
	public void getSearchProductListTest() {
		searchResultsPage =	accPage.doSearch("Macbook");
		List<String>actProductList = searchResultsPage.getProductResultsList();
		System.out.println(actProductList);
	}
	
	
	
	
	
	
	
	
	
}
