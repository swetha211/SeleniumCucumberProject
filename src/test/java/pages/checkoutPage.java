package pages;

import Utils.ReusableMehods;
import configuration.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class checkoutPage extends BaseClass {

    ReusableMehods reUse=new ReusableMehods();
    JavascriptExecutor js=(JavascriptExecutor)driver;
   By cartIcon = By.cssSelector("a[class='cart-icon']");
    By checkOutButton = By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]");
   By productItem = By.xpath("//p[@class='product-name']");
    By placeOrder = By.xpath("//button[contains(text(),'Place Order')]");

    @Step("checkout the items from cart bag")
    public void CheckoutItems() throws InterruptedException {

        Thread.sleep(1000);

        driver.findElement(cartIcon).click();
        Thread.sleep(2000);
       // driver.findElement(checkOutButton).click();
       // js.executeScript("arguments[0].click();", driver.findElement(checkOutButton));
        reUse.clickElementUsingJavaScript(driver.findElement(checkOutButton));
        Thread.sleep(2000);
        System.out.println("Clicked element");

    }

    @Step("Get product from checkout page")
    public List<String> ExtractProduct(){
   // public void ExtractProduct(){
      System.out.println("Inside getproduct");
       List<WebElement>list=driver.findElements(productItem);

        List<String>productName=new ArrayList<>();
        String product;
        System.out.println("ISize of getProduct"+list.size());
        for(int i=0;i<list.size();i++){

            product=list.get(i).getText().split("-")[0].trim();
            productName.add(product);

        }
        System.out.println(productName);
        return productName;

    }
    public Boolean VerifyPlaceOrderButton()
    {

        return driver.findElement(placeOrder).isDisplayed();
    }


}
