package pages.todoIst.centersPages;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class SearchResults {
    public Button findInSearchResults(String text){
        Button searchSuccess = new Button(By.xpath("//ul[@class=\"items\"]//div[text()=\""+ text+"\"]/../../../../.."));
        return searchSuccess;
    }
}
