package helper;

import drivermanager.DriverManager;
import hooks.Hooks;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class TestNGListener implements ITestListener, ISuiteListener, IInvokedMethodListener {
    protected Logger LOGGER = LogManager.getLogger("TestRunner");

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("- - - - - - - > START TEST CASE: " + iTestResult.getName());
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info("Test successfully executed.");
        LOGGER.info("- - - - - - - > TEST CASE END \n");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult){
        try {
            takeScreenShot(iTestResult);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        LOGGER.error("Test Failed with message: " + iTestResult.getThrowable().getMessage());
        LOGGER.info("- - - - - - - > TEST CASE END \n");
    }
    @Attachment(value = "Failure in test", type = "image/png")
    private File takeScreenShot(ITestResult iTestResult) throws IOException{
        Object currentClass = iTestResult.getInstance();
        DriverManager driverManager = ((Hooks) currentClass).getDriverManager();
        return driverManager.takeScreenShot(iTestResult.getName()+"-"+iTestResult.getStatus());
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.info("Test run: " + iTestContext.getAllTestMethods().length +
                    ", Passed: " + iTestContext.getPassedTests().size() +
                    ", Failures: " + iTestContext.getFailedTests().size() +
                    ", Skipped: " + iTestContext.getSkippedTests().size());
    }
}