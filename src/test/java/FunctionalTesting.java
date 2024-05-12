import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class FunctionalTesting extends BaseiOSTest {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		IOSDriver driver = DesiredCapabilities();
//		
		List<MobileElement> elementUsername = driver.findElementsByAccessibilityId("test-Username");
		for (MobileElement element : elementUsername) {
			// Perform actions like click, sendKeys, etc.
			element.sendKeys("standard_user");
		}
		
		List<MobileElement> elementPassword = driver.findElementsByAccessibilityId("test-Password");
		for (MobileElement element : elementPassword) {
			// Perform actions like click, sendKeys, etc.
			element.sendKeys("secret_sauce");
		}
		
		List<MobileElement> elementsButtonLogin = driver.findElementsByXPath("//XCUIElementTypeOther[@name=\"test-LOGIN\"]");
		for (MobileElement element : elementsButtonLogin) {
            // Perform actions like click, sendKeys, etc.
            element.click();
        }
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<MobileElement> elementsAddToCartFirst = driver.findElementsByXPath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[1]");
		for (MobileElement element : elementsAddToCartFirst) {
            // Perform actions like click, sendKeys, etc.
            element.click();
        }
		
		//Scroll function
		HashMap<String,Object>scrollObject = new HashMap<>();
		scrollObject.put("direction", "down");
		scrollObject.put("label", "Sauce Labs Onesie");
		
		driver.executeScript("mobile:scroll", scrollObject);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List<MobileElement> elementsAddToCartSecond = driver.findElementsByXPath("(//XCUIElementTypeOther[@name=\"test-ADD TO CART\"])[4]");
		for (MobileElement element : elementsAddToCartSecond) {
            // Perform actions like click, sendKeys, etc.
            element.click();
        }
		
		String actualString = driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"2\"])[3]").getText();
//		System.out.println(actualString);
		String expectedString = "2";
		
		if (actualString.equals(expectedString)) {
            System.out.println("Total item checkout correct");
            // Perform action
        } else {
            System.out.println("Total item checkout not correct");
            // Perform alternate action
        }
		
		//Get name item
		String actualFirstItem = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"test-Item title\" and @label=\"Sauce Labs Backpack\"]").getText();
//		System.out.println(actualFirstItem);
		
		String actualSecondItem = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"test-Item title\" and @label=\"Sauce Labs Onesie\"]").getText();
//		System.out.println(actualSecondItem);
		
		List<MobileElement> elementsCartIcon = driver.findElementsByXPath("(//XCUIElementTypeOther[@name=\"2\"])[3]");
		for (MobileElement element : elementsCartIcon) {
            // Perform actions like click, sendKeys, etc.
            element.click();
        }
		
		String expectedFirstItem = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Sauce Labs Backpack\"]").getText();
		String expectedSecondItem = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Sauce Labs Onesie\"]").getText();
		
		//Checking item checkout
		if ((actualFirstItem.equals(expectedFirstItem)) && (actualSecondItem.equals(expectedSecondItem))) {
            System.out.println("First item and second item checkout correct");
            
            HashMap<String,Object>scrollObjectButton = new HashMap<>();
            scrollObjectButton.put("direction", "down");
            scrollObjectButton.put("label", "CONTINUE SHOPPING");
    		
    		driver.executeScript("mobile:scroll", scrollObjectButton);
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            
            List<MobileElement> elementsCheckoutButton = driver.findElementsByXPath("//XCUIElementTypeOther[@name=\"test-CHECKOUT\"]");
    		for (MobileElement element : elementsCheckoutButton) {
                // Perform actions like click, sendKeys, etc.
                element.click();
            }
    		
            // Checkout information pages
    		List<MobileElement> elementFirstName = driver.findElementsByAccessibilityId("test-First Name");
    		for (MobileElement element : elementFirstName) {
    			// Perform actions like click, sendKeys, etc.
    			element.sendKeys("First");
    		}
    		
    		List<MobileElement> elementLastName = driver.findElementsByAccessibilityId("test-Last Name");
    		for (MobileElement element : elementLastName) {
    			// Perform actions like click, sendKeys, etc.
    			element.sendKeys("Last");
    		}
    		List<MobileElement> elementZipCode = driver.findElementsByAccessibilityId("test-Zip/Postal Code");
    		for (MobileElement element : elementZipCode) {
    			// Perform actions like click, sendKeys, etc.
    			element.sendKeys("123456\n");
    		}
    		
    		String expectedElementFirstName = driver.findElementByXPath("//XCUIElementTypeTextField[@name=\"test-First Name\"]").getText();
    		String expectedElementLastName = driver.findElementByXPath("//XCUIElementTypeTextField[@name=\"test-Last Name\"]").getText();
    		String expectedElementZipCode = driver.findElementByXPath("//XCUIElementTypeTextField[@name=\"test-Zip/Postal Code\"]").getText();
    		
    		if(expectedElementFirstName != null && expectedElementLastName != null && expectedElementZipCode != null) {
    			List<MobileElement> elementContinueCheckout = driver.findElementsByXPath("//XCUIElementTypeOther[@name=\"test-CONTINUE\"]");
        		for (MobileElement element : elementContinueCheckout) {
        			// Perform actions like click, sendKeys, etc.
        			element.click();
        		}
        		
        		String firstItemValue = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"$29.99\"]").getText();
        		String secondItemValue = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"$7.99\"]").getText();
        		
