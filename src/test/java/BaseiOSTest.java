import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;

public class BaseiOSTest {
	WebDriver driver;
	
	public static IOSDriver DesiredCapabilities() throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.2");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 50000);
		capabilities.setCapability("commandTimeouts", "12000");
		
		capabilities.setCapability(MobileCapabilityType.APP, "Users/putriharviana/Desktop/SwagLabsMobileApp.app");

		IOSDriver driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
		
		return driver;
	}

}
