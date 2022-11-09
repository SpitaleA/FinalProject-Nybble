package pages.todoIst.centersPages;

import controlSelenium.Button;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class LoginPage {
    public TextBox emailTextBox = new TextBox(By.id("element-0"),"Email textbox");
    public TextBox passwordTextBox = new TextBox(By.id("element-3"),"pwd textbox");
    public Button loginButton = new Button(By.xpath("//button[@type=\"submit\"]"),"Login button");

    public void login(String user,String pwd){
        emailTextBox.setText(user);
        passwordTextBox.setText(pwd);
        loginButton.click();
    }
}
