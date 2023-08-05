package common;

import api.freelance.ApiAuthController;
import api.freelance.entities.RegisterUser;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import common.listeners.CustomExtentReportListener;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.util.Random;
@Listeners({CustomExtentReportListener.class})
public class BaseTest {

    protected static String PASSWORD = "MyPass1234!";

    @BeforeTest
    public void beforeTest() {
        Selenide.open("https://freelance.lsrv.in.ua/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    protected int randomPrefixName() {
        Random random = new Random();
        int randomNumber = random.nextInt(8999) + 1000;
        return randomNumber;
    }

    public RegisterUser registerRandomUserByApi() {
        ApiAuthController apiAuthController = new ApiAuthController();
        RegisterUser newRegisterUser = getRandomRegisterUser();
        apiAuthController.registerUser(newRegisterUser);
        return newRegisterUser;
    }

    protected RegisterUser getRandomRegisterUser() {
        return new RegisterUser()
                .setUsername("Sveta" + randomPrefixName())
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD);
    }
}
