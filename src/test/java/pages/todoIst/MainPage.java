package pages.todoIst;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class MainPage {
    public Button loginButton= new Button(By.xpath("//ul/li/a[contains(text(),'Iniciar ses') and @href=\"/auth/login\"]"),"Login button");
    public Button registerButton= new Button(By.xpath("//ul//a[@href='/auth/signup']"),"Register button");

}
