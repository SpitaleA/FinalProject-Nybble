package pages.phpTravels;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

public class LoggedInPage {
    public Label fullNameLabel = new Label(By.xpath("//div[@class=\"card-body\"]/strong"));
    public Button logoutBtn = new Button(By.xpath("//div[@class=\"d-none d-lg-block sidebar\"]//a[@href=\"/logout.php\"]"));
}
