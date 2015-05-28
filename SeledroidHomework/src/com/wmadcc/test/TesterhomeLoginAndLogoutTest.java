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


public class TesterhomeLoginAndLogoutTest {

	private WebDriver driver = null;
	
	@Before
	public void setUp() throws Exception {
		driver = new SelendroidDriver(SelendroidCapabilities.android());
	}

	@Test
	public void testLoginAndLogout() throws InterruptedException {
		String baseUrl = "http://www.testerhome.com";
		String loginText = "登录";
		String logoutText = "退出";
		
		String userName = "wmadcc";
		String password = "wmadcc4633";
		
		// get index page of testerhome
		driver.get(baseUrl);
		Thread.sleep(1000);
		
		// enter login page by clicking login button
		driver.findElement(By.partialLinkText(loginText)).click();
		Thread.sleep(500);
		
		// input user name and password 
		WebElement userNameInput = driver.findElement(By.id("user_login"));
		userNameInput.clear();
		userNameInput.sendKeys(userName);
		
		WebElement passwordInput = driver.findElement(By.id("user_password"));
		passwordInput.clear();
		passwordInput.sendKeys(password);
		
		// click login commit button and wait for success alert
		driver.findElement(By.name("commit")).click();
		TestWaiter.waitForElement(By.cssSelector("div.alert.alert-success"), 3, driver);
		Thread.sleep(3000);
		
		// click toggle menu button
		WebElement toggleMenuBtn = driver.findElement(By.cssSelector("button.navbar-toggle"));
		toggleMenuBtn.click();
		Thread.sleep(500);
		
		// click logout text and wait for success alert
		driver.findElement(By.partialLinkText(logoutText)).click();
		TestWaiter.waitForElement(By.cssSelector("div.alert.alert-success"), 3, driver);
		Thread.sleep(3000);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
