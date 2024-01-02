package configuration;



import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static java.sql.DriverManager.getDriver;

public class BaseClass {

    public static Logger logger=LogManager.getLogger(BaseClass.class);;
    public static Properties prop;
    public static WebDriver driver;
  //  public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    public BaseClass() {

        try {
        prop=new Properties();
        FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties"
                +"/config.properties");

            prop.load(file);
        }  catch (FileNotFoundException e) {
            logger.error("Properties file is not found in the location",e);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initialization() throws InterruptedException {

        String browserName = prop.getProperty("browser");
        String url=prop.getProperty("baseURL");
        System.out.println("URL"+url);
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.get(url);
           //driver.get("https://webaccess:mmpass1@www.uat.morganmckinley.com");
           // driver.get( "https://www.uat.morganmckinley.com");
            logger.info("Logged in successfully");
            driver.manage().window().maximize();

                Thread.sleep(2000);


        }
        else if(browserName.equals("FF")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver.exe");


            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(url);
            System.out.println(prop.getProperty(url));
            driver.navigate().refresh();
        } else if (browserName.equals("edge")) {

            System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "/src/test/resources/drivers/msedgedriver.exe");
            //instance of EdgeDriver
            EdgeOptions options=new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
           driver = new EdgeDriver(options);
            //URL launch
            driver.get(url);
            driver.manage().window().maximize();
        }
        // tdriver.set(driver);
        //return getDriver();
    }
  /* public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }*/
   /*public void tearDown()throws IOException {


      *//*  if(scenario.isFailed()){
            File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Failed Screenshot",new FileInputStream(screenshot));
        }
        else{
            System.out.println("Scenario Passed");
        }*//*
        driver.quit();
        logger.info("Driver closed successfully");

    }*/
   /* public String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }
        return path;
    }*/

}
