package pageForFreelanceProject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private SelenideElement titleTextElement = $x("//h4");

    public void getTitleText() {
        titleTextElement.getText();
    }

}
