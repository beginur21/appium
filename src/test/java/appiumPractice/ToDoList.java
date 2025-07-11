package appiumPractice;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.MobileUtils;

public class ToDoList {
    @Test
    public void validateToDoList(){
        /*
1-Setup your automation
2-Run your automation and start finding the element
3-Click/Tap ok button from pop-up
4-Click Clear Button(X button).
5-Click ok button like French word
6-Click add button
7-Provide the name
8-Provide the IT Practice for task part
9-Check the box is displayed,enabled and not selected then click
10-Click ok button
11-Validate the your name text
12-Validate the your task text
13-Click clear button
14-CLick ok button
15-Validate the message "The list doesn't contain any element !" message
 */

        AndroidDriver driver = MobileUtils.getDriver("src/test/resources/app-debug.apk");

        WebElement okButton = driver.findElementByAndroidUIAutomator("text(\"OK\")");
        okButton.click();

        WebElement xButton = driver.findElementByXPath("//android.widget.ImageButton[@resource-id=\"fr.stevenfrancony.mytodolist:id/clearButton\"]");
        xButton.click();
        WebElement okFrenchClick = driver.findElementByAndroidUIAutomator("text(\"OUI\")");
        okFrenchClick.click();
        WebElement addButtonClick = driver.findElementByXPath("//android.widget.ImageButton[@resource-id=\"fr.stevenfrancony.mytodolist:id/addButton\"]");
        addButtonClick.click();

        WebElement name = driver.findElementByAndroidUIAutomator("text(\"Name...\")");
        name.sendKeys("Begimai");
        WebElement task = driver.findElementByAndroidUIAutomator("text(\"Task...\")");
        task.sendKeys("IT Practice");

        WebElement boxCheck = driver.findElementByClassName("android.widget.CheckBox");
        if (boxCheck.isDisplayed() && boxCheck.isEnabled() && !boxCheck.isSelected()){
            boxCheck.click();
        }
        WebElement ok = driver.findElementByAndroidUIAutomator("text(\"OK\")");
        ok.click();

        WebElement nameValidation = driver.findElementByAndroidUIAutomator("text(\"Begimai\")");
        Assert.assertTrue(nameValidation.isDisplayed());

        WebElement taskValidation = driver.findElementByXPath("//android.widget.TextView[@resource-id=\"fr.stevenfrancony.mytodolist:id/text\"]");
        Assert.assertTrue(taskValidation.isDisplayed());

        WebElement delete = driver.findElementByXPath("//android.widget.ImageButton[@resource-id=\"fr.stevenfrancony.mytodolist:id/clearButton\"]");
        delete.click();

        WebElement okButtonClick = driver.findElementByAndroidUIAutomator("text(\"OUI\")");
        okButtonClick.click();
        WebElement validateText = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"fr.stevenfrancony.mytodolist:id/list_status\"]"));
        Assert.assertTrue(validateText.isDisplayed());




    }
}
