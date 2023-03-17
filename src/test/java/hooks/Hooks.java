package hooks;

import drivermanager.DriverManager;
import drivermanager.DriverManagerFactory;
import drivermanager.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class Hooks {
    private static final Logger LOGGER = LogManager.getLogger("Hooks");
    protected DriverManager driverManager;
    @BeforeTest()
    public void setUp() throws IOException {
        driverManager = DriverManagerFactory.getManager(DriverType.ANDROID);
    }
    @AfterTest
    public void tearDown(){
        driverManager.quitDriver();
    }
}
