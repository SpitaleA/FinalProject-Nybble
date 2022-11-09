package pages.todoIst.modals;

import controlSelenium.Button;
import controlSelenium.CheckBox;
import controlSelenium.TextBox;
import enums.ProjectCircleColors;
import org.openqa.selenium.By;

public class EditProjectModal {

    public Button addButton = new Button(By.xpath("//button[@type=\"submit\"]"),"Create new project button");
    public TextBox inputProjectTextBox = new TextBox(By.id("edit_project_modal_field_name"),"Project name texbox");
    public CheckBox favoriteCheckbox = new CheckBox(By.xpath("//input[@type=\"checkbox\"]/.."));
    public Button colorCombobox = new Button(By.cssSelector(".color_dropdown_toggle"));

    public Button setOptColorCombobox(ProjectCircleColors color){
        Button colorSelected = new Button(By.xpath("//li[@aria-label=\""+color +"\"]"));
        return colorSelected;
    }



}
