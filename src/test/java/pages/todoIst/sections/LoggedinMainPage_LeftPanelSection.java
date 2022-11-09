package pages.todoIst.sections;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class LoggedinMainPage_LeftPanelSection {
    public Button listaProyectos= new Button(By.xpath("//ul[@id=\"projects_list\"]/li"));

    public Button archivateProjectBtn = new Button(By.xpath("//div[contains(text(),\"Archivar proyecto\")]"));
    public Button projectMenu = new Button(By.xpath("//a[@href=\"/app/projects\"]"));
    public Button newProjectButton = new Button(By.xpath("//button[contains(@aria-label,\"adir proyecto\")]"),"New project button");
    public Button editProject = new Button(By.xpath("//li[contains(@role,\"menuitem\")]//div[text()=\"Editar proyecto\"]"),
            "Edit project option button");
    public Button deleteProjectBtn = new Button(By.xpath("(//li//div[text()])[last()]/.."),"Delete project option button");
    public  Label projectList = new Label(By.xpath("//ul[@id=\"projects_list\"]/li"),"Projects list labels");
    public Button inboxBtn = new Button(By.xpath("//li[@id='filter_inbox']//a"));
    public Button confirmDeleteBtn = new Button(By.xpath("//button[@type=\"submit\"]"));
    public Boolean isInboxSelected(){
        return new Button(By.cssSelector("#filter_inbox>div>div")).getCssAttributeValue("background-color").equals("rgba(238, 238, 238, 1)");
    }
    public Button opcProjectFindWithName(String projectName){
        Button optionsProject = new Button(By.xpath("//a[contains(@aria-label,'" + projectName +"')]/following-sibling::div/button[contains(@type,'button')]"),
                "Options project with name " + projectName + " button");
        return optionsProject;
    }
    public boolean projectWithNameExist(String name){
        Label deleteProject = new Label(By.xpath("//span[text()='"+name+ "']"), "Project " + name + " label");
        if(deleteProject.isControlDisplayed())
            return true;
        else
            return false;
    }
}
