package controlSelenium;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import singletonSession.Session;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Control {
    protected By locator;
    protected WebElement control;
    protected String controlName; //reflection
    protected List<WebElement> controls = new ArrayList<>();
    protected WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));

    //********************              CONSTRUCTORS              ********************
    public Control (By locator){
        this.locator=locator;
    }
    public Control (By locator, String controlName){
        this.locator=locator;
        this.controlName=controlName;
    }

    @Step("{0}")
    public void step(String action){}
    protected void findControl(){
        control= Session.getInstance().getBrowser().findElement(this.locator);
//        controls = Session.getInstance().getBrowser().findElements(this.locator);
    }

    public void click(){
//        waitPresence();
        this.findControl();
        this.step("click on CONTROL " +controlName);
        control.click();
    }



    //********************              GETS              ********************
    public String getText(){
        this.findControl();
        this.step("Get Text from " +controlName + ", the value is: "+ control.getText());
        return control.getText();
    }
    public String getAttributeValue(String attribute){
        this.findControl();
        return this.control.getAttribute(attribute);
    }
    public List<WebElement> getControls() {
        this.findControl();
        return controls;
    }
    public String getCssAttributeValue(String style){
        this.findControl();
        return this.control.getCssValue(style);
    }
    public Integer getControlsQuantity(){
        this.findControl();
        return this.controls.size()-1;
    }

    //********************              BOOLEANS              ********************
    public boolean hasAttributeValue(String attribute,String value){
        this.findControl();
        return this.control.getAttribute(attribute).contains(value);
    }
    /** Verifica si el control en cuestion isDisplayed
     * @return  boolean
     */
    public boolean isControlDisplayed(){
        try {
            this.findControl();
            this.step("Check the " +controlName + " is displayed: true");
            return control.isDisplayed();
        }catch (Exception e){
            this.step("Check the " +controlName + " is displayed: false");
            return false;
        }
    }


    //********************              ACTIONS              ********************
    public void makeRightClickAction(){
        this.findControl();
        Actions action = new Actions(Session.getInstance().getBrowser());
        action.contextClick(this.control).perform();
    }
    public void hoverAction(){
        this.findControl();
        Actions action = new Actions(Session.getInstance().getBrowser());
        action.moveToElement(this.control).perform();
    }
    public void sendKeysAction(String text){
        this.findControl();
        Actions action = new Actions(Session.getInstance().getBrowser());
        action.sendKeys(text)
                .perform();
    }

    //********************              WAITS              ********************

    /** Espera a que el control tenga dentro de un ATRIBUTO un texto especifico
     */
    public void waitClickable()
    {
        // todo --> factory para instanciar el wait una sola vez
        this.wait.until(ExpectedConditions.elementToBeClickable(this.locator));
    }
    /** Espera a que el control tenga dentro de un ATRIBUTO un texto especifico
     * @param attribute: es por ejemplo el CLASS en un elemento HTML
     * @param value: valor dentro del ATRIBUTO HTML
     */
    public void waitUntilElementHasHtmlAttribute(String attribute, String value){
//        WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        this.wait.until(ExpectedConditions.attributeContains(this.locator,attribute,value));
    }
    /** Espera a que el control tenga dentro de un ATRIBUTO un texto especifico
     */
    public void waitInvisibility(){
//        WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated(this.locator));
    }
    public void waitIFrameToBeSwitchable(){
        WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
    }
    public void waitVisibility(){
//        WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.locator));
    }
    public void waitSelectable(){
//        WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        this.wait.until(ExpectedConditions.elementToBeSelected(this.locator));
    }
    public void waitPresence(){
//        WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.locator));
    }
    /** Settea el attributo de la clase controls (que es un array de webelements)
     * y lo devuelve
     * @return  array de WebElements
     */



   /* public void waitControl(By locator, int timeOut) throws InterruptedException {
        Label test= new Label(this.locator); // subject
        int i = 0;
        do{
            Thread.sleep(1000);
            i++;
            this.control.click(); // refressh

        }while (!test.isControlDisplayed() || i <= timeOut);


    }*/
}
