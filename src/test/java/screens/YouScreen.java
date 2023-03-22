package screens;

import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class YouScreen extends BaseScreen{
    private static final Logger LOGGER = LogManager.getLogger(YouScreen.class);
    private Properties locators;
    public YouScreen(DriverManager driverManager) throws IOException {
        super(driverManager);
        locators = new Properties();
        locators.load(new FileInputStream("src/test/resources/youscreenlocators.properties"));
    }
    public MovieListScreen openWatchList() throws IOException {
        LOGGER.info("Opening watch list...");
        WebElement seeAllWatchListElement = driverManager.getElementByAccID(locators.get("SEE_ALL_WATCH_LIST_ACCID").toString());
        driverManager.tapOnElement(seeAllWatchListElement);
        return new MovieListScreen(driverManager);
    }
    public MovieListScreen openMyRatingsList() throws IOException {
        LOGGER.info("Opening ratings list...");
        WebElement seeAllWatchListElement = driverManager.getElementByXpath(locators.get("MY_RATINGS_XPATH").toString());
        driverManager.tapOnElement(seeAllWatchListElement);
        return new MovieListScreen(driverManager);
    }

}
