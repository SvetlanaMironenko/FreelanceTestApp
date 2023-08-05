package web.freelance;

import com.codeborne.selenide.SelenideElement;
import web.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage extends BasePage {

    private SelenideElement registerTitle = $x("//h2");
    private SelenideElement usernameInput = $x("//*[@formcontrolname='username']");
    private SelenideElement nameInput = $x("//*[@formcontrolname='name']");
    private SelenideElement lastnameInput = $x("//*[@formcontrolname='lastname']");
    private SelenideElement passwordInput = $x("//*[@formcontrolname='password']");
    private SelenideElement confirmPasswordInput = $x("//*[@formcontrolname='confirmPassword']");
    private SelenideElement registerButtonDisabled = $x("//button[contains(@class,'button-disabled')]");
    private SelenideElement registerButton = $x("//button");

    public String getTitleText() {
        return registerTitle.getText();
    }

    public RegisterPage checkElementsInputsIsRed() {
        SelenideElement[] elements = {usernameInput, nameInput, lastnameInput, passwordInput, confirmPasswordInput};
        for (SelenideElement curElement : elements) {
            curElement.click();
            curElement.getAttribute("aria-invalid");
        }
        registerTitle.click();
        confirmPasswordInput.getAttribute("aria-invalid");
        return this;
    }

    public RegisterPage clickRegisterButton() {
        registerButton.click();
        return this;
    }

    public RegisterPage checkRegisterButtonIsDisabled() {
        registerButtonDisabled.getAttribute("button-disabled");
        return this;
    }

    public RegisterPage fillCredentials(String username, String name, String lastname, String password, String confirmPassword) {
        setFieldValue(usernameInput, username);
        setFieldValue(nameInput, name);
        setFieldValue(lastnameInput, lastname);
        setFieldValue(passwordInput, password);
        setFieldValue(confirmPasswordInput, confirmPassword);
        return this;
    }

}
