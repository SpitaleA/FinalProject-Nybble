package cleanTest.todoIst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import singletonSession.Session;

import static controlSelenium.Control.waitInstance;
import static utils.RandomString.getAlphaNumericString;

public class AuthTcs extends TestBaseTodoIst{

    @Test
    public void register() throws InterruptedException {
        String email = getAlphaNumericString(6) + "@gmail.com";
        String pwd = getAlphaNumericString(8);

        mainPage.registerButton.click();
        registerPage.emailTextbox.setText(email);
        registerPage.pwdTextbox.setText(pwd);
        registerPage.registerBtn.click();
        project_CenterTasksArea.skipStart.click();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not registred correctly");
    }
    @Test
    public void registerAndDeleteAccount() throws InterruptedException {
        String email = getAlphaNumericString(6) + "@gmail.com";
        String pwd = getAlphaNumericString(8);

        //REGISTER
        mainPage.registerButton.click();
        registerPage.emailTextbox.setText(email);
        registerPage.pwdTextbox.setText(pwd);
        registerPage.registerBtn.click();


        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not registred correctly");

        //DELETE ACCOUNT
        project_CenterTasksArea.skipStart.click();
        project_CenterTasksArea.closeWelcomeModalBtn.click();
        navbar.accountBtn.click();
        navbar.configurationOptBtn.click();
        settingsModal.deleteAccountBtn.click();
        settingsModal.pwdDeleteTextbox.setText(pwd);
        settingsModal.confirmDeleteBtn.click();

        try {
            waitInstance.until(ExpectedConditions.urlToBe("https://todoist.com/auth/account-deleted"));
            Assertions.assertTrue(true);
        }catch (Exception e){
            Assertions.assertEquals(false, "Error account was not deleted");
        }
        Thread.sleep(3000);
    }

    @Test
    public void logoutTest() throws InterruptedException {

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //LOGOUT
        navbar.accountBtn.click();
        navbar.logoutOptBtn.click();

        Assertions.assertTrue(mainPage.loginButton.isControlDisplayed(),"ERROR user was not logged out");
    }


}
