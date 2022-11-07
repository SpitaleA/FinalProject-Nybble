package pages.phpTravels;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class MainPage {
    public Button registerBtn = new Button(By.xpath("//li//a[@data-name=\"signup\"]"),"Boton registro");

}
