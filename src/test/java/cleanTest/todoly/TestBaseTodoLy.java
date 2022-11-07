package cleanTest.todoly;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.todoLy.*;
import singletonSession.Session;
import utils.GetProperties;
import utils.Watcher;


public class TestBaseTodoLy {
    public MainPage mainPage=new MainPage();
    public LeftPanelSection leftPanelSection = new LeftPanelSection();
    public LoginModal loginModal= new LoginModal();
    public MenuSection menuSection= new MenuSection();
    public SettingsPage settingsPage = new SettingsPage();
    public RegisterModal registerModal = new RegisterModal();
    @BeforeEach
    public void setup(){
        // todo create properties file
        Session.getInstance().getBrowser().get(GetProperties.getInstance().getHost());
    }
    @AfterEach
    public void cleanup(){
        Watcher watcher = new Watcher();
//        attach();
//        Session.getInstance().closeBrowser();
    }



    @Attachment(value = "screenshot",type = "image/png")
    public byte[] attach(){
        // tomar captura de pantalla - adjuntarlo en el reporte
        return ((TakesScreenshot) Session.getInstance().getBrowser()).getScreenshotAs(OutputType.BYTES);
    }





}
