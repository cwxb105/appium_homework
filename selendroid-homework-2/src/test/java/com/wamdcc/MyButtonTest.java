package com.wamdcc;

import static io.selendroid.waiter.TestWaiter.waitForElement;
import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyButtonTest {
	private SelendroidDriver driver = null;
    public static final String ACTIVITY_CLASS = "io.selendroid.testapp." + "HomeScreenActivity";

    protected DesiredCapabilities getDefaultCapabilities() {
        SelendroidCapabilities caps = new SelendroidCapabilities();
        caps.setAut("io.selendroid.testapp:0.16.0-SNAPSHOT");
        caps.setLaunchActivity("io.selendroid.testapp.HomeScreenActivity");
        caps.setSelendroidExtensions("extension.dex");
        return caps;
    }

    @Before
    public void setup() throws Exception {
        driver = new SelendroidDriver(getDefaultCapabilities());
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void myButtonClickTest() {
        String myBtnClick = "Hello, my button click";
        
        // click my button
    	WebElement myBtn = driver.findElement(By.id("myButton"));
    	myBtn.click();
    	
    	// wait the toast to confirm click action
    	waitForElement(By.linkText(myBtnClick), 3, driver);
    }
    
    @Test
    public void myButtonLongClickTest() {
    	String myBtnLongClick = "Hello, my button long press";

    	WebElement myBtn = driver.findElement(By.id("myButton"));
    	
        TouchActions longClick = new TouchActions(driver).longPress(myBtn);
        longClick.perform();
    	
    	// wait the toast to confirm long click action
    	waitForElement(By.linkText(myBtnLongClick), 5, driver);
    	
//        WebElement button = driver.findElement(By.id("buttonTest"));
//        TouchActions longPress = new TouchActions(driver).longPress(button);
//        longPress.perform();
//        WebElement text = driver.findElement(By.partialLinkText("Long Press Tap"));
//        Assert.assertNotNull(text);
//        driver.findElement(By.id("button1")).click();
    }

    @After
    public void teardown() {
        driver.quit();
    }
    
}
