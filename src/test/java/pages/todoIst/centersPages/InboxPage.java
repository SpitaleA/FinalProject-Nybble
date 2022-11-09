package pages.todoIst.centersPages;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class InboxPage {
    public Button findTaskByName(String name){
        Button taskFound = new Button(By.xpath("//li[@id]//div[text()='" + name+ "']"));
        return taskFound;
    }
}
