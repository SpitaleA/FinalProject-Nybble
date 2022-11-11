package pages.todoIst.centersPages;

import controlSelenium.Button;
import controlSelenium.CheckBox;
import controlSelenium.Label;
import controlSelenium.TextBox;
import org.openqa.selenium.By;

import static utils.ByUtils.getXpath;

public class ProjectCenterTasksArea {

    // **************************************** BUTTONS ****************************************
    public Button addTaskCenterBtn = new Button(By.xpath("//button[@class=\"plus_add_button\"]"),"Add task button");
    public Button dateSelectionWindowBtn = new Button(By.xpath("//button[@class=\"item_due_selector icon_pill\"]"),"Due date icon button");
    public Button sopenPriorityOptionsCombobox = new Button(By.xpath("//button[@class=\"item_action item_actions_priority\"]"),
            "Open priority options combobox");
    public Button confirmAddTaskbtn = new Button(By.xpath("//button[@data-testid=\"task-editor-submit-button\"]"), "Confirm add task button");
    public Button tasksList = new Button(By.xpath("//ul[@class=\"items\"]/li"),"Tasks displayed buttons");
    public Button skipStart = new Button(By.xpath("//nav//button"),"Skip intro at register button");
    public Button openViewOptBtn = new Button(By.xpath("//div[@class=\"view_header__content\"]//button[@class]"), "open project view options button");
    public Button openLayoutsOptBtn = new Button(By.xpath("//header//a[contains(@href,\"/articles/\")]/../following-sibling::ul/li"),"Open layout options button");
    public Button panelViewOptBtn = new Button(By.xpath("//ul[@role=\"listbox\"]/li[@id and @data-value=\"board\"]"), "Panel view option button");
    public Button addSectionToPanelBtn = new Button(By.xpath("//button[@class=\"board_add_section_button\"]"), "Add section to project panel button");
    public Button addTaskInSectionConfirmBtn = new Button(By.xpath("//button[@data-testid]"), "Add task in section confirm button");
    public Button dragTaskIconBtn = new Button(By.xpath("//div[@class=\"task_list_item__drag_container\"]/span"), "Drag task icon");
    public Button setReminderForTaskBtn = new Button(By.xpath("//div[text()=\"Recordatorios\"]/.."),"Set reminder for task button");
//    public Button updateToProBtn = new Button(By.xpath("//a[@href=\"/premium/upgrade\"]"));
    public Button deleteTaskOptionBtn = new Button(By.xpath("(//li//div[text()])[last()]/.."),"Delete task option button");
    public Button closeWelcomeModalBtn = new Button(By.xpath("//button[@aria-label=\"Close modal\"]"), "Close welcome modal button");

    // **************************************** TEXTBOX ****************************************
    public TextBox titleTaskEditTextBox = new TextBox(By.xpath("//div[@class=\"notranslate public-DraftEditor-content\"]"),"Task name textbox");
    public TextBox setDateTextBox = new TextBox(By.xpath("//div[@class=\"scheduler-input\"]/input[@value]"), "Due date textbox");
    public TextBox sectionNameTextbox = new TextBox(By.xpath("//input[@class=\"name\"]"), "Section name textbox");
    public TextBox taskNameInSectionTextbox = new TextBox(By.cssSelector(".notranslate"),"Task name in section textbox");


    // **************************************** LABEL ****************************************
    public Label actualSectionDisplayedTitleLabel = new Label(By.xpath("//div[@id=\"editor\"]//h1/span[@class=\"simple_content\"]"),
            "Actual section title displayed label");


    // **************************************** FUNCTIONS ****************************************
    public CheckBox getCheckBoxFromTaskByName(String taskName){
        try {
            String taskXpath = getXpath(getTaskByName(taskName).getControl());
            CheckBox taskCheckbox = new CheckBox(By.xpath(taskXpath + "/../../../..//*[@class=\"task_checkbox__circle\"]"),"Task "+ taskName + " checkbox");
            return taskCheckbox;

        } catch (Exception e){
            return null;
        }
    }

    public Button getTaskByName(String taskName){
        try {
            Button taskByNameBtn = new Button(By.xpath("//div[text()=\"" + taskName + "\"][last()]"),"Task with name " + taskName + " button");
            return taskByNameBtn;
        } catch (Exception e){
            return null;
        }
    }
    public Button getTaskDateByName(String taskName){
        String xpath = getXpath(getTaskByName(taskName).getControl());
        Button taskDate = new Button(By.xpath(xpath +"/../../following-sibling::*//span[contains(@class,\"date\")]"), "Date for task " + taskName);
        return taskDate;
    }
    public Button getTaskInSectionByName(String sectionName, String taskName){
        Button panelProjectTaskBtn = new Button(By.xpath(
                "//span[text()=\""+ sectionName +"\"]//../../../..//following-sibling::div[@class=\"board_section__task_list\"]//div[text()=\""+ taskName +"\"]/../../../../.."),
                "Task " + taskName +" in section " + sectionName);
        return panelProjectTaskBtn;
    }

    public Button addTaskInSectionBySectionName(String sectionName){
        Button addTaskInPanelBtn = new Button(By.xpath(
                "//header[@id]//span[@class=\"simple_content\" and text()=\"" + sectionName + "\"]/../../../../following-sibling::footer//button"),
                "Add task in section " + sectionName + " button");
        return addTaskInPanelBtn;
    }
    public Button findSectionByName(String sectionName){
        Button sectionByName = new Button(By.xpath("//header[@id]//span[@class='simple_content' and text()='"+ sectionName +"']"),
                "Find section with name " + sectionName);
        return sectionByName;
    }
    // todo --> si no uso estos 2 borrarlo
    public Button findSectionReleaseDragAreaBySectionName(String sectionName){
        String xpath = getXpath(findSectionByName(sectionName).getControl());
        Button releaseAreaSection = new Button(By.xpath(xpath + "/../../../../following-sibling::div/..//footer"));
        return releaseAreaSection;
    }

    public Button findSectionReleaseDragArea2BySectionName(String sectionName){
        String xpath = getXpath(findSectionReleaseDragAreaBySectionName(sectionName).getControl());
        Button releaseAreaSection2 = new Button(By.cssSelector(".releaseAreaSection"));
        return releaseAreaSection2;
    }
    public Button getPriorityButtonByNumber(String priorityNumber){
        Button prioritySet = new Button(By.xpath("//span[@class=\"priority_picker_item_name\" and text()=\"Prioridad " + priorityNumber +"\"]"),
                "Priority " + priorityNumber + " button");
        return prioritySet;
    }
    public void isWelcomeModalPresent(){
        if(closeWelcomeModalBtn.isControlDisplayed()){
            closeWelcomeModalBtn.doubleClickAction();
        }
    }

}
