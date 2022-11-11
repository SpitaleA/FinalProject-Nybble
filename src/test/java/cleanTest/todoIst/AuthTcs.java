package cleanTest.todoIst;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import singletonSession.Session;
import utils.RunnerExtension;
import utils.Watcher;

import static utils.RandomString.getAlphaNumericString;

@Owner("Agustin Spitale")
@Story("Final Project - Todoist")
public class AuthTcs extends TestBaseTodoIst{

    @DisplayName("Verify user can register")
    @Description("This is to verify if a normal user can register")
    @Epic("Register")
    @Feature("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    public void register() throws InterruptedException {
        String email = getAlphaNumericString(6) + "@gmail.com";
        String pwd = getAlphaNumericString(8);

        mainPage.registerButton.click();
        registerPage.emailTextbox.setText(email);
        registerPage.pwdTextbox.setText(pwd);
        registerPage.registerBtn.click();
        loadingPage.loadingLabel.waitInvisibility();
        project_CenterTasksArea.skipStart.click();


        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not registred correctly");
        Thread.sleep(3000);
    }

    @DisplayName("Verify user can register and delete account")
    @Description("This is to verify if a normal user can register and delete his account")
    @Epic("Register")
    @Feature("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void registerAndDeleteAccount() throws InterruptedException {
        String email = getAlphaNumericString(6) + "@gmail.com";
        String pwd = getAlphaNumericString(8);

        //REGISTER
        mainPage.registerButton.click();
        registerPage.emailTextbox.setText(email);
        registerPage.pwdTextbox.setText(pwd);
        registerPage.registerBtn.click();
        loadingPage.loadingLabel.waitInvisibility();
        project_CenterTasksArea.skipStart.waitClickable();
        project_CenterTasksArea.skipStart.click();
        project_CenterTasksArea.isWelcomeModalPresent();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not registred correctly");

        //DELETE ACCOUNT
        navbar.accountBtn.waitClickable();
        navbar.accountBtn.click();
        navbar.configurationOptBtn.waitClickable();
        navbar.configurationOptBtn.click();
        settingsModal.closeConfirmAccountPopUp.click();
        settingsModal.deleteAccountBtn.waitClickable();
        settingsModal.deleteAccountBtn.click();
        settingsModal.pwdDeleteTextbox.waitPresence();
        settingsModal.pwdDeleteTextbox.setText(pwd);
        settingsModal.confirmDeleteBtn.waitClickable();
        settingsModal.confirmDeleteBtn.click();

        try {
            navbar.accountBtn.getWaitInstance().until(ExpectedConditions.urlToBe("https://todoist.com/auth/account-deleted"));
            Assertions.assertTrue(true);
        }catch (Exception e){
            Assertions.assertTrue(false, "Error account was not deleted");
        }
        Thread.sleep(3000);
    }

    @DisplayName("Verify user can login and logout")
    @Epic("Login")
    @Feature("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void logoutTest() throws InterruptedException {

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //LOGOUT
        navbar.accountBtn.waitClickable();
        navbar.accountBtn.click();
        navbar.accountBtn.waitClickable();
        navbar.logoutOptBtn.click();

        Assertions.assertTrue(mainPage.loginButton.isControlDisplayed(),"ERROR user was not logged out");
        Thread.sleep(3000);
    }
}
