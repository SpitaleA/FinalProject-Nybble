package pages.todoIst;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

public class LoggedinMainPage_Navbar {
    public Button addTask = new Button(By.id("quick_add_task_holder"),"Add task navbar button");
    public Button accountBtn = new Button(By.xpath("//button[@id]//img"));
    public Button configurationBtn = new Button(By.id("user_menu_settings_menuitem"));
    public Button logoutBtn = new Button(By.xpath("//button[@id and @role=\"menuitem\"]/following-sibling::button"));
}
