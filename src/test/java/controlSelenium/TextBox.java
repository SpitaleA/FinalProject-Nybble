package controlSelenium;

import org.openqa.selenium.By;

public class TextBox extends Control{

    public TextBox(By locator) {
        super(locator);
    }

    public TextBox(By locator, String controlName) {
        super(locator, controlName);
    }

    /** Busca el control, borra lo que este escrito
     * y escribe lo que se mande por parametro
     */
    public void setText(String value){
        this.findControl();
        this.step("Set on control: "+controlName+" the value: ["+value+"]");
        this.control.clear();
        this.control.sendKeys(value);
    }
    public void setTextnoClear(String value){
        this.findControl();
        this.control.sendKeys(value);
    }

}
