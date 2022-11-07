package pages.todoIst;

import controlSelenium.Button;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class EditProjectModal {

    public Button addButton = new Button(By.xpath("//button[@type=\"submit\"]"),"Create new project button");
    public TextBox inputProjectTextBox = new TextBox(By.id("edit_project_modal_field_name"),"Project name texbox");



}
