package cleanTest.phpTravel;

import utils.RunnerExtension;
import utils.Watcher;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import singletonSession.Session;

import java.util.ArrayList;
import java.util.Date;

public class TestSuite1 extends  TestBasePhpTravel{
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
    @ExtendWith(RunnerExtension.class)
    public void verifyRegisterSuccessfully() throws InterruptedException {
        String initHandle = Session.getInstance().getBrowser().getWindowHandle();
        String randomEmail = "email" + new Date().getTime() +"@gmail.com";
        mainPage.registerBtn.click();
        ArrayList<String> tabs = new ArrayList<String> (Session.getInstance().getBrowser().getWindowHandles());
//        Thread.sleep(5000);
        Session.getInstance().getBrowser().switchTo().window(tabs.get(1));
        registerPage.firstName.setText("agustin");
        registerPage.lastName.setText("lopez");
        registerPage.email.setText(randomEmail);
        registerPage.phone.setText("123456789");
        registerPage.address.setText("san juan");
        registerPage.address2.setText("cordoba");
        registerPage.city.setText("rosario");
        registerPage.state.setText("santa fe");
        registerPage.postCode.setText("2000");
        registerPage.countryComboboxBtn.click();
        registerPage.findCountryBtn("Argentina").click();
        registerPage.mobilePhone.setText("123456789");
        registerPage.pwd.setText("123456789");
        registerPage.confirmPwd.setText("123456789");
        Session.getInstance().getBrowser().switchTo().frame(registerPage.captchaFrame.getAttributeValue("name"));
        registerPage.controlLable.waitUntilElementHasHtmlAttribute("type","hidden");

        registerPage.capcha.hoverAction();
        registerPage.capcha.waitClickable();
        registerPage.capcha.waitVisibility();
        registerPage.capcha.click();
        Thread.sleep(12000);


        Session.getInstance().getBrowser().switchTo().defaultContent();
        registerPage.submitRegister.waitClickable();
        registerPage.submitRegister.click();

//        Session.getInstance().getBrowser().switchTo().window(tabs2.get(1));

        Assertions.assertEquals("agustin lopez",loggedInPage.fullNameLabel.getText(),"ERROR la cuenta no fue creada");
        loggedInPage.logoutBtn.click();

        Assertions.assertTrue(loginPage.loginBtn.isControlDisplayed(),"ERROR la cuenta no se desloggeo");
        Thread.sleep(5000);
    }
}
