package screens;

import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class SearchScreen extends BaseScreen{
    private static final Logger LOGGER = LogManager.getLogger(SearchScreen.class);
    private Properties locators;
    public SearchScreen(DriverManager driverManager) throws IOException {
        super(driverManager);
        locators = new Properties();
        locators.load(new FileInputStream("src/test/resources/searchscreenlocators.properties"));
    }

    public SearchScreen tapOnSearchBar(){
        LOGGER.info("Taping the search bar");
        WebElement searchBar = driverManager.getElementByID(locators.get("SEARCH_BAR_ID").toString());
        driverManager.tapOnElement(searchBar);
        return this;
    }
    public SearchScreen setMoviesOnlyFilter(){
        LOGGER.info("Filtering by only movies");
        WebElement advanceSearchButton = driverManager.getElementByXpath(locators.get("ADVANCED_SEARCH_XPATH").toString());
        driverManager.tapOnElement(advanceSearchButton);
        WebElement movieTag = driverManager.getElementByXpath(locators.get("MOVIE_TAG_XPATH").toString());
        driverManager.tapOnElement(movieTag);
        return this;
    }
    public SearchScreen typeInSearchBar(String input){
        LOGGER.info("Searching for '" + input +"' on the search bar");
        WebElement searchBar = driverManager.getElementByID(locators.get("SEARCH_BAR_ID").toString());
        driverManager.typeOnElement(searchBar, input);
        return this;
    }
    public SearchScreen seeFilteredResults(){
        LOGGER.info("Taping 'See results' button");
        WebElement seeResultsButton = driverManager.getElementByID(locators.get("SEE_RESULTS_BUTTON_ID").toString());
        driverManager.tapOnElement(seeResultsButton);
        return this;
    }
    public MovieScreen selectFirstSuggestion() throws IOException {
        LOGGER.info("Selecting the first suggestion");
        List <WebElement> suggestions = driverManager.getElementsByID(locators.get("SUGGESTIONS_ID").toString());
        driverManager.tapOnElement(suggestions, 0);
        return new MovieScreen(driverManager);
    }
    public MovieScreen selectFirstResult() throws IOException {
        LOGGER.info("Selecting the first result");
        List<WebElement> suggestions = driverManager.getElementsByID(locators.get("RESULTS_ID").toString());
        driverManager.tapOnElement(suggestions, 0);
        return new MovieScreen(driverManager);
    }
    public MovieScreen selectRandomResult() throws IOException {
        Random generator = new Random();
        List<WebElement> suggestions = driverManager.getElementsByID(locators.get("RESULTS_ID").toString());
        int randomIndex = generator.nextInt(suggestions.size());
        LOGGER.info("Selecting the result #"+(randomIndex+1));
        driverManager.tapOnElement(suggestions, randomIndex);
        return new MovieScreen(driverManager);
    }
}
