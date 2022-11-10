package pages.todoIst.modals;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class ArchiveProjectModal {
    public Button confirmArchiveProjectBtn = new Button(By.xpath("//button[@type=\"submit\"]"));
    public Button closeModalBtn = new Button(By.xpath("//div[@data-testid=\"button-container\"]/button"));
}
