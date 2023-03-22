package drivermanager;

import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DriverManager {
    int TIMEOUT = 30;
    WebElement getElementByID(String elementID);
    WebElement getElementByXpath(String elementXpath);
    WebElement getElementByAccID(String elementAccID);
    List<WebElement> getElementsByID(String elementID);
    WebElement scrollToGetElementByID(String elementID);
    void tapOnElement(WebElement element);
    void tapOnElement(List<WebElement> elements, int index);
    void typeOnElement(WebElement element, String input);
    String getTexOfElement(WebElement element);
    String getPageSource();
    File takeScreenShot(String name) throws IOException;
    void quitDriver();
}
