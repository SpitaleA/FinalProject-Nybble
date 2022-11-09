package cleanTest.todoIst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import singletonSession.Session;

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

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not registred correctly");
    }
    @Test
    public void registerAndDeleteAccount() throws InterruptedException {
        String email = getAlphaNumericString(6) + "@gmail.com";
        String pwd = getAlphaNumericString(8);

        mainPage.registerButton.click();
        registerPage.emailTextbox.setText(email);
        registerPage.pwdTextbox.setText(pwd);
        registerPage.registerBtn.click();
        project_CenterTasksArea.skipStart.click();
        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not registred correctly");
//        Thread.sleep(10000);
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.configurationBtn.click();
        settingsModal.deleteAccountBtn.click();
        settingsModal.pwdDeleteTextbox.setText(pwd);
        settingsModal.confirmDeleteBtn.click();
        Assertions.assertEquals("https://todoist.com/auth/account-deleted",
                                Session.getInstance().getBrowser().getCurrentUrl(),
                                "Error account was not deleted");
    }

    @Test
    public void logoutTest() throws InterruptedException {

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //LOGOUT
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.logoutBtn.click();

        Assertions.assertTrue(mainPage.loginButton.isControlDisplayed(),"ERROR user was not logged out");
    }


}
