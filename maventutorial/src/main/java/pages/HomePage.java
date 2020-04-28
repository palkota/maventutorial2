package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class HomePage extends BasePage{

	By SIGNIN_BUTTON = By.id("signin_button");
	By SEARCH_TEXTFIELD = By.name("searchTerm");
			
	public void clickSignInButton()
	{
		click(SIGNIN_BUTTON);
	}
	
	public void do_search(String st)
	{
		type(SEARCH_TEXTFIELD,st);
		type(SEARCH_TEXTFIELD,Keys.ENTER);
	}	
}
