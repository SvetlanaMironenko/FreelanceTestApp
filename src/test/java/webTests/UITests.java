package webTests;

import common.BaseTest;
import api.freelance.ApiAuthController;
import api.freelance.entities.RegisterUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.logger.Logger;
import web.freelance.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class UITests extends BaseTest {

    @Test
    public void loginTest() {
        Logger.step("1. Prepare account for login by API");
        RegisterUser newRegisterUser = new RegisterUser()
                .setUsername("Sveta" + randomPrefixName())
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD);
        ApiAuthController postController = new ApiAuthController();
        postController.registerUser(newRegisterUser);

        Logger.step("2. Open page Home");
        open("https://freelance.lsrv.in.ua");
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getTitleText(),
                "Welcome to Freelance test app",
                "Wrong page title is displayed");

        Logger.step("3. Open page Login");
        homePage.clickLinkLogin();
        webdriver().shouldHave(url("https://freelance.lsrv.in.ua/login"));

        Logger.step("4. Check and fill 'login' and 'password'");
        LoginPage loginPage = new LoginPage();
        loginPage.checkLoginAndPasswordInputsIsRed()
                .checkLoginButtonIsDisabled()
                .fillCredentials(newRegisterUser.getUsername(), newRegisterUser.getPassword())
                .clickLoginButton();
        webdriver().shouldHave(url("https://freelance.lsrv.in.ua/main"));
        MainUserPage mainUserPage = new MainUserPage();
        mainUserPage.checkProfileVisible();
        mainUserPage.logOut();
    }

    @Test
    public void registerAccountTest() {
        Logger.step("1. Open page Home");
        open("https://freelance.lsrv.in.ua/home");
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getTitleText(),
                "Welcome to Freelance test app",
                "Wrong page title is displayed");
        Logger.step("2. Click Create Account");
        homePage.clickCreateAccount();

        Logger.step("3. Open registration page");
        RegisterPage registerPage = new RegisterPage();
        Assert.assertEquals(registerPage.getTitleText(),
                "Register",
                "Wrong page title is displayed");
        registerPage.checkElementsInputsIsRed()
                .checkRegisterButtonIsDisabled()
                .fillCredentials("Sveta" + randomPrefixName(), "Sveta2", "Mironenko", "12345678", "12345678")
                .clickRegisterButton();

        LoginPage loginPage = new LoginPage();
        Logger.wait(1, "");
        webdriver().shouldHave(url("https://freelance.lsrv.in.ua/login"));
        Assert.assertEquals(loginPage.getLoginTitle(),
                "Login",
                "Wrong page title is displayed");
    }

    @Test
    public void userCanEditProfileTest() {
        Logger.step("1. Prepare (register) user and login by API");
        RegisterUser newRegisteredUser = registerRandomUserByApi();

        Logger.step("2. Open page Login and fill 'login' and 'password'");
        open("https://freelance.lsrv.in.ua/login");
        LoginPage loginPage = new LoginPage();
        loginPage.fillCredentials(newRegisteredUser.getUsername(), newRegisteredUser.getPassword())
                .clickLoginButton();
        webdriver().shouldHave(url("https://freelance.lsrv.in.ua/main"));

        Logger.step("3. Go to your profile");
        MainUserPage mainUserPage = new MainUserPage();
        mainUserPage.goToProfile();

        ProfilePage profilePage = new ProfilePage();
        Logger.step("4. Open Profile");
        Assert.assertEquals(profilePage.getTitleTextProfile(),
                "Profile",
                "Wrong page title is displayed");

        Assert.assertEquals(profilePage.getUserLoginText(),
                newRegisteredUser.getUsername(),
                "Wrong userLogin is displayed");

        EditUserProfilePopUpPage editUserProfilePopUpPage = profilePage.clickEditInfoButton();
        editUserProfilePopUpPage.fillValues("Sveta", "Odesa")
                .clickUpdateButton();

        Assert.assertEquals(profilePage.getFullNameText(),
                "Sveta Odesa",
                "Wrong fullName is displayed");

        mainUserPage.logOut();
    }

    @Test
    public void userCreateJobAtProfileTest() {
        Logger.step("1. Prepare (register) user and login by API");
        RegisterUser newRegisteredUser = registerRandomUserByApi();

        Logger.step("2. Open page Login and fill 'login' and 'password'");
        open("https://freelance.lsrv.in.ua/login");
        LoginPage loginPage = new LoginPage();
        loginPage.fillCredentials(newRegisteredUser.getUsername(), newRegisteredUser.getPassword())
                .clickLoginButton();
        webdriver().shouldHave(url("https://freelance.lsrv.in.ua/main"));

        Logger.step("3. Go to your profile");
        MainUserPage mainUserPage = new MainUserPage();
        mainUserPage.goToProfile();

        ProfilePage profilePage = new ProfilePage();
        Logger.step("4. Open Profile");
        Assert.assertEquals(profilePage.getTitleTextProfile(),
                "Profile",
                "Wrong page title is displayed");
        profilePage.clickAddJobButton();

        Assert.assertEquals(profilePage.getTitleCreateJob(),
                "Create Job",
                "Wrong page title is displayed");

        Logger.step("5. Fill Message to Job");
        profilePage.fillJobMessage("My information",
                "Hello, I study foreign languages English and Spanish. I would be happy to communicate in these languages.",
                "10")
                .clickCreateJobButton();
        Logger.step("6. Check 1 jobs");
        Assert.assertEquals(profilePage.getHaveOneJobsTitle(),
                "You have 1 jobs",
                "Wrong page title is displayed");

        mainUserPage.logOut();
    }

    @Test
    public void userAddMessageToJobTest() {
        Logger.step("1. Prepare (register) user and login by API");
        RegisterUser newRegisteredUser = registerRandomUserByApi();

        Logger.step("2. Open page Login and fill 'login' and 'password'");
        open("https://freelance.lsrv.in.ua/login");
        LoginPage loginPage = new LoginPage();
        loginPage.fillCredentials(newRegisteredUser.getUsername(), newRegisteredUser.getPassword())
                .clickLoginButton();
        webdriver().shouldHave(url("https://freelance.lsrv.in.ua/main"));

        Logger.step("3. Go to write a message");
        MainUserPage mainUserPage = new MainUserPage();
        mainUserPage.clickViewInfoButton()
                .fillCredentials("Hello")
                .clickLeaveACommentButton()
                .clickCloseJobDetailsButton();
        Assert.assertEquals(mainUserPage.getCommentsInMyMessage(),
                "Comments: 1",
                "Wrong page title is displayed");

        mainUserPage.logOut();
    }

    @Test
    public void userOpenProfileAndDeleteJobTest() {
        Logger.step("1. Prepare (register) user and login by API");
        RegisterUser newRegisteredUser = registerRandomUserByApi();

        Logger.step("2. Open page Login and fill 'login' and 'password'");
        open("https://freelance.lsrv.in.ua/login");
        LoginPage loginPage = new LoginPage();
        loginPage.fillCredentials(newRegisteredUser.getUsername(), newRegisteredUser.getPassword())
                .clickLoginButton();
        webdriver().shouldHave(url("https://freelance.lsrv.in.ua/main"));

        Logger.step("3. Open Profile");
        MainUserPage mainUserPage = new MainUserPage();
        ProfilePage profilePage = mainUserPage.goToProfile();

        Logger.step("4. Fill Message to Job");
        profilePage.clickAddJobButton();
        profilePage.fillJobMessage("My information",
                        "Hello, I study foreign languages English and Spanish. I would be happy to communicate in these languages.",
                        "10")
                .clickCreateJobButton();

        Logger.step("5. Remove my message on page Profile");
        profilePage.clickRemoveMessageButton();

        mainUserPage.logOut();
    }

}
