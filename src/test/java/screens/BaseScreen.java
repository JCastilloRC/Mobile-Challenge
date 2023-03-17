package screens;

import drivermanager.DriverManager;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseScreen{
    protected String homeButtonID;
    protected String searchButtonID;
    protected DriverManager driverManager;
    public BaseScreen(DriverManager driverManager) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/basescreenlocators.properties"));
        this.driverManager = driverManager;
        homeButtonID = properties.get("HOME_BUTTON_ID").toString();
        searchButtonID = properties.get("SEARCH_BUTTON_ID").toString();
    }
    public SearchScreen openSearchScreen() throws IOException {
        driverManager.tapOnByID(searchButtonID);
        return new SearchScreen(driverManager);
    }

}
