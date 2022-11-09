package pages.todoIst.sections;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

public class LoggedinMainPage_Navbar {
    public Button searchBox = new Button(By.xpath("//input[@id]"));
    public Button addTask = new Button(By.id("quick_add_task_holder"),"Add task navbar button");
    public Button accountBtn = new Button(By.xpath("//button[@id]//img"));
    public Button configurationBtn = new Button(By.id("user_menu_settings_menuitem"));
    public Button logoutBtn = new Button(By.xpath("//button[@id and @role=\"menuitem\"]/following-sibling::button"));
    public Button changeThemeBtn = new Button(By.xpath("//a[@href=\"/app/settings/theme\"]"));
    public Label navbarColor = new Label(By.id("top_bar"));
    public Button syncWaitIconBtn = new Button(By.xpath("//div[@id=\"top_bar_inner\"]//button[@aria-disabled=\"false\" and contains(@class,\"top_bar_btn\")]"));
}
