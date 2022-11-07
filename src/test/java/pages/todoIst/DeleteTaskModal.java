package pages.todoIst;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class DeleteTaskModal {
    public Button deleteTaskBtn = new Button(By.xpath("//button[@type=\"submit\"]"),"Delete task button confirmation");
}
