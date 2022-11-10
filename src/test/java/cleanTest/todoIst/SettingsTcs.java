package cleanTest.todoIst;

import enums.Languages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static utils.RandomString.getAlphaNumericString;

public class SettingsTcs extends TestBaseTodoIst{


    @Test
    @Disabled
    public void changePassword() throws InterruptedException {
        String newName = getAlphaNumericString(6);
        String actualName;


        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);


        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE NAME
        navbar.accountBtn.click();
        navbar.configurationOptBtn.click();
        settingsModal.changePwd.click();
        settingsModal.newPwdTextbox.setTextnoClear("hola");
        settingsModal.nameTextBox.setText(newName);
        settingsModal.updateConfigurationChangesBtn.click();
        settingsModal.closeModalBtn.click();
        actualName = navbar.accountBtn.getAttributeValue("alt");

        Assertions.assertEquals(newName,actualName,"ERROR name was not changed correctly");
    }

    @Test
    public void changePageLanguage() throws InterruptedException {
        String labelBeforeLanguageChange;
        String labelAfterLanguageChange;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE LANGUAGE
        labelBeforeLanguageChange = project_CenterTasksArea.actualSectionDisplayedTitleLabel.getText();
        navbar.accountBtn.click();
        navbar.configurationOptBtn.click();
        settingsModal.generalConfigBtn.click();
        settingsModal.languageComboboxBtn.click();
        settingsModal.findLanguageBtn(Languages.English).waitClickable();
        settingsModal.findLanguageBtn(Languages.English).click();
        settingsModal.updateConfigurationChangesBtn.click();
        loadingPage.loadingLabel.waitInvisibility();
        settingsModal.closeModalBtn.click();
        labelAfterLanguageChange = project_CenterTasksArea.actualSectionDisplayedTitleLabel.getText();

        Assertions.assertNotEquals(labelBeforeLanguageChange,labelAfterLanguageChange,"ERROR language was not changed");

        //GO BACK TO THE ORGINAL LANGUAGE
        //CHANGE LANGUAGE
        labelBeforeLanguageChange = project_CenterTasksArea.actualSectionDisplayedTitleLabel.getText();
        navbar.accountBtn.click();
        navbar.configurationOptBtn.click();
        settingsModal.generalConfigBtn.click();
        settingsModal.languageComboboxBtn.click();
        settingsModal.findLanguageBtn(Languages.Español).waitClickable();
        settingsModal.findLanguageBtn(Languages.Español).click();
        settingsModal.updateConfigurationChangesBtn.click();
        loadingPage.loadingLabel.waitInvisibility();
        settingsModal.closeModalBtn.click();
        labelAfterLanguageChange = project_CenterTasksArea.actualSectionDisplayedTitleLabel.getText();

        Assertions.assertNotEquals(labelBeforeLanguageChange,labelAfterLanguageChange,"ERROR language was not changed back to the orginal");
    }
    @Test
    public void changeUserName() throws InterruptedException {
        String newName = getAlphaNumericString(6);
        String actualName;


        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE NAME
        navbar.accountBtn.click();
        navbar.configurationOptBtn.click();
        settingsModal.nameTextBox.click();
        settingsModal.nameTextBox.setText(newName);
        settingsModal.updateConfigurationChangesBtn.click();
        settingsModal.closeModalBtn.click();
        actualName = navbar.accountBtn.getAttributeValue("alt");

        Assertions.assertEquals(newName,actualName,"ERROR name was not changed correctly");
        Thread.sleep(3500);
    }
    @RepeatedTest(3)
    public void changeUIColors() throws InterruptedException {
        Random rand = new Random();
        String newColorTheme;
        String colorThemeBefore;
        int randNumber = rand.nextInt(3);

        //LOGIN
        mainPage.loginButton.click();
        loginPage.loginButton.waitClickable();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE THEME COLOR
        colorThemeBefore = navbar.navbarColorLabel.getCssAttributeValue("background-color");
        themeColorsList.remove(colorThemeBefore);
        navbar.accountBtn.click();
        navbar.changeThemeOptBtn.click();
        settingsModal.themesBtns.getControls().get(randNumber).click();
        settingsModal.updateConfigurationChangesBtn.click();
        newColorTheme = navbar.navbarColorLabel.getCssAttributeValue("background-color");

        Assertions.assertEquals(themeColorsList.get(randNumber),newColorTheme,"ERROR theme color was not changed");

        settingsModal.closeModalBtn.click();
        Thread.sleep(3500);

//        System.out.println("success");
//        System.out.println(colorThemeBefore + " to " + newColorTheme);
    }
}
