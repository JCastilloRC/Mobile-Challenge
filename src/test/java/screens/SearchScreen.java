package screens;

import drivermanager.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SearchScreen extends BaseScreen{

    private String searchBarID;
    private String suggestionsID;
    public SearchScreen(DriverManager driverManager) throws IOException {
        super(driverManager);
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/searchscreenlocators.properties"));
        searchBarID = properties.get("SEARCH_BAR_ID").toString();
        suggestionsID = properties.get("SUGGESTIONS_ID").toString();
    }

    public SearchScreen typeInSearchBar(String input){
        driverManager.tapOnByID(searchBarID);
        driverManager.typeOnByID(searchBarID, input);
        return this;
    }

    public SearchScreen selectFirstResult(){
        driverManager.tapOnByID(suggestionsID, 0);
        return this;
    }
}
