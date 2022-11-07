package cleanTest.todoly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import singletonSession.Session;

import java.util.Date;

public class CRUDProjectTest extends TestBaseTodoLy{
    @Test
    public void CRUDProject() throws InterruptedException {
        String newProjectName = "new " + new Date().getTime();
        String newProjectNameEdit = "edit " + new Date().getTime();
        //LOGIN
        mainPage.loginButton.click();
        loginModal.login("agusto15@nybble.com","123456");

        //CREATE
        leftPanelSection.newProjectButton.click();
        leftPanelSection.inputProjectTextBox.setText(newProjectName);
        leftPanelSection.addButton.click();
        leftPanelSection.findLastProjectWithName(newProjectName).click();


        Assertions.assertEquals(newProjectName,
                leftPanelSection.findLastProjectWithName(newProjectName).getText(),
                "ERROR no se creo el proyecto");

        //EDIT
        leftPanelSection.findLastProjectWithName(newProjectName).waitClickable();
        leftPanelSection.findLastProjectWithName(newProjectName).click();
        leftPanelSection.findLastProjectWithNameOption(newProjectName).click();
        leftPanelSection.editProjectButton.click();
        leftPanelSection.inputProjectEditTextBox.setText(newProjectNameEdit);
        leftPanelSection.itemEditSumbitButton.click();

        Assertions.assertEquals(newProjectNameEdit,
                leftPanelSection.findLastProjectWithName(newProjectNameEdit).getText(),
                "ERROR no se le cambio el nombre al proyecto");

        //DELETE
        leftPanelSection.findLastProjectWithName(newProjectNameEdit).click();
        leftPanelSection.findLastProjectWithNameOption(newProjectNameEdit).click();
        leftPanelSection.deleteProjectButton.click();
        Session.getInstance().getBrowser().switchTo().alert().accept();
        // todo --> ver que demora un ratito el waitInvisibility
        menuSection.loadingIcon.waitInvisibility();
        Assertions.assertFalse(leftPanelSection.findLastProjectWithName(newProjectNameEdit).isControlDisplayed(),"ERROR no se borro el elemento");

        Thread.sleep(2000);

    }

}
