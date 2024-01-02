package stepDefinitions;

import Utils.dependedtMethods;
import configuration.BaseClass;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import listeners.TestAllureListener;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import pages.checkoutPage;
import pages.landingPage;


import java.util.ArrayList;
import java.util.List;


@Listeners({TestAllureListener.class})
public class landingPageStepDef extends BaseClass{

    dependedtMethods dependentMethods;
    public landingPageStepDef(dependedtMethods dependentMethods ){

        this.dependentMethods=dependentMethods;
    }

    landingPage landingPage;


    @Given("User is on GreenCart Landing page")
    public void user_is_on_GreenCart_Landingpage() throws InterruptedException {



     /*   JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.open()");
*/


        landingPage =new landingPage();
        logger.info("User is on login page");
        String title= landingPage.getTitleLandingPage();
        System.out.println("title"+title);
        Assert.assertTrue(title.contains("GreenKart"));
        landingPage.validateElementDisplayed();
        Thread.sleep(2000);
        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
        driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);
        Thread.sleep(5000);

    }
    @Given("User is on sample Landing page")
    public void user_is_on_sample_Landingpage() throws InterruptedException {

    System.out.println("Hello welcome");
    driver.findElement(By.xpath("//button[contains(text(),'Accept All Cookies')]")).click();
    //driver.findElement(By.xpath("//label[@for='edit-eu-eligibility-1']/preceding-sibling::span[2]")).click();
       Thread.sleep(2000);
//        WebElement myelement = driver.findElement(By.xpath("//label[@for='edit-eu-eligibility-1']/preceding-sibling::span[2]"));
//        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
//        jse2.executeScript("arguments[0].click()", myelement);

    }

    @When("user searched with Shortname {string} and extracted actual name of product")
    public void userSearchWithName(String item) throws InterruptedException {

        System.out.println("User validated the login page");
        landingPage.searchItem(item);
        Thread.sleep(2000);
        dependentMethods.landingPageProduct= landingPage.productNames();
        System.out.println(dependentMethods.landingPageProduct);
        logger.info("Extracted actual name of products successfully");

        for (String product : dependentMethods.landingPageProduct) {
            Assert.assertTrue(product.contains(item));
        }

    }


    @Then("Added {string} items of the selected product to cart")
    public void addedItemsOfTheSelectedProductToCart(String quantity) {
        landingPage.incrementQuantity(Integer.parseInt(quantity));
        landingPage.addToCart();
    }
    @Then("validate the Error message")
    public void validateError() throws InterruptedException {
        WebElement t =  driver.findElement(By.id("edit-first-name"));
        t.click();
        WebElement t1 =  driver.findElement(By.id("edit-last-name"));
        t1.click();
        WebElement errorF= driver.findElement(By.xpath("//div[@class='form-group-inner']/div[@class='invalid-feedback']"));
        String act = errorF.getText();
        Thread.sleep(3000);
        System.out.println(act);

        String exp = "Please enter your first name";
        //identify actual error message

        //verify error message with Assertion
        Assert.assertEquals(act, exp);

    }
    @Then("Close the webdriver")
    public void closeTheWebdriver() {

        driver.quit();
        logger.info("Webdriver Closed Successfully");
    }



}
