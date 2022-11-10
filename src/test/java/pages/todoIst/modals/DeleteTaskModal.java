package pages.todoIst.modals;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class DeleteTaskModal {
    public Button confirmDeleteBtn = new Button(By.xpath("//button/following-sibling::button[@type=\"submit\"]"),"Delete task button confirmation");
    public Button closeModalBtn = new Button(By.xpath("//div[@data-testid=\"button-container\"]/button"));
}
