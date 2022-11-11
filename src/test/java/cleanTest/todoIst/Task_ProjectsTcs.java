package cleanTest.todoIst;

import enums.ProjectCircleColors;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.Keys;
import singletonSession.Session;
import utils.RunnerExtension;
import utils.WaitUtil;

import java.util.Date;

import static utils.RandomString.getAlphaNumericString;

public class Task_ProjectsTcs extends TestBaseTodoIst{

    @DisplayName("Verify a user can create a new task in inbox")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void createTaskInInbox() throws InterruptedException {
        String newTask = "task " + new Date().getTime();

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com", "Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

        //GO TO INBOX SECTION
        sideBarSection.inboxBtn.click();

        Assertions.assertTrue(sideBarSection.isInboxSelected(),"ERROR inbox is not selected");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed(),"ERROR no se creo la task");

        Thread.sleep(3500);
    }
    @DisplayName("Verify a user can add a project to fauvorites and create a new task in it")
    @Epic("Projects")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.NORMAL)
    @Tag("SmokeTest")
    @Test
    public void createFavoriteProjectWithTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        String newTask = "task " + new Date().getTime();

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email, pwd);
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();



        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.favoriteCheckbox.waitClickable();
        editProjectModal.favoriteCheckbox.check();
        editProjectModal.addButton.waitClickable();
        editProjectModal.addButton.click();
        sideBarSection.projectsListBtns.waitPresence();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore + 1, numberOfProjectsAfter, "ERROR project was not created");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed(),"ERROR no se creo la task");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitClickable();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(3500);
    }

    @DisplayName("Verify a user can create a new project with a different color and add it to fauvorites")
    @Epic("Projects")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.NORMAL)
    @Tag("SmokeTest")
    @ParameterizedTest
    @EnumSource(ProjectCircleColors.class)
    public void createProject_WithColorAndAddFavorites(ProjectCircleColors projectCircleColor) throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        String defaultProjectCircleColor = "rgba(128, 128, 128, 1)";
        String actualProjectCircleColor;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com", "Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.favoriteCheckbox.waitClickable();
        editProjectModal.favoriteCheckbox.check();
        editProjectModal.colorCombobox.click();
        editProjectModal.setOptColorCombobox(projectCircleColor).click();
        editProjectModal.addButton.waitClickable();
        editProjectModal.addButton.click();
        sideBarSection.projectsListBtns.waitPresence();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        actualProjectCircleColor = sideBarSection.findProjectCircleByProjectName(projectName).getCssAttributeValue("color");

        Assertions.assertEquals(numberOfProjectsBefore + 1, numberOfProjectsAfter, "ERROR project was not created");
        Assertions.assertNotEquals(defaultProjectCircleColor, actualProjectCircleColor, "ERROR project color was not setted correctly");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        navbar.accountBtn.click();
        navbar.logoutOptBtn.click();
        Thread.sleep(3000);
    }
    @DisplayName("Verify a user can create a new project")
    @Epic("Projects")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("SmokeTest")
    @Test
    public void verifyUserCanCreateProject() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com", "Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        sideBarSection.projectsListBtns.waitPresence();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore + 1, numberOfProjectsAfter, "ERROR project was not created");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");
        Thread.sleep(3000);
    }

    @DisplayName("Verify a user can create a new task with priority 1 and due date for tomorrow")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
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
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.hoverAction();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        sideBarSection.projectsListBtns.waitPresence();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.dateSelectionWindowBtn.click();
        project_CenterTasksArea.setDateTextBox.setText(date + Keys.ENTER);
        project_CenterTasksArea.sopenPriorityOptionsCombobox.click();
        project_CenterTasksArea.getPriorityButtonByNumber("4").click();
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
        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(3000);
    }

    @DisplayName("Verify a user can delete a task")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
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
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        sideBarSection.projectsListBtns.waitPresence();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");
        navbar.accountBtn.waitUrlToMatchRegexExpression("");

        //CREATE TASK
        numberOfTasksBefore = project_CenterTasksArea.tasksList.getControlsQuantity();
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();
        navbar.accountBtn.waitClickable();

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==2,"ERROR task was not created");

        //DELETE A TASK
        project_CenterTasksArea.getTaskByName(newTask).waitClickable();
        project_CenterTasksArea.getTaskByName(newTask).makeRightClickAction();
        project_CenterTasksArea.deleteTaskOptionBtn.click();
        deleteTaskModal.confirmDeleteBtn.click();

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==1,"ERROR task was deleted");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(3000);
    }

    @DisplayName("Verify a user can delete a project")
    @Epic("Projects")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("SmokeTest")
    @Test
    public void deleteProject() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        sideBarSection.projectsListBtns.waitPresence();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

    }

    @DisplayName("Verify a user can check a task")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("SmokeTest")
    @Test
    public void checkingTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfTasksBefore;
        int numberOfTasksAfter;
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //CREATE TASK
        numberOfTasksBefore = project_CenterTasksArea.tasksList.getControlsQuantity();
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        navbar.accountBtn.waitUrlToMatchRegexExpression("a");

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

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(3000);
    }

    @DisplayName("Verify a normal user cant set a reminder for a task")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void verifySetReminderNotAvailable() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfTasksBefore;
        int numberOfTasksAfter;
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //CREATE TASK
        numberOfTasksBefore = project_CenterTasksArea.tasksList.getControlsQuantity();
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        navbar.accountBtn.waitUrlToMatchRegexExpression("a");

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==2,"ERROR task was not created");


        //SET REMINDER FOR TASK
        project_CenterTasksArea.getTaskByName(newTask).waitClickable();
        project_CenterTasksArea.getTaskByName(newTask).makeRightClickAction();
        project_CenterTasksArea.setReminderForTaskBtn.click();

        Assertions.assertTrue(getProModal.updateToProBtn.isControlDisplayed(),"ERROR reminder is available");

        getProModal.closeModalBtn.makeRightClickAction();
        getProModal.closeModalBtn.click();

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(3500);

    }

    @DisplayName("Verify a user can create  2 subtask in a task")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void add2SubTasks() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfTasksBefore;
        int numberOfTasksAfter;
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.hoverAction();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //CREATE TASK
        numberOfTasksBefore = project_CenterTasksArea.tasksList.getControlsQuantity();
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        navbar.accountBtn.waitUrlToMatchRegexExpression("a");
        project_CenterTasksArea.tasksList.waitVisibility();
        Assertions.assertEquals(2,project_CenterTasksArea.tasksList.getControlsQuantity(),"ERROR task was not created");


        //ADD SUBTASKS
        project_CenterTasksArea.getTaskByName(newTask).waitClickable();
        project_CenterTasksArea.getTaskByName(newTask).click();
        editTaskModal.comboBoxPriorityBtn.click();
        editTaskModal.setPriority("3").click();
        editTaskModal.addSubTaskBtn.waitClickable();
        editTaskModal.addSubTaskBtn.click();
        editTaskModal.subtaskNameTextbox.waitPresence();
        editTaskModal.subtaskNameTextbox.setTextnoClear(" subtask 1");

        editTaskModal.confirmAddSubTaskBtn.waitClickable();
        editTaskModal.confirmAddSubTaskBtn.click();
        editTaskModal.subtaskNameTextbox.waitPresence();
        editTaskModal.subtaskNameTextbox.setTextnoClear(" subtask 2");
        editTaskModal.confirmAddSubTaskBtn.waitClickable();
        editTaskModal.confirmAddSubTaskBtn.click();
        editTaskModal.closeModalBtn.waitClickable();
        editTaskModal.closeModalBtn.click();

        navbar.accountBtn.waitClickable();
        numberOfTasksAfter = project_CenterTasksArea.tasksList.getControlsQuantity();
        Assertions.assertEquals(numberOfTasksBefore+3,numberOfTasksAfter
                ,"ERROR edit was not succesful");

        sideBarSection.projectMenuBtn.waitClickable();

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");


        Thread.sleep(3500);
    }

    @DisplayName("Verify a user can edit priority in a new task")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void setPriorityInEditModalTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

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
        editTaskModal.closeModalBtn.click();

        Assertions.assertEquals(priorityMap.get("priority3"),
                project_CenterTasksArea.getCheckBoxFromTaskByName(newTask).getCssAttributeValue("color")
                ,"ERROR edit was not succesful");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(3500);
    }

    @DisplayName("Verify a user can't create a new project with empty name")
    @Epic("Projects")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void createProjectWithEmptyName() throws InterruptedException {
        int projectsQuantityBefore;
        int projectsQuantityAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        projectsQuantityBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText("");
        editProjectModal.addButton.click();
        projectsQuantityAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(projectsQuantityBefore,projectsQuantityAfter,"ERROR an empty project was created");

        Thread.sleep(2000);
    }

    @DisplayName("Verify user can't create a task with empty name")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void createTaskWithEmptyName() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(""+Keys.ENTER);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.tasksList.getControlsQuantity()==1,"ERROR an empty task was created");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(2000);
    }
    @DisplayName("Verify a user cant create a new task with a past due date")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void createTaskWithPastDueDate() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        String pastDate = todayDate.minusDays(1).format(formatter);
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.dateSelectionWindowBtn.click();
        project_CenterTasksArea.setDateTextBox.setText(pastDate + Keys.ENTER);
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();
        if (navbar.syncWaitIconBtn.isControlDisplayed())
            navbar.syncWaitIconBtn.waitInvisibility();


        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed(),
                "ERROR no se creo la task");
        System.out.println(project_CenterTasksArea.getTaskDateByName(newTask).getCssAttributeValue("color"));
        Assertions.assertTrue(project_CenterTasksArea.getTaskDateByName(newTask).getCssAttributeValue("color").equals(datesColors.get("overdueDate")),
                "ERROR task was created but the date was wrong setted");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(2000);


    }

    @DisplayName("Verify a user can search for a task by task name")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.NORMAL)
    @Tag("SmokeTest")
    @Test
    public void serachTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String tareaNueva = "task " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;


        //LOGIN

        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");
