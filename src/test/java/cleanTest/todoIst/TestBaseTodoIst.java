package cleanTest.todoIst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.todoIst.*;
import singletonSession.Session;
import utils.GetProperties;

import java.util.HashMap;

public class TestBaseTodoIst {
    protected HashMap<String, String> priorityMap = new HashMap<String, String>(){{
        put("priority1", "rgba(209, 69, 59, 1)");
        put("priority2", "rgba(235, 137, 9, 1)");
        put("priority3", "rgba(36, 111, 224, 1)");
        put("priority4", "rgba(128, 128, 128, 1)");
    }};
    protected String email = GetProperties.getInstance().getUser();
    protected String pwd = GetProperties.getInstance().getPwd();

    // PAGES
    protected LoadingPage loadingPage = new LoadingPage();
    protected SettingsModal settingsModal = new SettingsModal();
    protected MainPage mainPage = new MainPage();
    protected LoginPage loginPage = new LoginPage();
    protected RegisterPage registerPage = new RegisterPage();
    protected LoggedinMainPage_LeftPanelSection leftPanel_LeftPanelSection = new LoggedinMainPage_LeftPanelSection();
    protected EditProjectModal editProjectModal = new EditProjectModal();
    protected LoggedinMainPage_Navbar loggedinMainPage_Navbar = new LoggedinMainPage_Navbar();
    protected LoggedinMainPage_CenterTasksArea loggedinMainPage_CenterTasksArea = new LoggedinMainPage_CenterTasksArea();
    protected EditTaskModal editTaskModal = new EditTaskModal();
    protected DeleteTaskModal deleteTaskModal = new DeleteTaskModal();

    @BeforeEach
    public void setup(){
        Session.getInstance().getBrowser().get(GetProperties.getInstance().getHost());
    }
    @AfterEach
    public void cleanup(){
        Session.getInstance().closeBrowser();
    }
}
