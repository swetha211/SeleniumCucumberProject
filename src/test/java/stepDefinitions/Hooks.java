package stepDefinitions;

import configuration.BaseClass;





import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Hooks extends BaseClass{

    //Scenario scenario;

    @Before
    public void beforeScenario() throws IOException, InterruptedException {
        System.out.println("hooks before method");
       BaseClass.initialization();
    }



    @After

    public void closeTheWebdriver() throws IOException {

      /* if(scenario.isFailed()){
            File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Failed Screenshot",new FileInputStream(screenshot));
        }*/
      // driver.quit();

       // logger.info("Webdriver Closed Successfully");
    }

}
