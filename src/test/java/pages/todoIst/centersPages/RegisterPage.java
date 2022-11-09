package pages.todoIst.centersPages;

import controlSelenium.Button;
import controlSelenium.TextBox;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class RegisterPage {
    public TextBox emailTextbox = new TextBox(By.id("element-0"));
    public TextBox pwdTextbox = new TextBox(By.id("element-3"));
    public Button registerBtn = new Button(By.xpath("//button[@data-gtm-id=\"start-email-signup\"]"));

}
