package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@class=\"login\"]")
    private WebElement singInButton;

    public WebElement getSingInButton() {
        return singInButton;
    }

    @FindBy(xpath = "//a[@class=\"account\"]")
    private WebElement accountNameLabel;

    public WebElement getAccountNameLabel() {
        return accountNameLabel;
    }

    @FindBy(xpath = "//a[@class=\"logout\"]")
    private WebElement logOutButton;

    public WebElement getLogOutButton() {
        return logOutButton;
    }
}
