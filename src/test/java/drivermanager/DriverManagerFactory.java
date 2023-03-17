package drivermanager;
import java.io.IOException;


public class DriverManagerFactory{
    private static final String ANDROID_CAP_PATH = "src/test/resources/androidcap.properties" ;
    //private static final String IOS_CAP_PATH = "src/test/resources/IOScap.properties" ;
    public static DriverManager getManager(DriverType type) throws IOException {
        return switch (type) {
            case ANDROID -> new AndroidDriverManager(new MobileCapabilities(ANDROID_CAP_PATH).getCapabilities());
            //case IOS -> new IOSDriverManager(new MobileCapabilities(IOS_CAP_PATH).getCapabilities());
        };
    }
}
