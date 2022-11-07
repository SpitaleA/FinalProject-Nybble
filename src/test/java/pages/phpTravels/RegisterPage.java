package pages.phpTravels;

import controlSelenium.Button;
import controlSelenium.CheckBox;
import controlSelenium.Control;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class RegisterPage {
    public TextBox firstName = new TextBox(By.id("inputFirstName"));
    public TextBox lastName = new TextBox(By.id("inputLastName"));
    public TextBox email = new TextBox(By.id("inputEmail"));
    public TextBox phone = new TextBox(By.id("inputPhone"));
    public TextBox address = new TextBox(By.id("inputAddress1"));
    public TextBox address2 = new TextBox(By.id("inputAddress2"));
    public TextBox city = new TextBox(By.id("inputCity"));
    public TextBox state = new TextBox(By.id("stateinput"));
    public TextBox postCode = new TextBox(By.id("inputPostcode"));
    public Button countryComboboxBtn = new Button(By.id("inputCountry"));
    public TextBox mobilePhone = new TextBox(By.id("customfield2"));
    public TextBox pwd = new TextBox(By.id("inputNewPassword1"));
    public TextBox confirmPwd = new TextBox(By.id("inputNewPassword2"));
    public Button capcha = new Button(By.xpath("//div[@class=\"recaptcha-checkbox-border\"]"),"capcha");
    public Control captchaFrame = new Control(By.xpath("//iframe[@title=\"reCAPTCHA\"]"), "captchaFrame");
    public Control controlLable = new Control(By.id("recaptcha-token"));
    public Button submitRegister = new Button(By.xpath("//input[@type=\"submit\"]"));
    public Button findCountryBtn(String countryName){
        Button countryBtn = new Button(By.xpath("//select[@id=\"inputCountry\"]/option[contains(text(),\""+countryName+"\")]"));
        return countryBtn;
    }
}
