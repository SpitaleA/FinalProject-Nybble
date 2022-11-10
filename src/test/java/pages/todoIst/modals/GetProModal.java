package pages.todoIst.modals;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class GetProModal {
    public Button updateToProBtn = new Button(By.xpath("//a[@href=\"/premium/upgrade\"]"));
    public Button closeModalBtn = new Button(By.xpath("//div[@data-testid=\"button-container\"]/button"));
}
