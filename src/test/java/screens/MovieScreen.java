package screens;

import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import java.io.FileInputStream;
import java.io.IOException;
import org.openqa.selenium.NoSuchElementException;
import java.util.Properties;

public class MovieScreen extends BaseScreen{
    private static final Logger LOGGER = LogManager.getLogger(MovieScreen.class);
    private Properties locators;
    public MovieScreen(DriverManager driverManager) throws IOException {
        super(driverManager);
        locators = new Properties();
        locators.load(new FileInputStream("src/test/resources/moviescreenlocators.properties"));
    }
    public String getTitle(){
        String title;
        int count = 0;
        IllegalArgumentException EmptyTitleException = new IllegalArgumentException("Empty Title");
        while(true){
            try {
                WebElement titleElement = driverManager.getElementByID(locators.get("TITLE_ID").toString());
                title = driverManager.getTexOfElement(titleElement);
                if(title.equals("")) throw EmptyTitleException;
                LOGGER.info("The title of the movie is '" +title+"'");
                break;
            }
            catch(NoSuchElementException|IllegalArgumentException e){
                LOGGER.info(e.getMessage());
                LOGGER.info("Lets try one more time...");
                count++;
                if(count > super.getMaxTries()) throw e;
            }
        }
        return title;
    }
    public String getOverview(){
        String overview;
        WebElement overviewElement = driverManager.getElementByID(locators.get("OVERVIEW_ID").toString());
        overview = driverManager.getTexOfElement(overviewElement);
        LOGGER.info("The overview of the movie is '" +overview+"'");
        return overview;
    }
    public String addToWatchList(){
        String title = getTitle();
        LOGGER.info("Adding movie to watchlist...");
        WebElement addToWatchListElement = driverManager.getElementByID(locators.get("ADD_TO_WATCHLIST_BUTTON_ID").toString());
        driverManager.tapOnElement(addToWatchListElement);
        return title;
    }

    public RatingScreen fastRateMovie() throws IOException {
        LOGGER.info("Taping on my rating for this movie...");
        WebElement rateMovieElement = driverManager.scrollToGetElementByID(locators.get("RATE_THIS_ID").toString());
        driverManager.tapOnElement(rateMovieElement);
        return new RatingScreen(driverManager);
    }
    public RatingScreen scrollToRateMovie() throws IOException {
        LOGGER.info("Scrolling down to 'user reviews' to rate movie...");
        WebElement rateMovieElement = driverManager.scrollToGetElementByID(locators.get("USER_REVIEWS_RATING_ID").toString());
        driverManager.tapOnElement(rateMovieElement);
        return new RatingScreen(driverManager);
    }
}
