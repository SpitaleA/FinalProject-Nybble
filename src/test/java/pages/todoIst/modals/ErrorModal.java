package pages.todoIst.modals;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

public class ErrorModal {
    public Button okBtn = new Button(By.xpath("//a[@class=\"ist_button ist_button_red\" and text()=\"Ok\"]"));
    public Label errorLabel = new Label(By.xpath("//td[text()=\"Error\"]"));


}
