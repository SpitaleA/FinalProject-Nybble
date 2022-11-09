package pages.todoIst.sections;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

public class Navbar {
    public Button searchBox = new Button(By.xpath("//input[@id]"));
    public Button addTaskBtn = new Button(By.id("quick_add_task_holder"),"Add task navbar button");
    public Button accountBtn = new Button(By.xpath("//button[@id]//img"));
    public Button configurationOptBtn = new Button(By.id("user_menu_settings_menuitem"));
    public Button logoutOptBtn = new Button(By.xpath("//button[@id and @role=\"menuitem\"]/following-sibling::button"));
    public Button changeThemeOptBtn = new Button(By.xpath("//a[@href=\"/app/settings/theme\"]"));
    public Label navbarColorLabel = new Label(By.id("top_bar"));
    public Button syncWaitIconBtn = new Button(By.xpath("//div[@id=\"top_bar_inner\"]//button[@aria-disabled=\"false\" and contains(@class,\"top_bar_btn\")]"));
}
