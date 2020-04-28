package pages;

import org.openqa.selenium.By;

public class AccountSummaryPage extends BasePage{

	 By PAYBILLS = By.xpath("//a[text()='Pay Bills']");
	 
	 public void clickPayBillsLink()
	 {
		 click(PAYBILLS);
	 }
}
