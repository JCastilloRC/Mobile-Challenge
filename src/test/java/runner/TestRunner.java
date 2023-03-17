package runner;

import classes.BagOfWords;
import classes.Movie;
import classes.User;
import helper.Helper;
import helper.TestNGListener;
import hooks.Hooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import screens.LoginScreen;

import java.io.IOException;
@Listeners(TestNGListener.class)
public class TestRunner extends Hooks{
    private static final Logger LOGGER = LogManager.getLogger("TestRunner");
    @Test
    public void searchForAMovie() throws IOException, InterruptedException {
        Movie aMovie = Helper.parseMovieYAML("testdata/moviedetails.yml");
        new LoginScreen(driverManager).
                skipSignIn().
                openSearchScreen().
                typeInSearchBar(aMovie.getTitle()).
                selectFirstResult();
    }
}
