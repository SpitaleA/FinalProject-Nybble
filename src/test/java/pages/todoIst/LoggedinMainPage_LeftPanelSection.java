package pages.todoIst;

import controlSelenium.Button;
import controlSelenium.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class LoggedinMainPage_LeftPanelSection {
    public List<Label> listaProyectos= new ArrayList<>();
    public Button newProjectButton = new Button(By.xpath("//button[contains(@aria-label,\"adir proyecto\")]"),"New project button");
    public Button editProject = new Button(By.xpath("//li[contains(@role,\"menuitem\")]//div[text()=\"Editar proyecto\"]"),
            "Edit project option button");
    public Button deleteProject = new Button(By.xpath("//div[text()=\"Eliminar proyecto\"]"),"Delete project option button");
    public  Label projectList = new Label(By.xpath("//ul[@id=\"projects_list\"]/li"),"Projects list labels");

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
