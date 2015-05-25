package com.wmadcc.test;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class NormalLoginTest extends UiAutomatorTestCase {
	public void testNormalLogin() throws UiObjectNotFoundException {
		String userNmae = "cctest003";
		String password = "qwerty";
		
		// go back to home page by clicking home 
		UiDevice.getInstance().pressHome();
		sleep(2000);
		
		// open applications list by clicking Apps
		UiObject apps = new UiObject(new UiSelector().description("Apps"));
		apps.clickAndWaitForNewWindow();
		
		// scroll to target application and click it to open
		UiScrollable appsList = new UiScrollable(new UiSelector().resourceId(
				"com.android.launcher:id/apps_customize_pane_content"));
		appsList.setAsHorizontalList();
		UiObject u51Zhangdan = new UiObject(new UiSelector().text("51信用卡管家"));
		appsList.scrollIntoView(u51Zhangdan);
		u51Zhangdan.clickAndWaitForNewWindow();
		sleep(3000);
		
		// slide right to login activity if new version guide exist
		UiScrollable newVersionGuide = new UiScrollable(
				new UiSelector().resourceId("com.zhangdan.app:id/ViewPager"));
		if (newVersionGuide.exists()) {
			newVersionGuide.setAsHorizontalList();
			newVersionGuide.scrollToEnd(3);
		}
		
		// click login button to normal login page
		UiObject normalLogin = new UiObject(new UiSelector().
				resourceId("com.zhangdan.app:id/Button_OldUser_Login"));
		normalLogin.clickAndWaitForNewWindow();
		sleep(1000);
		
		// input user name and password, then click login button to login
		UiObject userNameEdit = new UiObject(new UiSelector().resourceId(
				"com.zhangdan.app:id/EditText_UserName"));
		UiObject passwordEdit = new UiObject(new UiSelector().resourceId(
				"com.zhangdan.app:id/EditText_Passwd"));
		userNameEdit.clearTextField();
		sleep(500);
		userNameEdit.setText(userNmae);
		sleep(500);
		passwordEdit.clearTextField();
		sleep(500);
		passwordEdit.setText(password);
		sleep(500);
		UiObject login = new UiObject(new UiSelector().resourceId(
				"com.zhangdan.app:id/Button_Login"));
		login.clickAndWaitForNewWindow();
		sleep(15000);
		
		// go back to home page of application if not in it
		UiObject homepgaeBtn = new UiObject(new UiSelector().resourceId(
				"com.zhangdan.app:id/RadioButton_Index"));
		while (!homepgaeBtn.exists()) {
			UiDevice.getInstance().pressBack();
			sleep(2000);
		}
		homepgaeBtn.click();
		sleep(1000);
		
		// click user name to enter the user center, then logout
		UiObject userNameTv = new UiObject(new UiSelector().resourceId(
				"com.zhangdan.app:id/TextView_User_Name"));
		userNameTv.clickAndWaitForNewWindow();
		
		UiScrollable scrollView = new UiScrollable(new UiSelector().className(
				"android.widget.ScrollView"));
		UiObject logoutTv = new UiObject(new UiSelector().resourceId(
				"com.zhangdan.app:id/TextView_Exit"));
		scrollView.setAsVerticalList();
		scrollView.scrollIntoView(logoutTv);
		logoutTv.clickAndWaitForNewWindow();
		
		UiObject confirmLogout = new UiObject(new UiSelector().text("确定注销"));
		confirmLogout.click();
		sleep(5000);
		
		// go back to home page
		UiDevice.getInstance().pressBack();
		sleep(1000);
		UiDevice.getInstance().pressHome();
		
	}
}
