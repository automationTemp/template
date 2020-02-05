package utility;

import automationFramework.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@SuppressWarnings("ALL")
public class Listener extends TestBase implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    // This belongs to ITestListener and will execute only on the event of fail test
    @Override
    public void onTestFailure(ITestResult arg0) {
        // This is calling the printTestResults method
        logger.info("I am in onTestFailure method " + getTestMethodName(arg0) + " failed");
        logger.info("Throwable: " + arg0.getThrowable());
        Object testClass = arg0.getInstance();
        driver = ((TestBase)testClass).getDriver();
        if (driver!=null){
            logger.info("Screenshot captured for test case:" + getTestMethodName(arg0));
            saveAllureScreenshot();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

}