//        		String firstItemValueOnly = firstItemValue.substring(0, firstItemValue.length() - 1); //remove last character
        		String firstItemValueOnly = firstItemValue.substring(1);
        		String secondItemValueOnly = secondItemValue.substring(1);
        		float firstValue = Float.parseFloat(firstItemValueOnly);
        		float secondValue = Float.parseFloat(secondItemValueOnly);
        		float expectedTotal = firstValue + secondValue;
//        		System.out.println(expectedTotal);
        		
        		String itemTotalActual = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Item total: $37.98\"]").getText();
        		String itemTotalReplaceString = itemTotalActual.replaceAll("Item total:\\s+", "");
        		String itemTotalActualValue = itemTotalReplaceString.substring(1);
//        		System.out.println(itemTotalActualValue);
        		float totalValueActual = Float.parseFloat(itemTotalActualValue);
        		
        		if(expectedTotal == totalValueActual) {
        			System.out.println("Item Total Value Correct");
        			
        			String taxValueActual = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Tax: $3.04\"]").getText();
        			String taxActualRemove = taxValueActual.replaceAll("Tax:\\s+", "");
        			String taxActualValue = taxActualRemove.substring(1);
        			float totalTaxActual = Float.parseFloat(taxActualValue);
//        			System.out.println(totalTaxActual);
        			
        			float totalExpected = totalValueActual + totalTaxActual;
//        			System.out.println(totalExpected);
        			
        			String actualTotal = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Total: $41.02\"]").getText();
        			String actualTotalRemove = actualTotal.replaceAll("Total:\\s+", "");
        			String actualTotalValue = actualTotalRemove.substring(1);
        			float totalFinal = Float.parseFloat(actualTotalValue);
//        			System.out.println(totalFinal);
        			
        			if (totalExpected == totalFinal) {
        				System.out.println("Total Final Value Correct");
        				
        				HashMap<String,Object>scrollObjectCheckout = new HashMap<>();
        				scrollObjectCheckout.put("direction", "down");
        				scrollObjectCheckout.put("label", "CANCEL");
        				
        				driver.executeScript("mobile:scroll", scrollObject);
        				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        				
        				List<MobileElement> elementFinishButton = driver.findElementsByXPath("//XCUIElementTypeOther[@name=\"test-FINISH\"]");
        	    		for (MobileElement element : elementFinishButton) {
        	    			// Perform actions like click, sendKeys, etc.
        	    			element.click();
        	    		}
        	    		
        	    		String actualTitleSuccess = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"THANK YOU FOR YOU ORDER\"]").getText();
        	    		String expectedTitleSuccess = "THANK YOU FOR YOU ORDER";
        	    		
        	    		String actualContentSuccess = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your order has been dispatched, and will arrive just as fast as the pony can get there!\"]").getText();
        	    		String expectedContentSuccess = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        	    		
        	    		if((actualTitleSuccess == expectedTitleSuccess) && (actualContentSuccess == expectedContentSuccess)) {
        	    			System.out.println("String success page correct");
        	    		} else {
        	    			System.out.println("String success page not correct");
        	    		}
        	    		
        	    		List<MobileElement> elementBackHome = driver.findElementsByXPath("//XCUIElementTypeOther[@name=\"test-BACK HOME\"]");
        	    		for (MobileElement element : elementBackHome) {
        	    			// Perform actions like click, sendKeys, etc.
        	    			element.click();
        	    		}
        				
        			} else {
        				System.out.println("Total Final Value Uncorrect");
        			}
         			
        		} else {
        			System.out.println("Total Value Not Correct");
        		}
    		} else {
    			String errorMessages = driver.findElementByXPath("//XCUIElementTypeOther[@name=\"test-Error message\"]").getText();
    			System.out.println(errorMessages);
    		}
        } else {
        	System.out.println("Item checkout not correct");
        }

	}

}
