package web.freelance;

import com.codeborne.selenide.SelenideElement;
import web.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    private SelenideElement loginInput = $x("//*[@formcontrolname='username']");
    private SelenideElement loginInvalidInput = $x("//*[@formcontrolname='username'][@aria-invalid='true']");
    private SelenideElement passwordInput = $x("//*[@formcontrolname='password']");
    private SelenideElement passwordInvalidInput = $x("//*[@formcontrolname='password'][@aria-invalid='true']");
    private SelenideElement buttonLoginDisabled = $x("//button[contains(@class,'button-disabled')]");
    private SelenideElement buttonLogin = $x("//*[@class='mat-button-wrapper']");
    private SelenideElement loginTitle = $x("//h2");

    public LoginPage checkLoginAndPasswordInputsIsRed() {
        loginInput.click();
        passwordInput.click();
        loginInput.click();
        loginInvalidInput.getAttribute("aria-invalid");
        passwordInvalidInput.getAttribute("aria-invalid");
        return this;
    }

    public LoginPage checkLoginButtonIsDisabled() {
        buttonLoginDisabled.getAttribute("button-disabled");
        return this;
    }

    public LoginPage clickLoginButton() {
        buttonLogin.click();
        return this;
    }

    public LoginPage fillCredentials(String username, String password) {
        setFieldValue(loginInput, username);
        setFieldValue(passwordInput, password);
        return this;
    }

    public String getLoginTitle() {
        return loginTitle.getText();
    }

}
