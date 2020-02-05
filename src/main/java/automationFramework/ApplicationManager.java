package automationFramework;

import pages.LoginPage;
import steps.LoginSteps;
import steps.MainSteps;

public class ApplicationManager {

    private LoginPage loginPage;
    private LoginSteps loginSteps;
    private MainSteps mainSteps;

    public void init() {
        loginPage = new LoginPage();
        loginSteps = new LoginSteps();
        mainSteps = new MainSteps();
    }

    public MainSteps mainSteps() {
        return mainSteps;
    }

    public LoginPage loginPage() {
        return loginPage;
    }

    public LoginSteps loginSteps() {
        return loginSteps;
    }
}
