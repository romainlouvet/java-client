package io.appium.java_client.pagefactory_tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSFindBys;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AndroidPageObjectTest {
	
	private AppiumDriver driver;
	@FindBy(className = "android.widget.TextView")
	private List<WebElement> textVieWs;
	
	@AndroidFindBy(className = "android.widget.TextView")
	private List<WebElement> androidTextViews;
	
	@iOSFindBy(uiAutomator = ".elements()[0]")
	private List<WebElement> iosTextViews;	
	
	@iOSFindBy(uiAutomator = ".elements()[0]")
	@AndroidFindBy(className = "android.widget.TextView")
	private List<WebElement> androidOriOsTextViews;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/text1\")")
	private List<WebElement> androidUIAutomatorViews;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/text1\")")
	private List<MobileElement> mobileElementViews;
	
	@FindBy(className = "android.widget.TextView")
	private List<MobileElement> mobiletextVieWs;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/text1\")")
	private List<RemoteWebElement> remoteElementViews;
		
	@AndroidFindBys({
		@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/list\")"),
		@AndroidFindBy(className = "android.widget.TextView")
		})
	private List<WebElement> chainElementViews;
		
	@iOSFindBys({@iOSFindBy(uiAutomator = ".elements()[0]"),
		@iOSFindBy(xpath = "//someElement")})
	private List<WebElement> iosChainTextViews;	
	
	@AndroidFindBys({
		@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/list\")"),
		@AndroidFindBy(id = "android:id/text1")
		})
	@iOSFindBys({@iOSFindBy(uiAutomator = ".elements()[0]"),
		@iOSFindBy(xpath = "//someElement")})
	private List<WebElement> chainAndroidOrIOSUIAutomatorViews;	
	
	
	@FindBy(id = "android:id/text1")
	private WebElement textView;	
	
	@AndroidFindBy(className = "android.widget.TextView")
	private WebElement androidTextView;
	
	@iOSFindBy(uiAutomator = ".elements()[0]")
	private WebElement iosTextView;
	
	@AndroidFindBy(className = "android.widget.TextView")
	@iOSFindBy(uiAutomator = ".elements()[0]")
	private WebElement androidOriOsTextView;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/text1\")")
	private WebElement androidUIAutomatorView;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/text1\")")
	private MobileElement mobileElementView;
	
	@FindBy(className = "android.widget.TextView")
	private MobileElement mobiletextVieW;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/text1\")")
	private RemoteWebElement remotetextVieW;	
	
	@AndroidFindBys({
		@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/list\")"),
		@AndroidFindBy(className = "android.widget.TextView")
		})
	private WebElement chainElementView;
	
	@iOSFindBys({@iOSFindBy(uiAutomator = ".elements()[0]"),
		@iOSFindBy(xpath = "//someElement")})
	private WebElement iosChainTextView;
	
	@AndroidFindBys({
		@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/list\")"),
		@AndroidFindBy(id = "android:id/text1")
		})
	@iOSFindBys({@iOSFindBy(uiAutomator = ".elements()[0]"),
		@iOSFindBy(xpath = "//someElement")})
	private WebElement chainAndroidOrIOSUIAutomatorView;	

	@Before
	public void setUp() throws Exception {
	    File appDir = new File("src/test/java/io/appium/java_client");
	    File app = new File(appDir, "ApiDemos-debug.apk");
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	    driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void findByElementsTest() {
		Assert.assertNotEquals(0, textVieWs.size());
	}
	
	@Test
	public void findByElementTest() {
		Assert.assertNotEquals(null, textView.getAttribute("text"));
	}
	
	
	@Test
	public void androidFindByElementsTest(){
		Assert.assertNotEquals(0, androidTextViews.size());
	}
	
	@Test
	public void androidFindByElementTest(){
		Assert.assertNotEquals(null, androidTextView.getAttribute("text"));
	}
	
	@Test
	public void checkThatElementsWereNotFoundByIOSUIAutomator(){
		Assert.assertEquals(0, iosTextViews.size());
	}
	
	@Test
	public void checkThatElementWasNotFoundByIOSUIAutomator(){
		NoSuchElementException nsee = null;
		try{
			iosTextView.getAttribute("text");
		}
		catch (Exception e){
			nsee = (NoSuchElementException) e;
		}
		Assert.assertNotNull(nsee);
	}
	
	@Test
	public void androidOrIOSFindByElementsTest(){
		Assert.assertNotEquals(0, androidOriOsTextViews.size());
	}
	
	@Test
	public void androidOrIOSFindByElementTest(){
		Assert.assertNotEquals(null, androidOriOsTextView.getAttribute("text"));
	}
	
	@Test
	public void androidFindByUIAutomatorElementsTest(){
		Assert.assertNotEquals(0, androidUIAutomatorViews.size());
	}
	
	@Test
	public void androidFindByUIAutomatorElementTest(){
		Assert.assertNotEquals(null, androidUIAutomatorView.getAttribute("text"));
	}
	
	@Test
	public void areMobileElementsTest(){
		Assert.assertNotEquals(0, mobileElementViews.size());
	}
	
	@Test
	public void isMobileElementTest(){
		Assert.assertNotEquals(null, mobileElementView.getAttribute("text"));
	}
	
	@Test
	public void areMobileElements_FindByTest(){
		Assert.assertNotEquals(0, mobiletextVieWs.size());
	}
	
	@Test
	public void isMobileElement_FindByTest(){
		Assert.assertNotEquals(null, mobiletextVieW.getAttribute("text"));
	}	
	
	@Test
	public void areRemoteElementsTest(){
		Assert.assertNotEquals(0, remoteElementViews.size());
	}
	
	@Test
	public void isRemoteElementTest(){
		Assert.assertNotEquals(null, remotetextVieW.getAttribute("text"));
	}	
	
	@Test
	public void androidChainSearchElementsTest(){
		Assert.assertNotEquals(0, chainElementViews.size());
	}
	
	@Test
	public void androidChainSearchElementTest(){
		Assert.assertNotEquals(null, chainElementView.getAttribute("text"));
	}	
	
	@Test
	public void checkThatElementsWereNotFoundByIOSUIAutomator_Chain(){
		Assert.assertEquals(0, iosChainTextViews.size());
	}	
	
	@Test
	public void checkThatElementWasNotFoundByIOSUIAutomator_Chain(){
		NoSuchElementException nsee = null;
		try{
			iosChainTextView.getAttribute("text");
		}
		catch (Exception e){
			nsee = (NoSuchElementException) e;
		}
		Assert.assertNotNull(nsee);
	}

	@Test
	public void androidOrIOSFindByElementsTest_ChainSearches(){
		Assert.assertNotEquals(0, chainAndroidOrIOSUIAutomatorViews.size());
	}

	@Test
	public void androidOrIOSFindByElementTest_ChainSearches(){
		Assert.assertNotEquals(null, chainAndroidOrIOSUIAutomatorView.getAttribute("text"));
	}			
}
