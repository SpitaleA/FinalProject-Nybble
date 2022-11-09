package cleanTest.todoIst;

import enums.Languages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static utils.RandomString.getAlphaNumericString;

public class SettingsTcs extends TestBaseTodoIst{


    @Test
    public void testchagnepassword() throws InterruptedException {
        String newName = getAlphaNumericString(6);
        String actualName;


        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE NAME
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.configurationBtn.click();
        settingsModal.changePwd.click();
        settingsModal.newPwdTextbox.setTextnoClear("hola");
        settingsModal.nameTextbox.setText(newName);
        settingsModal.updateConfigurationChangesBtn.click();
        settingsModal.closeModalBtn.click();
        actualName = loggedinMainPage_Navbar.accountBtn.getAttributeValue("alt");

        Assertions.assertEquals(newName,actualName,"ERROR name was not changed correctly");
    }

    @Test
    public void changePageLanguage() throws InterruptedException {
        String labelBeforeLanguageChange;
        String labelAfterLanguageChange;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE LANGUAGE
        labelBeforeLanguageChange = project_CenterTasksArea.actualSectionDisplayedTitleLabel.getText();
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.configurationBtn.click();
        settingsModal.generalConfigBtn.click();
        settingsModal.languageComboboxBtn.click();
        settingsModal.findLanguageBtn(Languages.Español).click();
        settingsModal.updateConfigurationChangesBtn.click();
        loadingPage.loadingLabel.waitInvisibility();
        settingsModal.closeModalBtn.click();
        labelAfterLanguageChange = project_CenterTasksArea.actualSectionDisplayedTitleLabel.getText();

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
    @RepeatedTest(3)
    public void changeUIColors() throws InterruptedException {
        Random rand = new Random();
        String newColorTheme;
        String colorThemeBefore;
        int randNumber = rand.nextInt(3);

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE NAME
        colorThemeBefore = loggedinMainPage_Navbar.navbarColor.getCssAttributeValue("background-color");
        themeColorsList.remove(colorThemeBefore);
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.changeThemeBtn.click();

        settingsModal.themesBtns.getControls().get(randNumber).click();
        settingsModal.updateConfigurationChangesBtn.click();
        newColorTheme = loggedinMainPage_Navbar.navbarColor.getCssAttributeValue("background-color");
        Assertions.assertEquals(themeColorsList.get(randNumber),newColorTheme,"ERROR theme color was not changed");
        settingsModal.closeModalBtn.click();
        loggedinMainPage_Navbar.navbarColor.waitClickable();
        if (loggedinMainPage_Navbar.syncWaitIconBtn.isControlDisplayed())
            loggedinMainPage_Navbar.syncWaitIconBtn.waitInvisibility();
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.logoutBtn.click();
        System.out.println("success");
        System.out.println(colorThemeBefore + " to " + newColorTheme);
    }
}