//        numberOfTasksBefore = loggedinMainPage_CenterTasksArea.tasks.findControls();

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(tareaNueva);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(tareaNueva).isControlDisplayed(),"ERROR no se creo la task");

        navbar.searchBox.click();
        navbar.searchBox.sendKeysAction(tareaNueva + Keys.ENTER);

        Assertions.assertTrue(searchResults.findInSearchResults(tareaNueva).isControlDisplayed(),"ERROR task was not found");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(2000);
    }

    @DisplayName("Verify a user can drag and drop a task from a project to inbox")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void dragAndDropTask() throws InterruptedException {
        String projectName = "name " + new Date().getTime();
        String newTask = "task " + new Date().getTime();
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;


        //LOGIN
        mainPage.loginButton.click();
        loginPage.login("agustin_spitale@hotmail.com","Nybble12345");
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();


        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        //CREATE TASK
        project_CenterTasksArea.addTaskCenterBtn.click();
        project_CenterTasksArea.titleTaskEditTextBox.setTextnoClear(newTask);
        project_CenterTasksArea.confirmAddTaskbtn.waitClickable();
        project_CenterTasksArea.confirmAddTaskbtn.click();

        Assertions.assertTrue(project_CenterTasksArea.getTaskByName(newTask).isControlDisplayed(),"ERROR no se creo la task");

        project_CenterTasksArea.getTaskByName(newTask).hoverAction();
        project_CenterTasksArea.dragTaskIconBtn.dragAndDrop(sideBarSection.inboxBtn.getControl());

        sideBarSection.inboxBtn.waitClickable();
        sideBarSection.inboxBtn.click();

        Assertions.assertEquals(newTask,inboxPage.findTaskByName(newTask).getText(),"ERROR task was not moved to inbox");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(2000);
    }

    @DisplayName("Verify a user can archivate a project")
    @Epic("Projects")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.NORMAL)
    @Tag("SmokeTest")
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
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
//        navbar.accountBtn.waitUrlToMatchRegexExpression("");

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        sideBarSection.findProjectByName(projectName).waitPresence();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.archivateOptBtn.waitClickable();
        sideBarSection.archivateOptBtn.click();
        archiveProjectModal.confirmArchiveProjectBtn.click();
