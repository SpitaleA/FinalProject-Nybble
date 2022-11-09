package cleanTest.todoIst;

import enums.ProjectCircleColors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import singletonSession.Session;
import utils.WaitUtil;

import java.util.Date;

import static utils.RandomString.getAlphaNumericString;

public class Task_ProjectsTcs extends TestBaseTodoIst{
    @Test
    public void createTaskInInbox() throws InterruptedException {
        String newTask = "task " + new Date().getTime();

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com", "Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();

        //GO TO INBOX SECTION
        loggedinMainPage_leftPanelSection.inboxBtn.click();

        Assertions.assertTrue(loggedinMainPage_leftPanelSection.isInboxSelected(),"ERROR inbox is not selected");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed(),"ERROR no se creo la task");

        Thread.sleep(3500);
    }
    @Test
    public void createFavoriteProjectWithTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        String newTask = "task " + new Date().getTime();

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com", "Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();


        //CREATE PROJECT
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.favoriteCheckbox.waitClickable();
        editProjectModal.favoriteCheckbox.check();
        editProjectModal.colorCombobox.click();
        editProjectModal.setOptColorCombobox(ProjectCircleColors.blue).click();
        editProjectModal.addButton.waitClickable();
        editProjectModal.addButton.click();
        loggedinMainPage_leftPanelSection.projectList.waitPresence();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore + 1, numberOfProjectsAfter, "ERROR project was not created");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed(),"ERROR no se creo la task");

        Thread.sleep(3500);
    }

    @Test
    public void CreateProject_WithColorAndAddFavorites() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com", "Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();


        //CREATE PROJECT
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.favoriteCheckbox.waitClickable();
        editProjectModal.favoriteCheckbox.check();
        editProjectModal.colorCombobox.click();
        editProjectModal.setOptColorCombobox(ProjectCircleColors.blue).click();
        editProjectModal.addButton.waitClickable();
        editProjectModal.addButton.click();
        loggedinMainPage_leftPanelSection.projectList.waitPresence();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore + 1, numberOfProjectsAfter, "ERROR project was not created");

        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.logoutBtn.click();
        Thread.sleep(3000);
    }
    @Test
    public void verifyUserCanCreateProject() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com", "Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();


        //CREATE PROJECT
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        loggedinMainPage_leftPanelSection.projectList.waitPresence();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore + 1, numberOfProjectsAfter, "ERROR project was not created");
        Thread.sleep(3000);
    }

        @Test
    public void createTaskWithPriorityAndDueDate() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        String date = todayDate.plusDays(1).format(formatter);
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        String getPriorityColorFromTask;
        String getDate;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();


        //CREATE PROJECT
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        loggedinMainPage_leftPanelSection.projectList.waitPresence();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.dateSelectionWindowBtn.click();
        project_CenterTasksArea.setDateTextBox.setText(date + Keys.ENTER);
        project_CenterTasksArea.sopenPriorityOptionsCombobox.click();
        project_CenterTasksArea.setPriority("4").click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        getPriorityColorFromTask = project_CenterTasksArea.getCheckBoxFromTaskByName(newTask).getCssAttributeValue("color");
        getDate = project_CenterTasksArea.getTaskDateByName(newTask).getText();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed() &&
                                getPriorityColorFromTask.equals(priorityMap.get("priority4")) &&
                                getDate.equals("Mañana")
                                ,"ERROR no se creo la task");
        Assertions.assertTrue(getPriorityColorFromTask.equals(priorityMap.get("priority4")) &&
                                getDate.equals("Mañana")
                                ,"ERROR task was created but not with wrong priority or date");

        Thread.sleep(3000);
    }

    @Test
    public void deleteTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        int numberOfTasksBefore;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.projectList.waitPresence();
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        loggedinMainPage_leftPanelSection.projectList.waitPresence();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");
        loggedinMainPage_Navbar.accountBtn.waitUrlToMatchRegexExpression("a");

        //CREATE TASK
        numberOfTasksBefore = project_CenterTasksArea.tasksList.getControlsQuantity();
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==2,"ERROR task was not created");

        //DELETE A TASK
        project_CenterTasksArea.getTaskByName(newTask).waitClickable();
        project_CenterTasksArea.getTaskByName(newTask).makeRightClickAction();
        project_CenterTasksArea.deleteTaskOptionBtn.click();
        project_CenterTasksArea.confirmDeleteBtn.click();

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==1,"ERROR task was deleted");
        Thread.sleep(3000);
    }

    @Test
    public void deleteProject() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.projectList.waitPresence();
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        loggedinMainPage_leftPanelSection.projectList.waitPresence();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //DELETE PROJECT
        loggedinMainPage_leftPanelSection.opcProjectFindWithName(projectName).hoverAction();
        loggedinMainPage_leftPanelSection.opcProjectFindWithName(projectName).waitClickable();
        loggedinMainPage_leftPanelSection.opcProjectFindWithName(projectName).makeRightClickAction();
        loggedinMainPage_leftPanelSection.deleteProjectBtn.waitClickable();
        loggedinMainPage_leftPanelSection.deleteProjectBtn.click();
        loggedinMainPage_leftPanelSection.projectList.waitVisibility();
        loggedinMainPage_leftPanelSection.confirmDeleteBtn.click();
        loggedinMainPage_leftPanelSection.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

    }

    @Test
    public void checkingTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfTasksBefore;
        int numberOfTasksAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();

        //CREATE TASK
        numberOfTasksBefore = project_CenterTasksArea.tasksList.getControlsQuantity();
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        loggedinMainPage_Navbar.accountBtn.waitUrlToMatchRegexExpression("a");

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==2,"ERROR task was not created");


        //CHECK TASK
        project_CenterTasksArea.getCheckBoxFromTaskByName(newTask).waitClickable();
        project_CenterTasksArea.getCheckBoxFromTaskByName(newTask).click();
        try {
            project_CenterTasksArea.getTaskByName(newTask).waitInvisibility();
            Assertions.assertTrue(true);
        } catch (Exception e){
            Assertions.assertTrue(false,"ERROR task was not checked");
        }

