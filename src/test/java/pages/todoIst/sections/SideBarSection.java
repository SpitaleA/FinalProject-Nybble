package pages.todoIst.sections;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

public class SideBarSection {
    public Button projectsListBtns = new Button(By.xpath("//ul[@id=\"projects_list\"]/li"));
    public Button archivateOptBtn = new Button(By.xpath("//div[contains(text(),\"Archivar proyecto\")]"));
    public Button projectMenuBtn = new Button(By.xpath("//a[@href=\"/app/projects\"]"));
    public Button newProjectButton = new Button(By.xpath("//button[contains(@aria-label,\"adir proyecto\")]"),"New project button");
    public Button editProjectOptBtn = new Button(By.xpath("//li[contains(@role,\"menuitem\")]//div[text()=\"Editar proyecto\"]"),
            "Edit project option button");
    public Button deleteProjectOptBtn = new Button(By.xpath("(//li//div[text()])[last()]/.."),"Delete project option button");
//    public  Label projectList = new Label(By.xpath("//ul[@id=\"projects_list\"]/li"),"Projects list labels");
    public Button inboxBtn = new Button(By.xpath("//li[@id='filter_inbox']//a"));
//    public Button confirmDeleteBtn = new Button(By.xpath("//button[@type=\"submit\"]"));
    public Boolean isInboxSelected(){
        return new Button(By.cssSelector("#filter_inbox>div>div")).getCssAttributeValue("background-color").equals("rgba(238, 238, 238, 1)");
    }
    public Button findProjectByName(String projectName){
        Button optionsProject = new Button(By.xpath("(//a[contains(@aria-label,'" + projectName +"')]/following-sibling::div/button[contains(@type,'button')])[last()]"),
                "Options project with name " + projectName + " button");
        return optionsProject;
    }
}
