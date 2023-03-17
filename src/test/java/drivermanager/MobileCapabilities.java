package drivermanager;


import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MobileCapabilities {
    private DesiredCapabilities capabilities;
    public MobileCapabilities(String capabilitiesPath) throws IOException {
        capabilities = new DesiredCapabilities();
        Properties properties= new Properties();
        properties.load(new FileInputStream(capabilitiesPath));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.get("DEVICE_NAME"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.get("PLATFORM_NAME"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.get("PLATFORM_VERSION"));
        capabilities.setCapability("appPackage", properties.get("APP_PACKAGE"));
        capabilities.setCapability("appActivity", properties.get("APP_ACTIVITY"));

    }
    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }
}
