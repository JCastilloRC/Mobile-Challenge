package drivermanager;
import java.io.IOException;


public class DriverManagerFactory{
    private static final String EMU_ANDROID_CAP_PATH = "src/test/resources/emulatorandroidcap.properties" ;
    private static final String REAL_ANDROID_CAP_PATH = "src/test/resources/realandroidcap.properties" ;
    //private static final String IOS_CAP_PATH = "src/test/resources/IOScap.properties" ;
    public static DriverManager getManager(DriverType type) throws IOException {
        return switch (type) {
            case EMU_ANDROID -> new AndroidDriverManager(new MobileCapabilities(EMU_ANDROID_CAP_PATH).getCapabilities());
            case REAL_ANDROID ->  new AndroidDriverManager(new MobileCapabilities(REAL_ANDROID_CAP_PATH).getCapabilities());
            //case IOS -> new IOSDriverManager(new MobileCapabilities(IOS_CAP_PATH).getCapabilities());
        };
    }
}
