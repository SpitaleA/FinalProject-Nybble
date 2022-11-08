package pages.todoIst;

import controlSelenium.Button;
import controlSelenium.TextBox;
import enums.Languages;
import org.openqa.selenium.By;

public class SettingsModal {
    public Button deleteAccountBtn = new Button(By.xpath("//a[@href=\"/app/settings/account/delete\"]"),"Delete account button");
    public TextBox pwdDeleteTextbox = new TextBox(By.id("element-13"),"Pwd for delete account textbox");
    public Button confirmDeleteBtn = new Button(By.xpath("//button[@type=\"submit\"]"),"Confirm account delete button");
    public Button generalConfigBtn = new Button(By.xpath("//a[@href=\"/app/settings/general\"]"),"General config section button");
    public Button languageComboboxBtn = new Button(By.id("element-5"));
    public Button updateConfigurationChangesBtn = new Button(By.xpath("//button[@type=\"submit\"]"),"Update configuration changed button");
    public Button closeModalBtn = new Button(By.xpath("//header/div/button[@type=\"button\" and @style]"));
    public TextBox nameTextbox = new TextBox(By.id("element-0"));
    public Button findLanguageBtn(Languages language){
        Button foundLanguageBtn = new Button(By.xpath("//*[@id=\"element-5\"]/*[contains(text(),\""+ language.name() +"\")]"));
        return foundLanguageBtn;
    }
}
