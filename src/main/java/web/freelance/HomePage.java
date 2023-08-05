package web.freelance;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private SelenideElement titleTextElement = $x("//h4");
    private SelenideElement linkLogin = $x("//a");
    private SelenideElement createAccount = $x("//*[@routerlink='/register']");

    public String getTitleText() {
        return titleTextElement.getText();
    }

    public LoginPage clickLinkLogin() {
        linkLogin.getAttribute("href");
        linkLogin.click();
        return new LoginPage();
    }

    public RegisterPage clickCreateAccount() {
        createAccount.click();
        return new RegisterPage();
    }

}
