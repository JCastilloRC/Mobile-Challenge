package screens;

import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RatingScreen extends BaseScreen{
    private static final Logger LOGGER = LogManager.getLogger(RatingScreen.class);
    private Properties locators;
    public RatingScreen(DriverManager driverManager) throws IOException {
        super(driverManager);
        locators = new Properties();
        locators.load(new FileInputStream("src/test/resources/ratingscreenlocators.properties"));
    }
    public RatingScreen setStars(int star){
        LOGGER.info("Setting up "+star+" stars");
        WebElement starElement = driverManager.getElementByID(locators.get("STAR_ID").toString()+star);
        driverManager.tapOnElement(starElement);
        return this;
    }
    public RatingScreen sentRating(){
        LOGGER.info("taping on 'Rate' button...");
        WebElement rateElement = driverManager.getElementByID(locators.get("RATE_BUTTON_ID").toString());
        driverManager.tapOnElement(rateElement);
        return this;
    }
    public boolean savedMessagePopped(){
        try {
            driverManager.getElementByXpath(locators.get("RATING_SAVED_XPATH").toString());
            return true;
        }
        catch(TimeoutException e){
            return false;
        }
    }
    public void deleteRating(){
        LOGGER.info("Removing rating from movie...\n");
        WebElement removeElement = driverManager.getElementByXpath(locators.get("REMOVE_RATING_XPATH").toString());
        driverManager.tapOnElement(removeElement);
    }
}
