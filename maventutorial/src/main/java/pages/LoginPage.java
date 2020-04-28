package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{

	By USERNAME = By.id("user_login");
	By PASSWORD = By.id("user_password");
	By SIGNIN = By.name("submit");

	public void doLogin(String uname, String pword)
	{
		type(USERNAME,uname);
		type(PASSWORD,pword);
		click(SIGNIN);
	}
}
