package factoryBrowser;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;

public class Chrome implements IBrowser {

    @Override
    public WebDriver create() {
        //Esto settea el path al archivo driver webdriver
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");

        //Para correr en headless
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        //Maximizar ventana
//        driver.manage().window().setPosition(new Point(2000, 1));
        driver.manage().window().maximize();
        return driver;
    }
}
