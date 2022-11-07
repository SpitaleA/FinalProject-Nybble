package cleanTest.phpTravel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.phpTravels.LoggedInPage;
import pages.phpTravels.LoginPage;
import pages.phpTravels.MainPage;
import pages.phpTravels.RegisterPage;
import singletonSession.Session;
import utils.GetProperties;

public class TestBasePhpTravel {

    public MainPage mainPage = new MainPage();
    public RegisterPage registerPage= new RegisterPage();
    public LoggedInPage loggedInPage = new LoggedInPage();
    public LoginPage loginPage = new LoginPage();
    @BeforeEach
    public void setup(){
        // todo create properties file
        Session.getInstance().getBrowser().get(GetProperties.getInstance().getHost());

    }
    @AfterEach
    public void cleanup(){
//        LoggerTest loggerTest = new LoggerTest();
//        attach();
        Session.getInstance().closeBrowser();
    }




}