//        Thread.sleep(5000);

    }

    @Test
    public void verifySetReminderNotAvailable() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfTasksBefore;
        int numberOfTasksAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();

        //CREATE TASK
        numberOfTasksBefore = project_CenterTasksArea.tasksList.getControlsQuantity();
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        loggedinMainPage_Navbar.accountBtn.waitUrlToMatchRegexExpression("a");

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==2,"ERROR task was not created");


        //SET REMINDER FOR TASK
        project_CenterTasksArea.getTaskByName(newTask).waitClickable();
        project_CenterTasksArea.getTaskByName(newTask).makeRightClickAction();
        project_CenterTasksArea.setReminderForTaskBtn.click();

        Assertions.assertTrue(project_CenterTasksArea.updateToProBtn.isControlDisplayed(),"ERROR reminder is available");
        Thread.sleep(5000);

    }

    @Test
    public void addSubTasks() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfTasksBefore;
        int numberOfTasksAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();

        //CREATE TASK
        numberOfTasksBefore = project_CenterTasksArea.tasksList.getControlsQuantity();
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        loggedinMainPage_Navbar.accountBtn.waitUrlToMatchRegexExpression("a");
        WaitUtil.setDriver(Session.getInstance().getBrowser());
//        WaitUtil.waitForAngularLoad();
//        WaitUtil.waitForJQueryLoad();
        WaitUtil.waitUntilJSReady();
        WaitUtil.waitUntilAngularReady();
        WaitUtil.waitUntilJQueryReady();
        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==2,"ERROR task was not created");
//        Thread.sleep(1000);
        Session.getInstance().closeBrowser();
//        Thread.sleep(4000);
        //EDIT TASK
        project_CenterTasksArea.getTaskByName(newTask).waitClickable();
        project_CenterTasksArea.getTaskByName(newTask).click();
        editTaskModal.comboBoxPriorityBtn.click();
        editTaskModal.setPriority("3").click();
        editTaskModal.setDateBtn.click();
        editTaskModal.setDateTextBox.setText("25/10/22" + Keys.ENTER);
        editTaskModal.addSubTaskBtn.waitClickable();
        editTaskModal.addSubTaskBtn.click();
