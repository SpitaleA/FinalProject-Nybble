package controlSelenium;

import factoryBrowser.FactoryBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import singletonSession.Session;
import utils.GetProperties;

import java.time.Duration;

public class SingletonWait {
    private static controlSelenium.SingletonWait waitInstance=null;
    private WebDriverWait webDriverWait;
    private SingletonWait(){
        webDriverWait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
    }

    public static controlSelenium.SingletonWait getInstance(){
        if (waitInstance == null)
            waitInstance= new SingletonWait();
        return waitInstance;
    }
    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }
}
