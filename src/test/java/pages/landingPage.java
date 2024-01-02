package pages;

import Utils.ReusableMehods;
import configuration.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class landingPage extends BaseClass {

    ReusableMehods reUse=new ReusableMehods();
    WebDriverWait wait = new WebDriverWait(driver,30);
    By loginHeader=By.xpath("//div[@class='brand greenLogo']");
    By searchBox=By.xpath("//form[@class='search-form']/descendant::input[@type='search']");
    By searchButton=By.xpath("//button[@class='search-button']");
    By productNames=By.xpath("//div[@class='product']/div/following::h4");
    By incrementItem=By.cssSelector("a.increment");
    By addToCart = By.xpath("//div[@class='product-action']/button");

    @Step("Getting landing page title")
    public String getTitleLandingPage()
    {
        String title=driver.getTitle();
        return title;
    }
    @Step("Validate webElement is displayed")
    public void validateElementDisplayed(){

        driver.findElement(loginHeader).isDisplayed();
    }
    @Step("Search product in landing page")
    public void searchItem(String item) throws InterruptedException {

        driver.findElement(searchBox).click();
        Thread.sleep(2000);

        //reUse.sendkeysUsingJS(driver.findElement(searchBox),item);
        driver.findElement(searchBox).sendKeys(item);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        //driver.findElement(searchButton).click();
        //reUse.clickElementUsingJavaScript(driver.findElement(searchButton));
        //Thread.sleep(2000);


    }
    @Step("List the product names displayed in landing page")
    public List<String> productNames(){


        List<WebElement>list=driver.findElements(productNames);

        List<String>productName=new ArrayList<>();
        String product;
        for(int i=0;i<list.size();i++){

            product=list.get(i).getText().split("-")[0].trim();
            productName.add(product);

        }
        System.out.println(productName);
        return productName;

    }
    @Step("Increment the quantity of the item")
    public void incrementQuantity(int quantity){

        int i=quantity-1;
        while(i>0){
            driver.findElement(incrementItem).click();
            i--;

        }
    }
    @Step("Add the item to the cart")
    public void addToCart()
    {

        driver.findElement(addToCart).click();
    }


}
