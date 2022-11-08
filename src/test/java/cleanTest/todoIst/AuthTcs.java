package cleanTest.todoIst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import singletonSession.Session;

import java.util.Date;

import static utils.RandomString.getAlphaNumericString;

public class AuthTcs extends TestBaseTodoIst{

    @Test
    public void register() throws InterruptedException {
        String email = getAlphaNumericString(6) + "@gmail.com";
        String pwd = getAlphaNumericString(8);

        mainPage.registerButton.click();
        registerPage.emailTextbox.setText(email);
        registerPage.pwdTextbox.setText(pwd);
        registerPage.registerBtn.click();
        loggedinMainPage_CenterTasksArea.skipStart.click();
        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not registred correctly");
        Thread.sleep(10000);


    }
    @Test
    public void registerAndDeleteAccount() throws InterruptedException {
        String email = getAlphaNumericString(6) + "@gmail.com";
        String pwd = getAlphaNumericString(8);

        mainPage.registerButton.click();
        registerPage.emailTextbox.setText(email);
        registerPage.pwdTextbox.setText(pwd);
        registerPage.registerBtn.click();
        loggedinMainPage_CenterTasksArea.skipStart.click();
        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not registred correctly");
//        Thread.sleep(10000);
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.configurationBtn.click();
        settingsModal.deleteAccountBtn.click();
        settingsModal.pwdDeleteTextbox.setText(pwd);
        settingsModal.confirmDeleteBtn.click();
        Assertions.assertEquals("https://todoist.com/auth/account-deleted",
                                Session.getInstance().getBrowser().getCurrentUrl(),
                                "Error account was not deleted");
    }

    @Test
    public void logoutTest() throws InterruptedException {

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //LOGOUT
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.logoutBtn.click();

        Assertions.assertTrue(mainPage.loginButton.isControlDisplayed(),"ERROR user was not logged out");
    }

    @Test
    public void crudProject() throws InterruptedException {

    String projectName = "name " + new Date().getTime();
    String tareaNueva = "task 1";


    //LOGIN

        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


    //CREATE PROJECT
        leftPanel_LeftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
//        numberOfTasksBefore = loggedinMainPage_CenterTasksArea.tasks.findControls();

    //CREATE TASK
        loggedinMainPage_CenterTasksArea.addTaskBtn.click();
        loggedinMainPage_CenterTasksArea.setDatebtn.click();
        loggedinMainPage_CenterTasksArea.setDateTextBox.setText("24/10/22" + Keys.ENTER);
        loggedinMainPage_CenterTasksArea.setPriorityBtn.click();
        loggedinMainPage_CenterTasksArea.setPriority("4").click();
        loggedinMainPage_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(tareaNueva);
        loggedinMainPage_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        loggedinMainPage_CenterTasksArea.confirmAddTaskbtn.click();


        Assertions.assertTrue(loggedinMainPage_CenterTasksArea.selectTaskByName(tareaNueva).isControlDisplayed(),"ERROR no se creo la task");

    //EDIT TASK
        loggedinMainPage_CenterTasksArea.selectTaskByName(tareaNueva).click();
        editTaskModal.comboBoxPriorityBtn.click();
        editTaskModal.setPriority("3").click();
        editTaskModal.setDateBtn.click();
        editTaskModal.setDateTextBox.setText("25/10/22" + Keys.ENTER);
        editTaskModal.closeModalBtn.click();


        Assertions.assertEquals(priorityMap.get("priority3"),loggedinMainPage_CenterTasksArea.checkBoxLastTask.getCssAttributeValue("color"),"ERROR edit was not succesful");

    //DELETE TASK
        loggedinMainPage_CenterTasksArea.selectTaskByName(tareaNueva).click();
        editTaskModal.moreOptions.click();
        editTaskModal.deleteTaskBtn.waitPresence();
        editTaskModal.deleteTaskBtn.click();
        deleteTaskModal.deleteTaskBtn.click();

//        numberOfTasksAfter = loggedinMainPage_CenterTasksArea.tasks.findControls();

//        Assertions.assertEquals(numberOfTasksBefore,numberOfTasksAfter,"ERROR no se borro la tarea");

        Thread.sleep(4000);


    }
}
