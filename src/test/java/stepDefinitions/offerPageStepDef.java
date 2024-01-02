package stepDefinitions;

import Utils.dependedtMethods;
import configuration.BaseClass;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import listeners.TestAllureListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.offersPage;

import java.util.ArrayList;
import java.util.List;

@Listeners({TestAllureListener.class})
public class offerPageStepDef extends BaseClass {
    dependedtMethods dependentMethods;
    public offerPageStepDef(dependedtMethods dependentMethods ){

        this.dependentMethods=dependentMethods;
    }

    offersPage offersPage=new offersPage();
    List<String> offerPageProduct;

    @Then("user searched for {string} shortname in offers page")
    public void userSearchedForNameShortnameInOffersPage(String name) throws InterruptedException {

        Thread.sleep(2000);
        offersPage.switchToOfferPage();
        logger.info("User is in offers page");
        Thread.sleep(2000);
        offersPage.elementDisplayed();

        offersPage.searchItem(name);

        offerPageProduct=offersPage.getElementsFromTable();
        System.out.println("offerd page product in stepdef file"+offerPageProduct);
        System.out.println("offerd page product in landing file"+dependentMethods.landingPageProduct);


        for (String product : offerPageProduct) {
            Assert.assertTrue(product.contains("name"));
        }

    }


    @And("validate product name in offers page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {

        try {
            Assert.assertEquals(offerPageProduct, dependentMethods.landingPageProduct);
            logger.info("product name in offers page matches with Landing Page");
        }
        catch (Exception e){
            e.printStackTrace();
            driver.quit();
        }
    }
}
