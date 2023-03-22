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
import screens.BaseScreen;
import screens.LoginScreen;
import screens.MovieScreen;
import java.io.IOException;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Listeners(TestNGListener.class)
public class TestRunner extends Hooks{
    private static final Logger LOGGER = LogManager.getLogger("TestRunner");
    @Test
    public void searchForAMovie() throws IOException{
        Movie aMovie = Helper.parseMovieYAML("testdata/moviedetails.yml");
        MovieScreen aMovieScreen = new  LoginScreen(driverManager).
                                        skipSignIn().
                                        openSearchScreen().
                                        tapOnSearchBar().
                                        typeInSearchBar(aMovie.getTitle()).
                                        selectFirstSuggestion();
        assertThat("Wrong title displayed",
                aMovieScreen.getTitle(),
                equalTo(aMovie.getTitle())
        );
        assertThat("Wrong overview displayed",
                aMovieScreen.getOverview(),
                equalTo(aMovie.getOverview())
        );
    }
    @Test(groups = {"WatchList"})
    public void addMovieToWatchLater() throws IOException {
        User anUser = Helper.parseUserYAML("testdata/usercredentials.yml");
        BagOfWords words = Helper.parseBagOfWordsYAML("testdata/bagofwords.yml");
        String movieTitleAdded =  new LoginScreen(driverManager).
                                        signInWIMDB().
                                        setCredentials(anUser).
                                        openSearchScreen().
                                        tapOnSearchBar().
                                        setMoviesOnlyFilter().
                                        typeInSearchBar(words.getRandomWord()).
                                        seeFilteredResults().
                                        selectRandomResult().
                                        addToWatchList();
        String movieTitleFromWatchList = new BaseScreen(driverManager).
                                            openYouScreen().
                                            openWatchList().
                                            getMovieTitleFromList(0);
        assertThat("Movie Titles don't match",
                    movieTitleFromWatchList,
                    equalTo(movieTitleAdded));

    }
    @Test(groups = {"Rating"})
    public void rateMovie() throws IOException, InterruptedException {
        User anUser = Helper.parseUserYAML("testdata/usercredentials.yml");
        BagOfWords words = Helper.parseBagOfWordsYAML("testdata/bagofwords.yml");
        boolean hasRatedMessage =  new LoginScreen(driverManager).
                                        signInWIMDB().
                                        setCredentials(anUser).
                                        openSearchScreen().
                                        tapOnSearchBar().
                                        setMoviesOnlyFilter().
                                        typeInSearchBar(words.getRandomWord()).
                                        seeFilteredResults().
                                        selectFirstResult().
                                        scrollToRateMovie().
                                        setStars(new Random().nextInt(10) + 1).
                                        sentRating().
                                        savedMessagePopped();
        assertThat("'Saved message' didn't appear",
                hasRatedMessage);
    }
}
