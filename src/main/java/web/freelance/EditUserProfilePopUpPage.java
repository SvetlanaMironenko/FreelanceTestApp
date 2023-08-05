package web.freelance;

import com.codeborne.selenide.SelenideElement;
import web.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class EditUserProfilePopUpPage extends BasePage {

    private SelenideElement nameInput = $x("//*[@formcontrolname='name']");
    private SelenideElement lastnameInput = $x("//*[@formcontrolname='lastname']");
    private SelenideElement updateButton = $x("//*[contains(text(),'Update')]");

    public EditUserProfilePopUpPage fillValues(String name, String lastname) {
        setFieldValue(nameInput, name);
        setFieldValue(lastnameInput, lastname);
        return this;
    }

    public EditUserProfilePopUpPage clickUpdateButton() {
        updateButton.click();
        return this;
    }


}
