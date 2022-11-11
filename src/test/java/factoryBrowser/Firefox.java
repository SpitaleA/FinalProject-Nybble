package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

public class Firefox implements IBrowser{
    @Override
    public WebDriver create() {
        System.setProperty("webdriver.gecko.driver","D:\\Documents\\Programacion\\NybbleAcademy\\Automation.CleanStructure\\src\\test\\resources\\driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return driver;
    }
}
