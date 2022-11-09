package cleanTest.todoIst;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.todoIst.*;
import pages.todoIst.centersPages.*;
import pages.todoIst.modals.ArchiveProjectModal;
import pages.todoIst.modals.DeleteTaskModal;
import pages.todoIst.modals.EditProjectModal;
import pages.todoIst.modals.EditTaskModal;
import pages.todoIst.sections.LoggedinMainPage_LeftPanelSection;
import pages.todoIst.sections.LoggedinMainPage_Navbar;
import pages.todoIst.settings.SettingsModal;
import singletonSession.Session;
import utils.GetProperties;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class TestBaseTodoIst {
    protected HashMap<String, String> priorityMap = new HashMap<String, String>(){{
        put("priority1", "rgba(209, 69, 59, 1)");
        put("priority2", "rgba(235, 137, 9, 1)");
        put("priority3", "rgba(36, 111, 224, 1)");
        put("priority4", "rgba(128, 128, 128, 1)");
    }};
    protected HashMap<String, String> datesColors = new HashMap<String, String>(){{
        put("overdueDate", "rgba(255, 112, 102, 1)");
        put("todayDate", "rgba(37, 184, 76, 1)");
        put("tomorrowDate", "rgb(255, 154, 20, 1)");
        put("thisWeek", "rgba(169, 112, 255, 1)");
        put("moreThanAWeek", "rgba(255, 255, 255, 0.6)");
    }};
    protected List<String> themeColorsList = new ArrayList<>(Arrays.asList("rgba(219, 76, 63, 1)", "rgba(61, 61, 61, 1)",
                                                            "rgba(40, 40, 40, 1)","rgba(247, 247, 247, 1)","rgba(255 ,144 ,0 ,1)"));
    protected String email = GetProperties.getInstance().getUser();
    protected String pwd = GetProperties.getInstance().getPwd();

    // PAGES
    protected LoadingPage loadingPage = new LoadingPage();
    protected SettingsModal settingsModal = new SettingsModal();
    protected MainPage mainPage = new MainPage();
    protected LoginPage loginPage = new LoginPage();
    protected RegisterPage registerPage = new RegisterPage();
    protected LoggedinMainPage_LeftPanelSection loggedinMainPage_leftPanelSection = new LoggedinMainPage_LeftPanelSection();
    protected EditProjectModal editProjectModal = new EditProjectModal();
    protected LoggedinMainPage_Navbar loggedinMainPage_Navbar = new LoggedinMainPage_Navbar();
    protected ProjectCenterTasksArea project_CenterTasksArea = new ProjectCenterTasksArea();
    protected EditTaskModal editTaskModal = new EditTaskModal();
    protected DeleteTaskModal deleteTaskModal = new DeleteTaskModal();
    protected ProjectsMenu projectsMenu = new ProjectsMenu();
    protected ArchiveProjectModal archiveProjectModal = new ArchiveProjectModal();
    protected SearchResults searchResults = new SearchResults();
    protected InboxPage inboxPage = new InboxPage();
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    protected LocalDate todayDate = LocalDate.now();


    @BeforeEach
    public void setup(){
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("URL", GetProperties.getInstance().getHost())
                        .put("User", GetProperties.getInstance().getUser())
                        .put("Pwd", GetProperties.getInstance().getPwd())
                        .build(), System.getProperty("user.dir")
                        + "/build/allure-results/");
        Session.getInstance().getBrowser().get(GetProperties.getInstance().getHost());
    }
    @AfterEach
    public void cleanup(){
        Session.getInstance().closeBrowser();
    }
}
