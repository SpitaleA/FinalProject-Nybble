package pages.todoIst.centersPages;

import controlSelenium.Button;
import org.openqa.selenium.By;

public class ProjectsMenu {
    public Button archivedProjectsTabBtn = new Button(By.id("archived"));
    public Button findArchivedProjectsByName(String projectName){
        Button projectArchivedFound = new Button(By.xpath("//div[@aria-labelledby=\"archived\"]//ul/li//div[text()=\""+ projectName +"\"]//..//.."));
        return projectArchivedFound;
    }
    public Button findActiveProjectsByName(String projectName){
        Button projectActiveFound = new Button(By.xpath("//div[@aria-labelledby=\"active\"]//ul/li//div[text()=\""+ projectName +"\"]/../.."));
        return projectActiveFound;
    }
}
