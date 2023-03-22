package hooks;

import drivermanager.DriverManager;
import drivermanager.DriverManagerFactory;
import drivermanager.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.*;
import screens.BaseScreen;

import java.io.IOException;

public class Hooks{
    private static final Logger LOGGER = LogManager.getLogger("Hooks");
    protected DriverManager driverManager;
    public DriverManager getDriverManager() {
        return driverManager;
    }

    @BeforeMethod()
    public void setUp() throws IOException {
        driverManager = DriverManagerFactory.getManager(DriverType.EMU_ANDROID);
    }
    @AfterMethod(onlyForGroups = {"WatchList"})
    public void deleteMovieFromWatchList() throws IOException {
        try {
            new BaseScreen(driverManager).
                    openYouScreen().
                    openWatchList().
                    deleteAllMoviesFromWatchList();
        }catch (TimeoutException|NoSuchElementException e){
            LOGGER.info(e.getMessage());
        }
    }
    @AfterMethod(onlyForGroups = {"Rating"})
    public void deleteRating() throws IOException {
        try {
            new BaseScreen(driverManager).
                    openYouScreen().
                    openMyRatingsList().
                    goToMovie(0).
                    fastRateMovie().
                    deleteRating();
        }catch (TimeoutException|NoSuchElementException e){
            LOGGER.info(e.getMessage());
        }
    }
    @AfterMethod(alwaysRun=true)
    public void tearDown(){
        driverManager.quitDriver();
    }

}
