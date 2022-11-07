package pages.todoIst;

import controlSelenium.Button;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class SettingsModal {
    public Button deleteAccountBtn = new Button(By.xpath("//a[@href=\"/app/settings/account/delete\"]"),"Delete account button");
    public TextBox pwdDeleteTextbox = new TextBox(By.id("element-13"));
    public Button confirmDeleteBtn = new Button(By.xpath("//button[@type=\"submit\"]"));
}
