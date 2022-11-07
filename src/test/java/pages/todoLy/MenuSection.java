package pages.todoLy;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

public class MenuSection {
    public Button logoutButton = new Button(By.xpath("//a[text()='Logout']"),"Logout button");
    public Button settingsButton = new Button(By.xpath("//a[@href=\"javascript:OpenSettingsDialog();\"]"));
    public Label loadingIcon = new Label(By.xpath("//img[@id=\"LoaderImg\" and contains(concat(' ', @style, ' '), ' display: none;')]"));
}
