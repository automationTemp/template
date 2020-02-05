package pages;

import automationFramework.TestBase;
import fieldDecorator.ExtendedFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import utility.Listener;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

@Listeners(Listener.class)
public class BasePage {
    protected WebDriver driver = TestBase.getDriver();

    static Actions actions;
    private Wait<WebDriver> wait = new WebDriverWait(driver, 40, 250);

    public BasePage() {
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public void waitUntilPageLoaded() {
        waitElementToBeClickable(By.xpath("//img[@class=\"img-responsive\"]"));
    }

    public void waitElementToBeClickable(By elementLocator) {
        wait.until(elementToBeClickable(elementLocator));
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(elementLocator));
        actions.build();
        actions.perform();
    }

}
