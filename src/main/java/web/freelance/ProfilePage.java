package web.freelance;

import com.codeborne.selenide.SelenideElement;
import web.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage extends BasePage {

    private SelenideElement titleProfile = $x("//h1");
    private SelenideElement buttonAddJob = $x("//*[@class='my-jobs ng-star-inserted']//*[@class='mat-button-wrapper']");
    private SelenideElement titleCreateJob = $x("//form/..//h2");
    private SelenideElement titleJobInput = $x("//*[@formcontrolname='title']");
    private SelenideElement descriptionJobInput = $x("//*[@formcontrolname='description']");
    private SelenideElement priceJobInput = $x("//*[@formcontrolname='price']");
    private SelenideElement createJobButton = $x("//*[contains(text(),'Create job')]");
    private SelenideElement haveOneJobsTitle = $x("//*[@class='my-jobs ng-star-inserted']//h2");
    private SelenideElement removeJobButton = $x("//*[text()='My information']//ancestor::mat-card//*[text()='delete']");
    private SelenideElement editInfoButton = $x("//*[contains(text(),'Edit Info')]");
    private SelenideElement fullName = $x("//div/h3");
    private SelenideElement userLogin = $x("//div/h2");

    public String getTitleTextProfile() {
        return titleProfile.getText();
    }

    public ProfilePage clickAddJobButton() {
        buttonAddJob.click();
        return this;
    }

    public String getTitleCreateJob() {
        return titleCreateJob.getText();
    }

    public ProfilePage fillJobMessage(String titleJob, String descriptionJob, String priceJob) {
        setFieldValue(titleJobInput, titleJob);
        setFieldValue(descriptionJobInput, descriptionJob);
        setFieldValue(priceJobInput, priceJob);
        return this;
    }

    public ProfilePage clickCreateJobButton() {
        createJobButton.click();
        return this;
    }

    public String getHaveOneJobsTitle() {
        return haveOneJobsTitle.getText();
    }

    public ProfilePage clickRemoveMessageButton() {
        removeJobButton.click();
        return this;
    }

    public EditUserProfilePopUpPage clickEditInfoButton() {
        editInfoButton.click();
        return new EditUserProfilePopUpPage();
    }

    public String getFullNameText() {
        return fullName.getText();
    }

    public String getUserLoginText() {
        return userLogin.getText();
    }

}
