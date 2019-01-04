package com.ibm.test;



import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ibm.initialization.WebDriverLaunch;
import com.ibm.pages.AdminPage;
import com.ibm.pages.LoginPage;
import com.ibm.pages.UserPage;

public class BaseTest extends WebDriverLaunch {
	@Test
	public void adminCredentials()
	{
		String url=p.getProperty("url");
		driver.get(url);
		String username=p.getProperty("user");
		String password=p.getProperty("password");
		LoginPage lp=new LoginPage(driver,wait);
		lp.inputEmail(username);
		lp.inputPassword(password);
		lp.clickOnLogin();
	}
	@Test
	public void dleteCategoryAndVerify() throws InterruptedException, SQLException
	{
		String catName=p.getProperty("categoryname");
		String tablename=p.getProperty("table");
		String colomn=p.getProperty("coloumn");
		String noRecFoundMessage=p.getProperty("message");
		String delMessage=p.getProperty("successmessage");
		String userurl=p.getProperty("urluser");
		AdminPage ap=new AdminPage(driver,wait);
		ap.clickOnCatalog();
		ap.clickOnCategories();
		Thread.sleep(3000);
		ap.searchCategoryToBeDeleted(catName);
	String act=	ap.dataBaseValidation(tablename, colomn, catName);
	Assert.assertEquals(catName, act);//beforedeleting
	ap.clickOnAction();
	Thread.sleep(3000);
	ap.clickDelete();
	Thread.sleep(3000);
	ap.clickOnConfirmDelete();
	Thread.sleep(3000);
	ap.successMessageVerification(delMessage);
	ap.verifyDeletedCategoryInAdminPage(catName, noRecFoundMessage);
	String actual=	ap.dataBaseValidation(tablename, colomn, catName);
	Assert.assertEquals(null, actual);//afterdeleting
	driver.get(userurl);
	UserPage up=new UserPage(driver,wait);
	up.clickOnShopByCategory();
	up.verificationOfCategoryPresence(catName);
	
	
		
		
	}

}
