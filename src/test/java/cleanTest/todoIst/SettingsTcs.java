package cleanTest.todoIst;

import enums.Languages;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static utils.RandomString.getAlphaNumericString;

public class SettingsTcs extends TestBaseTodoIst{


    @DisplayName("Verify user can change the page language")
    @Epic("General")
    @Feature("Settings")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @ParameterizedTest
    @EnumSource(Languages.class)
    public void changePageLanguage(Languages language) throws InterruptedException {
        String labelBeforeLanguageChange;
        String labelAfterLanguageChange;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE LANGUAGE
        labelBeforeLanguageChange = project_CenterTasksArea.actualSectionDisplayedTitleLabel.getText();
        navbar.accountBtn.click();
        navbar.configurationOptBtn.click();
        settingsModal.generalConfigBtn.click();
        settingsModal.languageComboboxBtn.click();
        settingsModal.findLanguageBtn(language).waitClickable();
        settingsModal.findLanguageBtn(language).click();
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
    @DisplayName("Verify user can change the user name")
    @Epic("General")
    @Feature("Settings")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void changeUserName() throws InterruptedException {
        String newName = getAlphaNumericString(6);
        String actualName;


        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

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
    @DisplayName("Verify user can change the UI colors theme")
    @Epic("General")
    @Feature("Settings")
    @Severity(SeverityLevel.MINOR)
    @Tag("SmokeTest")
    @ParameterizedTest
    @MethodSource("themeColorsList")
    public void changeUIColors(String themeIterator,int testNumber) throws InterruptedException {
        String newColorTheme;
        String colorThemeBefore;
        String originalTheme;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.loginButton.waitClickable();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CHANGE THEME COLOR
        originalTheme = navbar.navbarColorLabel.getCssAttributeValue("background-color");
        colorThemeBefore = originalTheme;
        navbar.accountBtn.click();
        navbar.changeThemeOptBtn.click();
        settingsModal.themesBtns.getControls().get(testNumber).click();
        settingsModal.updateConfigurationChangesBtn.click();
        newColorTheme = navbar.navbarColorLabel.getCssAttributeValue("background-color");

        Assertions.assertEquals(themeIterator,newColorTheme,"ERROR theme color was not changed");

        settingsModal.closeModalBtn.click();

        //RETURN TO THE ORIGIAL THEME
        navbar.accountBtn.click();
        navbar.changeThemeOptBtn.click();
        settingsModal.themesBtns.getControls().get(0).click();
        settingsModal.updateConfigurationChangesBtn.click();
        newColorTheme = navbar.navbarColorLabel.getCssAttributeValue("background-color");

        Assertions.assertEquals(originalTheme,newColorTheme,"ERROR theme color was not changed to the original");

        Thread.sleep(3500);

    }
}
