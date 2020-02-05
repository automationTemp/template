package fieldDecorator.impl;

import automationFramework.TestBase;
import fieldDecorator.Element;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

abstract class AbstractElement extends TestBase implements Element {
    Actions actions = new Actions(driver);

    protected final WebElement wrappedElement;

    protected AbstractElement(final WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    @Override
    public boolean isDisplayed() {
        try {
            return wrappedElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void click() {
        actions.moveToElement(wrappedElement).click().build().perform();

    }

    public void moveToElement() {
        actions.moveToElement(wrappedElement).build().perform();
    }
}
