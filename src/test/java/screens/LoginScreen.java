package screens;

import classes.User;
import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginScreen extends BaseScreen{

    private static final Logger LOGGER = LogManager.getLogger(LoginScreen.class);
    private Properties locators;

    public LoginScreen(DriverManager driverManager) throws IOException {
        super(driverManager);
        locators = new Properties();
        locators.load(new FileInputStream("src/test/resources/loginscreenlocators.properties"));
    }

    public BaseScreen skipSignIn() throws IOException {
        LOGGER.info("Taping on 'Skip sign in' button");
        WebElement notNowButton = driverManager.getElementByID(locators.get("NOT_NOW_BUTTON_ID").toString());
        driverManager.tapOnElement(notNowButton);
        return new BaseScreen(driverManager);
    }
    public LoginScreen signInWIMDB(){
        LOGGER.info("Taping on 'Sign in with IMDB account'");
        WebElement withIMDBButton = driverManager.getElementByID(locators.get("WIMDB_BUTTON_ID").toString());
        driverManager.tapOnElement(withIMDBButton);
        return this;
    }
    public BaseScreen setCredentials(User anUser) throws IOException {
        LOGGER.info("Typing credential for " + anUser.getName());
        WebElement emailField = driverManager.getElementByXpath(locators.get("EMAIL_FIELD_XPATH").toString());
        driverManager.typeOnElement(emailField, anUser.getEmail());
        WebElement passwordField = driverManager.getElementByXpath(locators.get("PASSWORD_FIELD_XPATH").toString());
        driverManager.typeOnElement(passwordField, anUser.getPassword());
        WebElement signInButton = driverManager.getElementByXpath(locators.get("SIGNIN_BUTTON_XPATH").toString());
        driverManager.tapOnElement(signInButton);
        return new BaseScreen(driverManager);
    }
}
