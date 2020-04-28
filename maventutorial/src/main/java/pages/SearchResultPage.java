package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends BasePage {

	By RESULT = By.xpath("//a[starts-with(text(),'Zero -')]");
	
	public int getResultLinkCount()
	{
		return getElements(RESULT).size();
	}
	
	public List<String> getResultLinkText()
	{
		 List<WebElement> links = getElements(RESULT);
		 List<String> linkText = new ArrayList<String>();
		 for(WebElement link : links)
		 {
			 linkText.add(link.getText());
		 }
		 return linkText;
	}
}
