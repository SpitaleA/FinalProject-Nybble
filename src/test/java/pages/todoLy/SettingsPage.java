package pages.todoLy;

import controlSelenium.Button;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class SettingsPage {
    public TextBox nameTextBox = new TextBox(By.id("FullNameInput"));
    public TextBox newPWD = new TextBox(By.id("TextPwNew"));
    public TextBox oldPWD = new TextBox(By.id("TextPwOld"));
    public Button okButton = new Button(By.xpath("//button//span[text()=\"Ok\"]"));
}
