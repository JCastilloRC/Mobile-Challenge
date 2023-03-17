package drivermanager;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AndroidDriverManager implements DriverManager {
    private AndroidDriver driver;
    private WebDriverWait wait;
    public AndroidDriverManager(DesiredCapabilities capabilities) throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);
        wait = new WebDriverWait(driver,Duration.ofSeconds(TIMEOUT));
    }

    @Override
    public WebElement getElementByID(String id) {
        return driver.findElement(By.id(id));
    }

    @Override
    public List<WebElement> getElementsById(String id) {
        return driver.findElements(By.id(id));
    }

    @Override
    public void tapOnByID(String elementID) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
        getElementByID(elementID).click();
    }

    @Override
    public void tapOnByID(String elementID, int index) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(elementID)));
        wait.until(ExpectedConditions.elementToBeClickable(getElementsById(elementID).get(index)));
        getElementsById(elementID).get(index).click();
    }

    @Override
    public void typeOnByID(String elementID, String input){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));
        getElementByID(elementID).sendKeys(input);
    }

    @Override
    public String getPageSource() {
        Set<String> contextNames = driver.getContextHandles();
        return driver.getPageSource();
    }

    @Override
    public void quitDriver() {
        driver.quit();
    }

}
