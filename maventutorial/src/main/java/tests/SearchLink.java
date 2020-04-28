package tests;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.SearchResultPage;
import testdata.ReadData;

public class SearchLink extends BaseTest{
	
  @Test(dataProvider="dp")
  public void f(String st, String c) {
	  test = report.createTest("Search link for: "+st);
	  HomePage home = new HomePage();
	  home.do_search(st);
	  SearchResultPage search = new SearchResultPage();
	  int actual = search.getResultLinkCount();
	  
	  Assert.assertEquals(Integer.parseInt(c), actual);
	  List<String> linkText = search.getResultLinkText();
	  for(String text:linkText)
	  {
		  Assert.assertEquals(text.contains(st),true);
	  }
  }
  
  @DataProvider
  public Object[][] dp() throws IOException
  {
	  ReadData data = new ReadData(System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\SearchScenario.xlsx","Search");
	  return data.getData();
  }
}
