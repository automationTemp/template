package steps;

import pages.MainPage;

import static automationFramework.TestBase.softAssert;

public class MainSteps {
    private MainPage mainPage = new MainPage();

    public void goToSingIn() {
        mainPage.getSingInButton().click();
    }

    public void checkAccountName(String name) {
        mainPage.getAccountNameLabel().getText();
        softAssert.assertEquals(mainPage.getAccountNameLabel().getText(), name);
    }

    public void logOut() {
        mainPage.getLogOutButton().click();
    }

}