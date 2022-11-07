package cleanTest.todoly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ChangeNameTest extends  TestBaseTodoLy{


    @Test
    public void verifyChangeName(){
        String newName="name"+new Date().getTime();
        mainPage.loginButton.click();
        loginModal.login("agusto15@nybble.com","123456");
        menuSection.settingsButton.click();
        settingsPage.nameTextBox.setText(newName);
        settingsPage.okButton.click();
        menuSection.settingsButton.click();

        //Validacion
        Assertions.assertEquals(newName,settingsPage.nameTextBox.getAttributeValue("value"),"ERROR no se edito el nombre");
    }
}
