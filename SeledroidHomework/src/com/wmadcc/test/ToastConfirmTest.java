package com.wmadcc.test;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.client.waiter.TestWaiter;
import io.selendroid.common.SelendroidCapabilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ToastConfirmTest {

	private WebDriver driver = null;
	
	@Before
	public void setUp() throws Exception {
		driver = new SelendroidDriver(new SelendroidCapabilities("io.selendroid.testapp:0.15.0"));
	}

	@Test
	public void testToastConfirm() throws InterruptedException {
		WebElement showToast = driver.findElement(By.id("showToastButton"));
		showToast.click();
		TestWaiter.waitForElement(By.partialLinkText("Hello selendroid toast!"), 4, driver);
		Thread.sleep(3000);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