//        Thread.sleep(1000);
        editTaskModal.subtaskNameTextbox.waitPresence();
        editTaskModal.subtaskNameTextbox.setTextnoClear(" subtask 1");
//        editTaskModal.subtaskNameTextbox.sendKeysAction(Keys.SPACE);

        editTaskModal.confirmAddSubTaskBtn.waitClickable();
        editTaskModal.confirmAddSubTaskBtn.click();
        editTaskModal.subtaskNameTextbox.waitPresence();
//        editTaskModal.subtaskNameTextbox.sendKeysAction(Keys.SPACE);
        editTaskModal.subtaskNameTextbox.setTextnoClear(" subtask 2");
        editTaskModal.confirmAddSubTaskBtn.waitClickable();
        editTaskModal.confirmAddSubTaskBtn.click();
        editTaskModal.closeModalBtn.waitClickable();
        editTaskModal.closeModalBtn.click();

        loggedinMainPage_Navbar.accountBtn.waitClickable();
        numberOfTasksAfter = project_CenterTasksArea.tasksList.getControlsQuantity();
        Assertions.assertEquals(numberOfTasksBefore+3,numberOfTasksAfter
                ,"ERROR edit was not succesful");

//        if (loggedinMainPage_Navbar.syncWaitIconBtn.isControlDisplayed())
//            loggedinMainPage_Navbar.syncWaitIconBtn.waitInvisibility();
        loggedinMainPage_leftPanelSection.projectMenu.waitClickable();
        loggedinMainPage_leftPanelSection.projectMenu.click();
        projectsMenu.searchActiveProjectsByName(projectName).waitVisibility();
        loggedinMainPage_leftPanelSection.inboxBtn.click();
        loggedinMainPage_leftPanelSection.inboxBtn.waitUrlToMatchRegexExpression("a");
        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.logoutBtn.waitClickable();
        loggedinMainPage_Navbar.logoutBtn.click();
    }

    @Test
    public void taskEdit() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
//        numberOfTasksBefore = loggedinMainPage_CenterTasksArea.tasks.findControls();

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==2,"ERROR task was not created");

        //EDIT TASK
        project_CenterTasksArea.getTaskByName(newTask).click();
        editTaskModal.comboBoxPriorityBtn.click();
        editTaskModal.setPriority("3").click();
        editTaskModal.setDateBtn.click();
        editTaskModal.setDateTextBox.setText("25/10/22" + Keys.ENTER);
        editTaskModal.closeModalBtn.click();


        Assertions.assertEquals(priorityMap.get("priority3"),
                project_CenterTasksArea.lastTaskCheckbox.getCssAttributeValue("color")
                ,"ERROR edit was not succesful");
        Thread.sleep(5000);
    }
    @Test
    public void createProjectWithEmptyName() throws InterruptedException {
        int projectsQuantityBefore;
        int projectsQuantityAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        projectsQuantityBefore = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText("");
        editProjectModal.addButton.click();
        projectsQuantityAfter = loggedinMainPage_leftPanelSection.projectList.getControlsQuantity();

        Assertions.assertEquals(projectsQuantityBefore,projectsQuantityAfter,"ERROR an empty project was created");

    }
    @Test
    public void createTaskWithEmptyName() throws InterruptedException {
        String projectName = "name " + new Date().getTime();

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
//        numberOfTasksBefore = loggedinMainPage_CenterTasksArea.tasks.findControls();

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(""+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==1,"ERROR an empty task was created");
    }
    @Test
    public void createTaskWithPastDueDate() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        String pastDate = todayDate.minusDays(1).format(formatter);

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
//        numberOfTasksBefore = loggedinMainPage_CenterTasksArea.tasks.findControls();

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.dateSelectionWindowBtn.click();
        project_CenterTasksArea.setDateTextBox.setText(pastDate + Keys.ENTER);
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();
        if (loggedinMainPage_Navbar.syncWaitIconBtn.isControlDisplayed())
            loggedinMainPage_Navbar.syncWaitIconBtn.waitInvisibility();


        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed() &&
                project_CenterTasksArea.getTaskDateByName(newTask).getCssAttributeValue("color").equals(datesColors.get("overdueDate")),
                "ERROR no se creo la task");


        System.out.println(project_CenterTasksArea.getTaskDateByName(newTask).getCssAttributeValue("color"));


    }
    @Test
    public void serachTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String tareaNueva = "task " + new Date().getTime();


        //LOGIN

        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
