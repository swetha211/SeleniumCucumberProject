package Options;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



/*@Listeners({TestAllureListener.class})*/
@CucumberOptions(features="src/test/java/features",glue ="stepDefinitions",
        monochrome=true,

        plugin= {"html:target/cucumber.html", "json:target/cucumber.json"})
public class testRunner extends AbstractTestNGCucumberTests {

    /*@Override
    @DataProvider(parallel=true)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }
*/
}

