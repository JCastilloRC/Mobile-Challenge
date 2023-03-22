package screens;

import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseScreen{
    private static final Logger LOGGER = LogManager.getLogger(BaseScreen.class);
    private Properties locators;
    protected DriverManager driverManager;
    public BaseScreen(DriverManager driverManager) throws IOException {
        locators = new Properties();
        locators.load(new FileInputStream("src/test/resources/basescreenlocators.properties"));
        this.driverManager = driverManager;
    }
    public SearchScreen openSearchScreen() throws IOException {
        LOGGER.info("Taping on 'search' button...");
        WebElement searchButton = driverManager.getElementByID(locators.get("SEARCH_BUTTON_ID").toString());
        driverManager.tapOnElement(searchButton);
        return new SearchScreen(driverManager);
    }
    public YouScreen openYouScreen() throws IOException {
        LOGGER.info("Taping on 'You' button...");
        WebElement searchButton = driverManager.getElementByID(locators.get("YOU_BUTTON_ID").toString());
        driverManager.tapOnElement(searchButton);
        return new YouScreen(driverManager);
    }
    protected int getMaxTries(){
        return Integer.parseInt(locators.get("MAX_TRIES").toString());
    }
}
