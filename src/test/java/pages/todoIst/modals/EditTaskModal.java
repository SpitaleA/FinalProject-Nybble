package pages.todoIst.modals;

import controlSelenium.Button;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

public class EditTaskModal {
    public Button comboBoxPriorityBtn = new Button(By.xpath("//div[text()=\"Prioridad\"]/following-sibling::div//div/button[@aria-haspopup=\"listbox\"]"),
            "Priority combobox");

    public Button setDateBtn = new Button(By.xpath("//div[text()=\"Fecha de vencimiento\"]/..//button"),
            "Due date selection button");
    public TextBox setDateTextBox = new TextBox(By.xpath("//input[@placeholder=\"Introduce una fecha de vencimiento\"]"),"Due date textbox");
    public Button closeModalBtn = new Button(By.xpath("//button[@aria-label=\"Cerrar ventana\"]"),"Close edit task modal button");
    public Button moreOptions = new Button(
            By.xpath("//div[@class=\"f9408a0e f53218d5\"]/button[contains(@aria-label,\"s acciones\")]")
            ,"More options button");
    public Button addSubTaskBtn = new Button(By.xpath("//div[@data-testid=\"task-main-content-container\"]//button[@aria-disabled]"));
    public Button deleteTaskBtn = new Button(By.xpath("//div[contains(text(),\"Eliminar tar\")]"),"Delete task button");
    public TextBox subtaskNameTextbox = new TextBox(By.xpath("(//div[@class=\"richtextinput\"]/div/div/div/div/div/div/span)[last()]"));
    public TextBox subtaskNameTextbox2 = new TextBox(By.xpath("(//div[@class=\"richtextinput\"]/div/div/div/div/div/div/span/span)[last()]"));
    public Button confirmAddSubTaskBtn = new Button(By.xpath("//button[@type=\"submit\"]"));
    public Button setPriority(String priorityNumber){
        Button prioritySet = new Button(By.xpath("//span[@class=\"priority_picker_item_name\" and text()=\"Prioridad " + priorityNumber +"\"]"),
                "Prioriry " + priorityNumber + " button");
        return prioritySet;
    }

}
