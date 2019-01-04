package com.ibm.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AdminPage {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(xpath="//*[@id='side-menu']/li[2]/a")
	WebElement catalogEle;
	@FindBy(xpath="//*[@id='side-menu']/li[2]/ul/li[3]/a")
	WebElement categoriesEle;
	@FindBy(xpath="//*[@id='dataTableExample2_filter']/label/input")
	WebElement searchCategoryEle;
	@FindBy(xpath="//*[@id='dataTableExample2']/tbody/tr/td[6]/div/button")
	WebElement actionEle;
	@FindBy(xpath="//*[@id='dataTableExample2']/tbody/tr/td[6]/div/ul/li[2]/a")
	WebElement DeleteCategoryEle;
	@FindBy(xpath="/html/body/div[4]/div[7]/div/button")
	WebElement confirmDelEle;
	@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr/td")
	WebElement norecordEle;
	@FindBy(xpath="//*[@id='page-wrapper']/div/div[2]")
	WebElement successmessageEle;
	public AdminPage(WebDriver driver,WebDriverWait wait)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
		this.wait=wait;
	}
	public void clickOnCatalog()
	{
		catalogEle.click();
	}
public void clickOnCategories()
{
	categoriesEle.click();
}
public void searchCategoryToBeDeleted(String ctr)
{
	searchCategoryEle.sendKeys(ctr);
}
public void clickOnAction()
{
	actionEle.click();
}
public void clickDelete()
{
	DeleteCategoryEle.click();
}
public void clickOnConfirmDelete()
{
	confirmDelEle.click();
}
public void successMessageVerification(String sm)
{
	Assert.assertTrue(successmessageEle.getText().contains(sm));
}
public void verifyDeletedCategoryInAdminPage(String ctr,String nrf)
{
	searchCategoryToBeDeleted(ctr);
	Assert.assertTrue(norecordEle.getText().trim().equals(nrf));
	
	}
public String dataBaseValidation(String table,String colomn,String colomn_value) throws SQLException
{
	String text=null;
	Connection c=DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries","foodsonfinger_atoz","welcome@123");
	Statement s=c.createStatement();
	ResultSet rs=s.executeQuery("SELECT * FROM "+table+" WHERE "+colomn+"='"+colomn_value+"'");
	if(rs.next())
	{
		text=rs.getString(colomn);
	}
	return text;
	 
	
}

}
