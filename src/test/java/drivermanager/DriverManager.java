package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public interface DriverManager {

    int TIMEOUT = 30;
    WebElement getElementByID(String id);
    List<WebElement> getElementsById(String id);
    void tapOnByID(String elementID);
    void tapOnByID(String elementID, int index);
    void typeOnByID(String elementID, String input);
    String getPageSource();
    void quitDriver();


}
