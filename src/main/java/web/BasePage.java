package web;

import com.codeborne.selenide.SelenideElement;

import java.util.Random;

public class BasePage {

    protected void setFieldValue(SelenideElement curElement, String value) {
        curElement.clear();
        curElement.sendKeys(value);
    }

}
