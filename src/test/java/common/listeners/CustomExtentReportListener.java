package common.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CustomExtentReportListener extends ExtentITestListenerClassAdapter {
    static {
        System.setProperty("extent.reporter.html.start", "true");
        System.setProperty("extent.reporter.html.out", "target/extentReport/ExtentHtml.html");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        ExtentTest test = ExtentTestManager.getTest(result);
        try {
            File file = getScreenShot(result);
            test.addScreenCaptureFromBase64String(Base64.getEncoder()
                    .encodeToString(FileUtils.readFileToByteArray(file)), "Failed test image");
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
        ExtentTest test = ExtentTestManager.getTest(result);
        try {
            File file = getScreenShot(result);
            test.addScreenCaptureFromBase64String(Base64.getEncoder()
                    .encodeToString(FileUtils.readFileToByteArray(file)), "Failed test image");
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getScreenShot(ITestResult iTestResult) {
        File fileForCopy = new File(iTestResult.getName() + ".jpg");
        File screenShotFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShotFile, fileForCopy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileForCopy;
    }

}
