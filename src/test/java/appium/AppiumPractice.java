package appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.MobileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppiumPractice {

    @Test
    public void validateAlarmOptions() throws MalformedURLException {
//        File apk = new File("src/test/resources/ApiDemos-debug-newVersion.apk");
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("deviceName", "sdk_gphone64_arm64");
//        desiredCapabilities.setCapability("platformName", "Android");
//        desiredCapabilities.setCapability("app", apk.getAbsolutePath());
//
//        URL appiumServerUrl = new URL("http:/0.0.0.0:4723/wd/hub");
//        AndroidDriver driver = new AndroidDriver(appiumServerUrl, desiredCapabilities);
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AndroidDriver driver = MobileUtils.getDriver("src/test/resources/ApiDemos-debug-newVersion.apk");

        WebElement appButton = driver.findElementByAccessibilityId("App");
        appButton.click();

        WebElement alarmButton = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Alarm\"]"));
        alarmButton.click();

        WebElement alarmControllerButton = driver.findElementByAccessibilityId("Alarm Controller");
        alarmControllerButton.click();

        List<WebElement> alarms = driver.findElements(By.className("android.widget.Button"));
        List<String> expectedOptions = Arrays.asList("One Shot Alarm","Start Repeating Alarm","Stop Repeating Alarm");
        for (int i = 0; i < alarms.size(); i++) {
            Assert.assertEquals(alarms.get(i).getText(),expectedOptions.get(i));
        }
        driver.quit();
    }

    @Test
    public void validateAlarmOptionsWithAndroidUIAutomator() throws MalformedURLException {
        File apk = new File("src/test/resources/ApiDemos-debug-newVersion.apk");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "sdk_gphone64_arm64");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("app", apk.getAbsolutePath());

        URL appiumServerUrl = new URL("http:/0.0.0.0:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(appiumServerUrl, desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement appButton = driver.findElementByAndroidUIAutomator("text(\"App\")");
        appButton.click();

        WebElement alarmButton = driver.findElementByAndroidUIAutomator("text(\"Alarm\")");
        alarmButton.click();

        WebElement alarmControllerButton = driver.findElementByAndroidUIAutomator("text(\"Alarm Controller\")");
        alarmControllerButton.click();

        List<WebElement> alarms = driver.findElements(By.className("android.widget.Button"));
        List<String> expectedOptions = Arrays.asList("One Shot Alarm","Start Repeating Alarm","Stop Repeating Alarm");
        for (int i = 0; i < alarms.size(); i++) {
            Assert.assertEquals(alarms.get(i).getText(),expectedOptions.get(i));
        }
        driver.quit();
    }
}
