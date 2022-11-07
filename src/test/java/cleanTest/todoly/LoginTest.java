package cleanTest.todoly;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import utils.GetProperties;
import utils.Watcher;

import static org.junit.jupiter.api.Assertions.fail;
@Tag("Sanity")
public class LoginTest extends  TestBaseTodoLy{
    @Test
    @DisplayName("Verify user can log in")
    @Description("This is to verify if a normal user can login with valid date")
    @Owner("Agustin")
    @Link("www.google.com")
    @Issue("www.jira.com/bug0005")
    @Lead("Testtt")
    @Epic("login")
    @Feature("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Story login")
    @ExtendWith(Watcher.class)
    @Tag("SmokeTest")
    public void verifyLoginSuccessfully(){

            mainPage.loginButton.click();
            loginModal.emailTextBox.setText(GetProperties.getInstance().getUser());
            loginModal.passwordTextBox.setText("");
            loginModal.passwordTextBox.setText(GetProperties.getInstance().getPwd());
            loginModal.loginButton.click();

            Assertions.assertTrue(menuSection.logoutButton.isControlDisplayed(),
                    "ERROR login was not successfully");
    }
}
