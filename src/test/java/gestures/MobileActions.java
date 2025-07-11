package gestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.MobileUtils;

import java.time.Duration;
import java.util.List;

public class MobileActions {
    @Test
    public void tabTest() {
        AndroidDriver driver = MobileUtils.getDriver("src/test/resources/ApiDemos-debug-newVersion.apk");
        WebElement contentButton = driver.findElementByAndroidUIAutomator("text(\"Content\")");
        MobileUtils.tap(driver, contentButton);

    }

    @Test
    public void longPressTest() {
        AndroidDriver driver = MobileUtils.getDriver("src/test/resources/ApiDemos-debug-newVersion.apk");
        WebElement viewsTap = driver.findElementByAndroidUIAutomator("text(\"Views\")");
        viewsTap.click();
        WebElement expandableListTap = driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
        expandableListTap.click();
        WebElement adapterTap = driver.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")");
        MobileUtils.tap(driver, adapterTap);
        List<WebElement> listItems = driver.findElementsByClassName("android.widget.TextView");

        for (WebElement name : listItems) {
            if (name.getText().contains("Names")) {
                System.out.println(name.getText());
            }
        }

        WebElement peopleNames = driver.findElementByXPath("//android.widget.TextView[@text=\"People Names\"]");

//        TouchAction touchAction=new TouchAction(driver);
//        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(peopleNames)).withDuration(Duration.ofSeconds(3))).perform();
//
        MobileUtils.longPress(driver, peopleNames);
        WebElement sampleMenu = driver.findElementByXPath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample menu\"]");

        Assert.assertTrue(sampleMenu.isDisplayed());
    }

    @Test
    public void scrollTest() {
        AndroidDriver driver = MobileUtils.getDriver("src/test/resources/ApiDemos-debug-newVersion.apk");
        WebElement viewsButton = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]"));
        viewsButton.click();

        MobileUtils.scroll(driver, "TextClock").click();


    }
}