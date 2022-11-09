package pages.todoIst.modals;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class DeleteTaskModal {
    public Button confirmDeleteBtn = new Button(By.xpath("//button[@type=\"submit\"]"),"Delete task button confirmation");
}
