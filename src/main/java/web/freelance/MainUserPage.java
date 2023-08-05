package web.freelance;

import com.codeborne.selenide.SelenideElement;
import web.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainUserPage extends BasePage {

    private SelenideElement userProfileIco = $x("//*[@mattooltip='Profile']//*[contains(text(),'account')]");
    private SelenideElement menuItemProfile = $x("//*[@role='menuitem'][text()='Profile']");
    private SelenideElement menuItemLogOut = $x("//*[@role='menuitem'][text()='Logout']");
    private SelenideElement viewInfoButton = $x("//*[contains(text(),'My information')]/../../..//*[@class='mat-button-wrapper']");
    private SelenideElement leaveAComment = $x("//*[@formcontrolname='message']");
    private SelenideElement leaveACommentButton = $x("//*[@class='mat-card mat-focus-indicator job-card']//*[@class='mat-button-wrapper']");
    private SelenideElement closeJobDetailsButton = $x("//*[@class='job-page ng-star-inserted']//button[contains(@class,'mat-focus-indicator') and not(contains(@class, 'button-disabled'))]");
    private SelenideElement commentsText = $x("//*[contains(text(),'My information')]/../../..//*[@align='end']");

    public void checkProfileVisible() {
        userProfileIco.shouldBe(visible);
    }

    public ProfilePage goToProfile() {
        userProfileIco.click();
        menuItemProfile.click();
        return new ProfilePage();
    }

    public void logOut() {
        userProfileIco.click();
        menuItemLogOut.click();
    }

    public MainUserPage clickViewInfoButton() {
        viewInfoButton.click();
        return this;
    }

    public MainUserPage fillCredentials(String message) {
        setFieldValue(leaveAComment, message);
        return this;
    }

    public MainUserPage clickLeaveACommentButton() {
        leaveACommentButton.click();
        return this;
    }

    public MainUserPage clickCloseJobDetailsButton() {
        closeJobDetailsButton.click();
        return this;
    }

    public String getCommentsInMyMessage() {
        return commentsText.getText();
    }

}