//        Thread.sleep(2000);
        sideBarSection.projectMenuBtn.waitClickable();
        sideBarSection.projectMenuBtn.click();
        projectsMenu.archivedProjectsTabBtn.click();
        projectsMenu.loadingIconLabel.waitInvisibility();

        Session.getInstance().getBrowser().navigate().refresh();

        Assertions.assertEquals(projectName,projectsMenu.findArchivedProjectsByName(projectName).getText(),"ERROR project was not archived");

        Thread.sleep(2000);

    }
    @DisplayName("Verify a user can set the project view to 'panel'")
    @Epic("Projects")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void setProjectViewPanel() throws InterruptedException {
        String projectName = getAlphaNumericString(6);
        int numberOfTasksBefore;
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        String newTask = "task " + new Date().getTime();

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        project_CenterTasksArea.openViewOptBtn.click();
        project_CenterTasksArea.openLayoutsOptBtn.click();
        project_CenterTasksArea.panelViewOptBtn.click();

        project_CenterTasksArea.openLayoutsOptBtn.sendKeysAction(Keys.ESCAPE);

        Assertions.assertTrue(project_CenterTasksArea.sectionNameTextbox.isControlDisplayed()
                ,"ERROR project has not change the layout");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitPresence();
        deleteTaskModal.confirmDeleteBtn.click();
        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(2000);
    }

    @DisplayName("Verify a user can create tasks in a project with panel layout")
    @Epic("Tasks")
    @Feature("ProjectsAndTasks")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("SmokeTest")
    @Test
    public void createTasksInProjectPanelView() throws InterruptedException {
        String projectName = getAlphaNumericString(6);
        int numberOfTasksBefore;
        int numberOfProjectsBefore;
        int numberOfProjectsAfter;
        String newTask = "task " + getAlphaNumericString(5);;
        String section1 = "section " + getAlphaNumericString(5);
        String section2 = "section " + getAlphaNumericString(5);

        //LOGIN
        mainPage.loginButton.click();
        loginPage.login(email ,pwd);
        loadingPage.loadingLabel.waitInvisibility();
        if (currentTimezoneModal.closeModal.isControlDisplayed())
            currentTimezoneModal.closeModal.click();

        Assertions.assertTrue(navbar.addTaskBtn.isControlDisplayed(),"Error user was not logged correctly");

        //CREATE PROJECT
        numberOfProjectsBefore = sideBarSection.projectsListBtns.getControlsQuantity();
        sideBarSection.newProjectButton.click();
        editProjectModal.inputProjectTextBox.setText(projectName);
        editProjectModal.addButton.click();
        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();

        Assertions.assertEquals(numberOfProjectsBefore+1,numberOfProjectsAfter,"ERROR project was not created");

        project_CenterTasksArea.openViewOptBtn.click();
        project_CenterTasksArea.openLayoutsOptBtn.click();
        project_CenterTasksArea.panelViewOptBtn.click();

        project_CenterTasksArea.openLayoutsOptBtn.sendKeysAction(Keys.ESCAPE);
        project_CenterTasksArea.sectionNameTextbox.click();
        project_CenterTasksArea.sectionNameTextbox.setText(section1 + Keys.ENTER);
        project_CenterTasksArea.addSectionToPanelBtn.click();
        project_CenterTasksArea.sectionNameTextbox.setText(section2 + Keys.ENTER);

        project_CenterTasksArea.addTaskInSectionBySectionName(section1).click();
        project_CenterTasksArea.taskNameInSectionTextbox.waitPresence();
        project_CenterTasksArea.taskNameInSectionTextbox.setTextnoClear(newTask);
        project_CenterTasksArea.addTaskInSectionConfirmBtn.waitClickable();
        project_CenterTasksArea.addTaskInSectionConfirmBtn.click();

        // todo --> ver si puedo implmentar el drag and drop
        /*loggedinMainPage_CenterTasksArea.selectTaskInPanelByName("section1","task1").waitClickable();
        loggedinMainPage_CenterTasksArea.selectTaskInPanelByName("section1","task1")
                .dragAndDropHardcore(loggedinMainPage_CenterTasksArea.findSectionReleaseDragAreaBySectionName("section2").getControl(),
                        loggedinMainPage_CenterTasksArea.findSectionReleaseDragArea2BySectionName("section2").getControl());
        Thread.sleep(2000);*/

        Assertions.assertTrue(project_CenterTasksArea.getTaskInSectionByName(section1,newTask).isControlDisplayed()
                ,"ERROR task in section1 was not created");

        //DELETE PROJECT
        sideBarSection.findProjectByName(projectName).hoverAction();
        sideBarSection.findProjectByName(projectName).waitClickable();
        sideBarSection.findProjectByName(projectName).makeRightClickAction();
        sideBarSection.deleteProjectOptBtn.waitClickable();
        sideBarSection.deleteProjectOptBtn.click();
        sideBarSection.projectsListBtns.waitVisibility();
        deleteTaskModal.confirmDeleteBtn.click();
//        deleteTaskModal.confirmDeleteBtn.waitInvisibility();

        numberOfProjectsAfter = sideBarSection.projectsListBtns.getControlsQuantity();
        Assertions.assertEquals(numberOfProjectsBefore,numberOfProjectsAfter,"ERROR project was not deleted");

        Thread.sleep(3500);
    }

}
