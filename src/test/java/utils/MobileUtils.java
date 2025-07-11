package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MobileUtils {

    public static AndroidDriver getDriver(String apk) {
        URL appiumServerUrl = null;
        try {
            appiumServerUrl = new URL("http:/0.0.0.0:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        DesiredCapabilities dc = getCaps(apk);
        AndroidDriver driver = new AndroidDriver(appiumServerUrl, dc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

    }

    public static DesiredCapabilities getCaps(String apk) {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName", "sdk_gphone64_arm64");
        dc.setCapability("platformName", "Android");
        dc.setCapability("app", new File(apk).getAbsolutePath());
        return dc;
    }
    public static void tap (AndroidDriver driver, WebElement element){
        TouchAction touchAction = new TouchAction<>(driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();

    }
    public static void longPress(AndroidDriver driver,WebElement element){
        TouchAction touchAction=new TouchAction(driver);
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)).withDuration(Duration.ofSeconds(3))).perform();
    }

    public static WebElement scroll(AndroidDriver driver, String text){
       return driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))");
    }
}
