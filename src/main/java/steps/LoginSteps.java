package steps;

import pages.LoginPage;

public class LoginSteps {
    private LoginPage loginPage = new LoginPage();

    public void typeEmail(String email) {
        loginPage.getEmailTextField().sendKeys(email);
    }

    public void typePassword(String password) {
        loginPage.getPasswordTextField().sendKeys(password);
    }

    public void submit() {
        loginPage.getSubmitButton().click();
    }

}
