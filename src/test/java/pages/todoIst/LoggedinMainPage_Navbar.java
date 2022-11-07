package pages.todoIst;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class LoggedinMainPage_Navbar {
    public Button addTask = new Button(By.id("quick_add_task_holder"),"Add task navbar button");
    public Button accountBtn = new Button(By.xpath("//button[@id]//img"));
    public Button configurationBtn = new Button(By.id("user_menu_settings_menuitem"));
}
