package pages.todoLy;

import controlSelenium.Button;
import controlSelenium.CheckBox;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class RegisterModal {
    public TextBox emailTextBox = new TextBox(By.id("ctl00_MainContent_SignupControl1_TextBoxEmail"));
    public TextBox passwordTextBox = new TextBox(By.id("ctl00_MainContent_SignupControl1_TextBoxPassword"));
    public TextBox userNameTextBox = new TextBox(By.id("ctl00_MainContent_SignupControl1_TextBoxFullName"));
    public CheckBox termsCheckBox = new CheckBox(By.id("ctl00_MainContent_SignupControl1_CheckBoxTerms"));
    public Button registerButton = new Button(By.id("ctl00_MainContent_SignupControl1_ButtonSignup"));

    public void register(String user,String email,String pwd){
        emailTextBox.setText(email);
        passwordTextBox.setText(pwd);
        userNameTextBox.setText(user);
        termsCheckBox.check();
        registerButton.click();
    }
}
