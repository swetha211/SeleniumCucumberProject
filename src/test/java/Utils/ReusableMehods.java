package Utils;

import configuration.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class ReusableMehods extends BaseClass {


    JavascriptExecutor js=(JavascriptExecutor) driver;
    public void swithNewWindow(){

        // Get the handle of the current window
        String currentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandler=driver.getWindowHandles();
        windowHandler.remove(currentWindowHandle);
        driver.switchTo().window(windowHandler.iterator().next());

    }
    public void clickElementUsingJavaScript(WebElement element){

        js.executeScript("arguments[0].click();", element);
        System.out.println("element clicked successfully usin js");
    }
    public void sendkeysUsingJS(WebElement element,String value){

        js.executeScript("arguments[0].value='"+value+"';",element);
        System.out.println("element send successfully using js");

    }
}
