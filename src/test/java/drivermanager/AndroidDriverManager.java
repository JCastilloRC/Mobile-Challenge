package drivermanager;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class AndroidDriverManager implements DriverManager {
    private AndroidDriver driver;
    private WebDriverWait wait;
    public AndroidDriverManager(DesiredCapabilities capabilities) throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);
        wait = new WebDriverWait(driver,Duration.ofSeconds(TIMEOUT));
    }
    @Override
    public WebElement getElementByID(String elementID) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(elementID)));
        return driver.findElement(By.id(elementID));
    }
    @Override
    public WebElement getElementByXpath(String elementXpath) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(elementXpath)));
        return driver.findElement(By.xpath(elementXpath));
    }
    @Override
    public WebElement getElementByAccID(String elementAccID) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(AppiumBy.accessibilityId(elementAccID)));
        return driver.findElement(AppiumBy.accessibilityId(elementAccID));
    }
    @Override
    public List<WebElement> getElementsByID(String elementID) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(elementID)));
        return driver.findElements(By.id(elementID));
    }
    @Override
    public WebElement scrollToGetElementByID(String elementID) {
        String locator = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceIdMatches(\".*"+elementID+".*\"));";
        return driver.findElement(AppiumBy.androidUIAutomator(locator));
    }
    @Override
    public void tapOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    @Override
    public void tapOnElement(List<WebElement> elements, int index) {
        wait.until(ExpectedConditions.elementToBeClickable(elements.get(index)));
        elements.get(index).click();
    }
    @Override
    public void typeOnElement(WebElement element, String input){
        element.sendKeys(input);
    }
    @Override
    public String getTexOfElement(WebElement element) {
        return element.getAttribute("text");
    }
    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }
    @Override
    public File takeScreenShot(String name) throws IOException {
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshots/"+name+".png"));
        return screenshot;
    }
    @Override
    public void quitDriver() {
        driver.quit();
    }
}
