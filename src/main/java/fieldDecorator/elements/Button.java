package fieldDecorator.elements;

import fieldDecorator.Element;

public interface Button extends Element {
    void click();

    String getTextValue();

    @Override
    boolean isDisplayed();

    @Override
    void moveToElement();
}
