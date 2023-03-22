package screens;

import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MovieListScreen extends BaseScreen{
    private static final Logger LOGGER = LogManager.getLogger(MovieListScreen.class);
    private Properties locators;

    public MovieListScreen(DriverManager driverManager) throws IOException {
        super(driverManager);
        locators = new Properties();
        locators.load(new FileInputStream("src/test/resources/movielistscreenlocators.properties"));

    }
    public MovieScreen goToMovie(int idx) throws IOException {
        List <WebElement> titleElements = driverManager.getElementsByID((locators.get("LIST_TITLES_ID").toString()));
        String title = driverManager.getTexOfElement(titleElements.get(idx));
        driverManager.tapOnElement(titleElements.get(idx));
        LOGGER.info("Going to screen of movie '"+title +"'");
        return new MovieScreen(driverManager);
    }
    public String getMovieTitleFromList(int idx){
        List <WebElement> titleElements = driverManager.getElementsByID((locators.get("LIST_TITLES_ID").toString()));
        String title = driverManager.getTexOfElement(titleElements.get(idx));
        LOGGER.info("The title of the movie #"+(idx+1)+" is '" + title +"'");
        return title;
    }
    public void deleteAllMoviesFromWatchList(){
        LOGGER.info("Deleting all movies from WatchList\n");
        int size  = driverManager.getElementsByID((locators.get("LIST_RIBBON_ID").toString())).size();
        for (int i=0; i<size; i++){
            List <WebElement> ribbonElements = driverManager.getElementsByID((locators.get("LIST_RIBBON_ID").toString()));
            driverManager.tapOnElement(ribbonElements.get(i));
        }
    }

}
