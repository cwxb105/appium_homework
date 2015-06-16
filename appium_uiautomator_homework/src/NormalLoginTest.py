#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os
import time
import unittest

from appium import webdriver


PATH = lambda p: os.path.join(os.path.dirname(__file__), p)

class NormalLoginTest(unittest.TestCase):

    def setUp(self):
        desired_caps = {}
        desired_caps['platformName'] = 'Android'
        desired_caps['platformVersion'] = '4.4'
        desired_caps['deviceName'] = 'MX4'
        desired_caps['app'] = PATH('51zhangdan.apk')
        desired_caps['appPackage'] = 'com.zhangdan.app'
        desired_caps['appActivity'] = 'com.zhangdan.app.activities.SplashActivity'
        
        desired_caps['noReset'] = 'false'
        desired_caps['unicodeKeyboard'] = 'true'
        desired_caps['resetKeyboard'] = 'true'
        
        desired_caps['appWaitActivity'] = \
                    'com.zhangdan.app.activities.main.NewVersionGuideActivity'
        
        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

    def tearDown(self):
        self.driver.quit()

    def testNormalLogin(self):
        user_name = 'cctest003'
        password = 'qwerty'
        
        # slide right to login activity if new version guide exist
        try:
            self.driver.implicitly_wait(6)
            self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                                        'resourceId("com.zhangdan.app:id/LinearLayout_Point")')
            time.sleep(1)
            
            screen_width = self.driver.get_window_size()['width']
            screen_height = self.driver.get_window_size()['height']
            flick_start_x = screen_width * 4 / 5
            flick_end_x = screen_width / 5
            flick_start_y = flick_end_y = screen_height / 2
            for i in xrange(3):
                self.driver.swipe(flick_start_x, flick_start_y, \
                                 flick_end_x, flick_end_y, 500)
                time.sleep(1)
        except:
            pass
        
        # click login button to normal login page
        self.driver.implicitly_wait(5)
        normal_login = self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                                        'resourceId("com.zhangdan.app:id/Button_OldUser_Login")')
        normal_login.click()
        time.sleep(2)
        
        # input user name and password, then click login button to login
        user_name_edit = self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                                            'resourceId("com.zhangdan.app:id/EditText_UserName")')
        password_edit = self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                                            'resourceId("com.zhangdan.app:id/EditText_Passwd")')
        user_name_edit.clear()
        time.sleep(0.5)
        user_name_edit.send_keys(user_name)
        time.sleep(0.5)
        
        password_edit.clear()
        time.sleep(0.5)
        password_edit.send_keys(password)
        time.sleep(0.5)
        
        self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                                    'resourceId("com.zhangdan.app:id/Button_Login")').click()
        
        # click index radio button in radio group
        self.driver.implicitly_wait(25)
        self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                                    'resourceId("com.zhangdan.app:id/RadioButton_Index")').click()
        time.sleep(2)
        
        # click user name to enter the user center, then logout
        self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                                    'resourceId("com.zhangdan.app:id/TextView_User_Name")').click()
        time.sleep(1.5)
        
        self.driver.implicitly_wait(3)
        logoutTv = self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                                                'resourceId("com.zhangdan.app:id/TextView_Exit")')
        logoutTv.click()
        
        self.driver.implicitly_wait(2)
        self.driver.find_element_by_android_uiautomator('new UiSelector().text("确定注销")').click()


        self.driver.implicitly_wait(18)
        self.driver.find_element_by_android_uiautomator('new UiSelector().' + \
                            'resourceId("com.zhangdan.app:id/Button_OldUser_Login")')

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()