package pages.todoIst;

import controlSelenium.Button;
import controlSelenium.CheckBox;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class LoggedinMainPage_CenterTasksArea {
    public Button addTaskBtn = new Button(By.xpath("//button[@class=\"plus_add_button\"]"),"Add task button");
    public TextBox titleTaskEditTextBox = new TextBox(By.xpath("//div[@class=\"notranslate public-DraftEditor-content\"]"),"Task name textbox");
    public Button setDatebtn = new Button(By.xpath("//button[@class=\"item_due_selector icon_pill\"]"),"Due date icon button");
    public TextBox setDateTextBox = new TextBox(By.xpath("//div[@class=\"scheduler-input\"]/input[@value]"), "Due date textbox");
    public Button setPriorityBtn = new Button(By.xpath("//button[@class=\"item_action item_actions_priority\"]"),
            "Open priority options combobox");
    public Button confirmAddTaskbtn = new Button(By.xpath("//button[@data-testid=\"task-editor-submit-button\"]"), "Confirm add task button");
    public CheckBox checkBoxLastTask = new CheckBox(By.xpath("(//button//div[@class=\"task_checkbox__circle\"])[last()]"),"Last task checkbox");
    public Button tasks = new Button(By.xpath("//ul[@class=\"items\"]/li"),"Tasks displayed buttons");
    public Button skipStart = new Button(By.xpath("//nav//button"));

    public Button selectTaskByName(String taskName){
        try {
            Button taskByNameBtn = new Button(By.xpath("//div[text()=\"" + taskName + "\"][last()]"),"Task with name " + taskName + " button");
            return taskByNameBtn;
        } catch (Exception e){
            return null;
        }
    }
    public Button setPriority(String priorityNumber){
        Button prioritySet = new Button(By.xpath("//span[@class=\"priority_picker_item_name\" and text()=\"Prioridad " + priorityNumber +"\"]"),
                "Priority " + priorityNumber + " button");
        return prioritySet;
    }

}
