package fieldDecorator.impl;


import fieldDecorator.elements.Button;
import org.openqa.selenium.WebElement;

class ButtonImpl extends AbstractElement implements Button {

    protected ButtonImpl(final WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public String getTextValue() {
        return wrappedElement.getAttribute("textContent").replaceAll("\u00a0", "");
    }
}
