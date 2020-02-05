package tests;

import automationFramework.TestBase;
import org.testng.annotations.Test;

public class TestMethod extends TestBase {

    @Test
    public void badTest() {
        app.loginPage().login();
        app.mainSteps().goToSingIn();
        app.loginSteps().typeEmail("a@ml.ru");
        app.loginSteps().typePassword("qwerty");
        app.loginSteps().submit();
        shutUp(2);
        app.mainSteps().checkAccountName("name");
        app.mainSteps().logOut();
        softAssert.assertAll();
    }

    @Test
    public void test() {
        app.loginPage().login();
        app.mainSteps().goToSingIn();
        app.loginSteps().typeEmail("a@ml.ru");
        app.loginSteps().typePassword("qwerty");
        app.loginSteps().submit();
        shutUp(2);
        app.mainSteps().checkAccountName("name name");
        app.mainSteps().logOut();
        softAssert.assertAll();
    }
}