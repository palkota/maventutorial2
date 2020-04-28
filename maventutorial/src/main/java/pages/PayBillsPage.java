package pages;

import org.openqa.selenium.By;

public class PayBillsPage extends BasePage{

	By ADDNEWPAYEE = By.xpath("//a[text()='Add New Payee']");
	By NAME = By.id("np_new_payee_name");
	By ADDRESS = By.id("np_new_payee_address");
	By ACCOUNT = By.id("np_new_payee_account");
	By DETAILS = By.id("np_new_payee_details");
	By ADD = By.id("add_new_payee");
	
	public void clickAddNewPayeeLink()
	{
		click(ADDNEWPAYEE);
	}
	
	public void addPayee(String name, String addrs, String acc, String details)
	{
		waitForElement(NAME,"visibility").sendKeys(name);
		type(ADDRESS,addrs);
		type(ACCOUNT,acc);
		type(DETAILS,details);
		click(ADD);
	}
}
