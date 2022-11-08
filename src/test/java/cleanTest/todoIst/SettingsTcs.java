package cleanTest.todoIst;

import controlSelenium.SingletonWait;
import enums.Languages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import singletonSession.Session;
import utils.RandomString;

import static utils.RandomString.getAlphaNumericString;

public class SettingsTcs extends TestBaseTodoIst{

    @Test
    public void changePageLanguage() throws InterruptedException {
        String labelBeforeLanguageChange;
        String labelAfterLanguageChange;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE LANGUAGE
        labelBeforeLanguageChange = loggedinMainPage_CenterTasksArea.todaySectionTitleLabel.getText();
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.configurationBtn.click();
        settingsModal.generalConfigBtn.click();
        settingsModal.languageComboboxBtn.click();
        settingsModal.findLanguageBtn(Languages.Español).click();
        settingsModal.updateConfigurationChangesBtn.click();
        loadingPage.loadingLabel.waitInvisibility();
        settingsModal.closeModalBtn.click();
        labelAfterLanguageChange = loggedinMainPage_CenterTasksArea.todaySectionTitleLabel.getText();

        Assertions.assertNotEquals(labelBeforeLanguageChange,labelAfterLanguageChange,"ERROR language was not changed");
    }
    @Test
    public void changeUserName() throws InterruptedException {
        String newName = getAlphaNumericString(6);
        String actualName;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE NAME
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.configurationBtn.click();
        settingsModal.nameTextbox.setText(newName);
        settingsModal.updateConfigurationChangesBtn.click();
        settingsModal.closeModalBtn.click();
        actualName = loggedinMainPage_Navbar.accountBtn.getAttributeValue("alt");

        Assertions.assertEquals(newName,actualName,"ERROR name was not changed correctly");
    }
}