//        numberOfTasksBefore = loggedinMainPage_CenterTasksArea.tasks.findControls();

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(tareaNueva);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(tareaNueva).isControlDisplayed(),"ERROR no se creo la task");

        loggedinMainPage_Navbar.searchBox.click();
        loggedinMainPage_Navbar.searchBox.sendKeysAction(tareaNueva + Keys.ENTER);

        Assertions.assertTrue(searchResults.search(tareaNueva).isControlDisplayed(),"ERROR task was not found");

        Thread.sleep(5000);
    }

    @Test
    public void dragAndDropTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();


        //LOGIN

        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
//        numberOfTasksBefore = loggedinMainPage_CenterTasksArea.tasks.findControls();

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed(),"ERROR no se creo la task");

        project_CenterTasksArea.getTaskByName(newTask).hoverAction();
        project_CenterTasksArea.dragTaskIconBtn.dragAndDrop(loggedinMainPage_leftPanelSection.inboxBtn.getControl());

        loggedinMainPage_leftPanelSection.inboxBtn.waitClickable();
        loggedinMainPage_leftPanelSection.inboxBtn.click();

//        if (loggedinMainPage_Navbar.syncWaitIconBtn.isControlDisplayed())
//            loggedinMainPage_Navbar.syncWaitIconBtn.waitInvisibility();

        Assertions.assertEquals(newTask,inboxPage.findTaskByName(newTask).getText(),"ERROR task was not moved to inbox");

        Thread.sleep(5000);
    }

    @Test
    public void archivateProject() throws InterruptedException {
        String projectName = getAlphaNumericString(6);
        int numberOfTasksBefore;
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //CREATE PROJECT
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.listaProyectos.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.listaProyectos.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        loggedinMainPage_leftPanelSection.opcProjectFindWithName(projectName).makeRightClickAction();
        loggedinMainPage_leftPanelSection.archivateProjectBtn.waitClickable();
        loggedinMainPage_leftPanelSection.archivateProjectBtn.click();
        archiveProjectModal.confirmArchiveProjectBtn.click();
        loggedinMainPage_leftPanelSection.projectMenu.waitClickable();
        if (loggedinMainPage_Navbar.syncWaitIconBtn.isControlDisplayed())
            loggedinMainPage_Navbar.syncWaitIconBtn.waitInvisibility();
        loggedinMainPage_leftPanelSection.projectMenu.click();
        projectsMenu.archivedProjectsTab.click();
        projectsMenu.searchArchivedProjectsByName(projectName).waitClickable();

        Assertions.assertTrue(projectsMenu.searchArchivedProjectsByName(projectName).isControlDisplayed(),"ERROR project was not archived");

        loggedinMainPage_Navbar.accountBtn.click();
        loggedinMainPage_Navbar.logoutBtn.click();

    }
    @Test
    public void setProjectViewPanel(){
        String projectName = getAlphaNumericString(6);
        int numberOfTasksBefore;
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        String newTask = "task 1";
        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //CREATE PROJECT
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.listaProyectos.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.listaProyectos.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        project_CenterTasksArea.openViewOptBtn.click();
        project_CenterTasksArea.openLayoutsOptBtn.click();
        project_CenterTasksArea.panelViewOptBtn.click();

        project_CenterTasksArea.openLayoutsOptBtn.sendKeysAction(Keys.ESCAPE);
//        loggedinMainPage_CenterTasksArea.sectionNameTextbox.click();
//        loggedinMainPage_CenterTasksArea.sectionNameTextbox.setText("section1" + Keys.ENTER);

        Assertions.assertTrue(project_CenterTasksArea.sectionNameTextbox.isControlDisplayed()
                ,"ERROR task was not moved");
    }

    @Test
    public void moveTasksInProjectPanelView() throws InterruptedException {
        String projectName = getAlphaNumericString(6);
        int numberOfTasksBefore;
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        String newTask = "task 1";

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();

        Assertions.assertTrue(loggedinMainPage_Navbar.addTask.isControlDisplayed(),"Error user was not logged correctly");

        //CREATE PROJECT
        numberOfProjectsBefore = loggedinMainPage_leftPanelSection.listaProyectos.getControlsQuantity();
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = loggedinMainPage_leftPanelSection.listaProyectos.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        project_CenterTasksArea.openViewOptBtn.click();
        project_CenterTasksArea.openLayoutsOptBtn.click();
        project_CenterTasksArea.panelViewOptBtn.click();

        project_CenterTasksArea.openLayoutsOptBtn.sendKeysAction(Keys.ESCAPE);
        project_CenterTasksArea.sectionNameTextbox.click();
        project_CenterTasksArea.sectionNameTextbox.setText("section1" + Keys.ENTER);
        project_CenterTasksArea.addSectionToPanelBtn.click();
        project_CenterTasksArea.sectionNameTextbox.setText("section2" + Keys.ENTER);

        project_CenterTasksArea.addTaskInPanelProjectByPanelName("section1").click();
        project_CenterTasksArea.taskNameInSectionTextbox.waitPresence();
        project_CenterTasksArea.taskNameInSectionTextbox.setTextnoClear("task1");
        project_CenterTasksArea.addTaskInSectionConfirmBtn.waitClickable();
        project_CenterTasksArea.addTaskInSectionConfirmBtn.click();

        // todo --> ver si puedo implmentar el drag and drop
        /*loggedinMainPage_CenterTasksArea.selectTaskInPanelByName("section1","task1").waitClickable();
        loggedinMainPage_CenterTasksArea.selectTaskInPanelByName("section1","task1")
                .dragAndDropHardcore(loggedinMainPage_CenterTasksArea.findSectionReleaseDragAreaBySectionName("section2").getControl(),
                        loggedinMainPage_CenterTasksArea.findSectionReleaseDragArea2BySectionName("section2").getControl());
        Thread.sleep(2000);*/

        Assertions.assertTrue(project_CenterTasksArea.selectTaskInPanelByName("section1","task1").isControlDisplayed()
                ,"ERROR task was not moved");
        Thread.sleep(3500);
//        loggedinMainPage_CenterTasksArea.changeViewBtn.click();

    }
    @Test
    public void crudProject() throws InterruptedException {

        String projectName = "name " + new Date().getTime();
        String tareaNueva = "task 1";


        //LOGIN

        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");


        //CREATE PROJECT
        loggedinMainPage_leftPanelSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
//        numberOfTasksBefore = loggedinMainPage_CenterTasksArea.tasks.findControls();

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.dateSelectionWindowBtn.click();
        project_CenterTasksArea.setDateTextBox.setText("24/10/22" + Keys.ENTER);
        project_CenterTasksArea.sopenPriorityOptionsCombobox.click();
        project_CenterTasksArea.setPriority("4").click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(tareaNueva);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();


        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(tareaNueva).isControlDisplayed(),"ERROR no se creo la task");

        //EDIT TASK
        project_CenterTasksArea.getTaskByName(tareaNueva).click();
        editTaskModal.comboBoxPriorityBtn.click();
        editTaskModal.setPriority("3").click();
        editTaskModal.setDateBtn.click();
        editTaskModal.setDateTextBox.setText("25/10/22" + Keys.ENTER);
        editTaskModal.closeModalBtn.click();


        Assertions.assertEquals(priorityMap.get("priority3"), project_CenterTasksArea.lastTaskCheckbox.getCssAttributeValue("color"),"ERROR edit was not succesful");

        //DELETE TASK
        project_CenterTasksArea.getTaskByName(tareaNueva).click();
        editTaskModal.moreOptions.click();
        editTaskModal.deleteTaskBtn.waitPresence();
        editTaskModal.deleteTaskBtn.click();
        deleteTaskModal.confirmDeleteBtn.click();

//        numberOfTasksAfter = loggedinMainPage_CenterTasksArea.tasks.findControls();

//        Assertions.assertEquals(numberOfTasksBefore,numberOfTasksAfter,"ERROR no se borro la tarea");

        Thread.sleep(4000);


    }
}
