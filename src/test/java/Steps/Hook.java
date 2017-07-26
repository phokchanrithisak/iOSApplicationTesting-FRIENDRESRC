package Steps;

import Base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by chhormchhatra from KEEN Virtual Company of KIT on 7/26/17.
 */

public class Hook extends BaseUtil{
    //    Before each scenario
    private BaseUtil base;
    public Hook(BaseUtil base) {
        this.base = base;
    }



    @Before
    public void initializer(){



        /*
        * TODO Specify each variable below:
        * NB :
        *   for real device, give simulator name as the device name
        *   and run instruments -s devices in terminal to get device name, platform Version and the unique code as udid
        *   and add one more cap.setCapability("udid", udidCode)
        * NB :
        *   If you do have only .ipa file, extract that .ipa file into .app file
        *   Then copy .app file into src/test/Res
        * */


        String simulatorName = "Virtual Device Name"; // can be found by run: instruments -s devices
        String appName = "appName.app";
        String bundleIdentifier = "";
        File app = new File("src/test/Res", appName);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName", "");
        cap.setCapability("platformName", "iOS");
        cap.setCapability("platformVersion", "e.g. 10.3.2");
        cap.setCapability("deviceName", simulatorName);
        cap.setCapability("app", app.getAbsolutePath());
        cap.setCapability("bundleId", bundleIdentifier);
        cap.setCapability("noReset", false);
        cap.setCapability("showXcodeLog", true);

		/*
		 * More configuration, see https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
		 * If the test fail because of the activity, try to check the log for the real activity and package name
		 * and replace it in the above code
		 * */



        MobileDriver<IOSElement> driver;
        try {
            driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            base.driver = driver;
        } catch (MalformedURLException e) {
            Assert.fail(e.getMessage());
        }

    }
    //    After each scenario
    @After
    public void terminator(Scenario scenario){
        if(scenario.isFailed()){
//          TODO code to take screenshot
            System.out.println("The scenario " +scenario.getName()+" is failed");
        }
        /*
        * Use close() to terminate the browser
        * Use quit() to terminate the driver instance
        * */
        base.driver.quit();
        System.out.println("Terminated App");
    }

}