package screens;

import drivermanager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginScreen extends BaseScreen{

    private static final Logger LOGGER = LogManager.getLogger(LoginScreen.class);
    private String withIMDBButtonID;

    private String notNowButtonID;
    private String emailFieldXpath;
    private String passwordFieldXpath;
    private String signInButtonXpath;
    public LoginScreen(DriverManager driverManager) throws IOException {
        super(driverManager);
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/loginscreenlocators.properties"));
        withIMDBButtonID = properties.get("WIMDB_BUTTON_ID").toString();
        notNowButtonID = properties.get("NOT_NOW_BUTTON_ID").toString();
        emailFieldXpath = properties.get("EMAIL_FIELD_XPATH").toString();
        passwordFieldXpath = properties.get("PASSWORD_FIELD_XPATH").toString();
        signInButtonXpath = properties.get("SIGNIN_BUTTON_XPATH").toString();
    }

    public BaseScreen skipSignIn() throws IOException {
        driverManager.tapOnByID(notNowButtonID);
        return new BaseScreen(driverManager);
    }
    public BaseScreen setCredentials(String email, String password) throws IOException {
        LOGGER.info(driverManager.getPageSource());
        driverManager.tapOnByID(withIMDBButtonID);
        return new BaseScreen(driverManager);
    }
}
