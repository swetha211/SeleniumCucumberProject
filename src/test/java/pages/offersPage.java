package pages;

import Utils.ReusableMehods;
import configuration.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class offersPage extends BaseClass {

    ReusableMehods reUse=new ReusableMehods();
    By offerPageLink= By.xpath("//a[text()='Top Deals']");
    By offersPageSearchBox=By.id("search-field");
    By tableElements=By.xpath("//table[@class='table table-bordered']/tbody/tr/td[1]");
    @Step("Switch to Offers page from landing page")
    public void switchToOfferPage() throws InterruptedException {

        driver.findElement(offerPageLink).click();

        Thread.sleep(2000);
        reUse.swithNewWindow();

    }
    @Step("Search Box in the Offers page is displayed")
    public void elementDisplayed(){
        driver.findElement(offersPageSearchBox).isDisplayed();
        logger.info("Webelement of offersPageSearchBox is displayed as expected");
    }
    @Step("Search product in offers page")
    public void searchItem(String item) throws InterruptedException {
        try {
            driver.findElement(offersPageSearchBox).click();
            logger.info("offersPage SearchBox is clicked successfully");
            driver.findElement(offersPageSearchBox).sendKeys(item);
        }
        catch (Exception e){

            logger.error("Failed to search the item in offers page");
        }
        Thread.sleep(1000);

    }
    @Step("Get elements from offers page table")
    public List<String> getElementsFromTable(){

        List<WebElement> tElement=driver.findElements(tableElements);
        List<String>productName=new ArrayList<>();
        String product;
        for(int i=0;i<tElement.size();i++){

            product=tElement.get(i).getText().trim();
            productName.add(product);

        }
        System.out.println(productName);
        return productName;
    }

}
