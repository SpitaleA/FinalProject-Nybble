package pages.todoIst.modals;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class ChangeCurrentTimezone {

    public Button closeModal = new Button(By.xpath("//div[@class=\"timezone_alert\"]//a[@class=\"timezone_link timezone_link_block\"]"));
}
