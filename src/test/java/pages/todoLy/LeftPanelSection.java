package pages.todoLy;

import controlSelenium.Button;
import controlSelenium.Control;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class LeftPanelSection {
    public Button newProjectButton = new Button(By.cssSelector("#MainTable > tbody > tr > td.MainTableLeft > div:nth-child(6) > div"));
    public Button addButton = new Button(By.id("NewProjNameButton"));
    public TextBox inputProjectTextBox = new TextBox(By.id("NewProjNameInput"));
    public TextBox inputProjectEditTextBox = new TextBox(By.id("ItemEditTextbox"));
    public Button itemEditSumbitButton = new Button(By.id("ItemEditSubmit"));
//    public Button lastProjectButton;
    public Button editProjectButton = new Button(By.xpath("//*[@id=\"projectContextMenu\"]/li[1]/a"));
    public Button deleteProjectButton = new Button(By.id("ProjShareMenuDel"));

    public Button findLastProjectWithNameOption(String name){
        Button projectOptionButton = new Button(By.xpath("//li[last()]//td[text()='" + name + "']//following-sibling::td//div[@style=\"display: block;\"]//img[@src=\"/Images/dropdown.png\"]"));
        return projectOptionButton;
    }
    public Button findLastProjectWithName(String name){
        Button lastProjectButton = new Button(By.xpath("//li[last()]//td[text()='" + name + "']"));
        return lastProjectButton;
    }

}
