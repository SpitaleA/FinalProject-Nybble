package utils;

import org.openqa.selenium.WebElement;

public class ByUtils {

    public static String getXpath(WebElement ele) {
        String str = ele.toString();
        String[] listString = new String[0];
        if(str.contains("xpath"))
            listString = str.split("xpath:");
        else if(str.contains("id"))
            listString = str.split("id:");
        String last = listString[1].trim();
        return last.substring(0, last.length() - 1);
    }
}
