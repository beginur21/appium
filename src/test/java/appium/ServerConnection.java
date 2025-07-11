package appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerConnection {


    @Test
    public void connectionTest() throws MalformedURLException {

        // We need to provide the location of the file
        File apk = new File("src/test/resources/ApiDemos-debug-newVersion.apk");//copy root
        //Set up our Desired Capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "sdk_gphone64_arm64");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("app", apk.getAbsolutePath());

        //PROVIDE APPIUM SERVER CONNECTION 0.0.0.0 -> means your ip address

        URL appiumServerUrl = new URL("http:/0.0.0.0:4723/wd/hub");
        // CREATE ANDROID DRIVER FROM THESE CAPABILITIES TO CONNECT THEM
        AndroidDriver driver = new AndroidDriver(appiumServerUrl, desiredCapabilities);

        AndroidElement viewsButton = (AndroidElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]"));
        viewsButton.click();

        MobileElement buttons = (MobileElement) driver.findElementByAccessibilityId("Buttons");
        buttons.click();

        WebElement normalText = driver.findElement(By.id("io.appium.android.apis:id/button_normal"));
        Assert.assertEquals(normalText.getText(),"Normal");
        driver.navigate().back();

        WebElement autoComplete = driver.findElementByAccessibilityId("Auto Complete");
        autoComplete.click();

        MobileElement screenTop = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Screen Top\"]"));
        screenTop.click();

        AndroidElement country = (AndroidElement) driver.findElement(By.className("android.widget.AutoCompleteTextView"));
        country.sendKeys("USA");

        WebElement validation = driver.findElementByAccessibilityId("Give me Focus");
        Assert.assertEquals(validation.getText(),"Give me Focus");

    }
}
