package stepDefinitions;

import Utils.dependedtMethods;
import configuration.BaseClass;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import listeners.TestAllureListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.checkoutPage;

import java.util.List;
@Listeners({TestAllureListener.class})
public class checkoutStepDef extends BaseClass {

   dependedtMethods dependentMethods;
    public checkoutStepDef(dependedtMethods dependentMethods ){

        this.dependentMethods=dependentMethods;
    }
    public checkoutPage checkoutPage;
   @Then("user Checkout and validate the {string} items in checkout page")
   public void user_checkout_and_validate_the_items_in_checkout_page(String value) throws InterruptedException {
       // Write code here that turns the phrase above into concrete actions
       System.out.println("item:"+value);
       checkoutPage = new checkoutPage();

       checkoutPage.CheckoutItems();

       List<String> productInCheckout=checkoutPage.ExtractProduct();
       System.out.println("productInCheckout:"+productInCheckout);
       for (int i = 0; i < productInCheckout.size(); i++) {
       Assert.assertTrue(productInCheckout.get(i).contains(value));
       logger.info("elements displayed in check out page is matched with item" + value);
        }
       System.out.println("productInLandingPage:"+dependentMethods.landingPageProduct);
        Assert.assertEquals(productInCheckout, dependentMethods.landingPageProduct);
       logger.info("Product in landing page and checkout pages are same as expected");
   }

    @And("Verify the place order page")
    public void placeTheOrder() throws InterruptedException {

        Thread.sleep(1000);

        Assert.assertTrue(checkoutPage.VerifyPlaceOrderButton());
        logger.info("Place order button is displayed as expected");
    }
}
