package tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AccountSummaryPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PayBillsPage;
import testdata.ReadData;


public class AddNewPayee extends BaseTest{

	PayBillsPage paybill;
  @BeforeClass
  public void beforeClass()
  {
	  HomePage home = new HomePage();
	  home.clickSignInButton();
	  LoginPage login = new LoginPage();
	  login.doLogin("username", "password");
	  AccountSummaryPage acc = new AccountSummaryPage();
	  acc.clickPayBillsLink();
  }
  
  @BeforeMethod
  public void beforeMethod()
  {
	  paybill = new PayBillsPage();
	  paybill.clickAddNewPayeeLink();
  }
  
  @Test(dataProvider="dp")
  public void f(String name, String addrs, String acc, String details) {
	  test = report.createTest("Add New payee for "+name);
	  paybill.addPayee(name, addrs, acc, details);
  }
  
  @DataProvider
  public Object[][] dp() throws IOException
  {
	  ReadData data = new ReadData(System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\PayeeDetails.xlsx","PayeeDetails");
	  return data.getData();
  }

}
