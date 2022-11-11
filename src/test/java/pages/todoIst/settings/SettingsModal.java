package pages.todoIst.settings;

import controlSelenium.Button;
import controlSelenium.TextBox;
import enums.Languages;
import org.openqa.selenium.By;

public class SettingsModal {
    public Button deleteAccountBtn = new Button(By.xpath("//a[@href=\"/app/settings/account/delete\"]"),"Delete account button");
    public Button closeConfirmAccountPopUp = new Button(By.xpath("(//div[@data-testid=\"toaster\"]/div/div/div)[last()]/button"),"Close confirm email pop up");
    public Button changePwd = new Button(By.xpath("//a[@href=\"/app/settings/account/password\"]"),"Change pwd button");
    public TextBox newPwdTextbox = new TextBox(By.xpath("//span[contains(text(),'Nueva')]/../../..//input"));
    public TextBox pwdDeleteTextbox = new TextBox(By.xpath("//input[@name]"),"Pwd for delete account textbox");
    public Button confirmDeleteBtn = new Button(By.xpath("//button[@type=\"submit\"]"),"Confirm account delete button");
    public Button generalConfigBtn = new Button(By.xpath("//a[@href=\"/app/settings/general\"]"),"General config section button");
    public Button languageComboboxBtn = new Button(By.xpath("//select[@name=\"language\"]"));
    public Button updateConfigurationChangesBtn = new Button(By.xpath("//button[@type=\"submit\"]"),"Update configuration changed button");
    public Button closeModalBtn = new Button(By.xpath("//header/div/button[@type=\"button\" and @style]"));
    public TextBox nameTextBox = new TextBox(By.xpath("//input[@name=\"name\"]"));
    public Button themesBtns = new Button(By.xpath("//button[@id and @aria-checked=\"false\" and not(@disabled)]"));
    public Button findLanguageBtn(Languages language){
        Button foundLanguageBtn = new Button(By.xpath("//*[@id]/*[contains(text(),\""+ language.name() +"\")]"));
        return foundLanguageBtn;
    }
}
