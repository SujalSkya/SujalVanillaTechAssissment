package org.example.base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static AndroidDriver driver;
    public static AndroidDriver driver2;
    public static Properties prop;
    private static final String propertyFilePath = "Configuration/Config.properties";

    @BeforeSuite(alwaysRun = true)
    public static void lunchApp() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", prop.getProperty("platformNameSender"));
        cap.setCapability("appium:platformVersion", prop.getProperty("platformVersionSender");
        cap.setCapability("appium:automationName", "uiautomator2");
        cap.setCapability("appium:deviceName", prop.getProperty("deviceNameSender");
        cap.setCapability("appPackage", "com.khalti");
        cap.setCapability("appActivity", "com.khalti.MainActivity");
        cap.setCapability("udid", prop.getProperty("udid1"));


        DesiredCapabilities cap1 = new DesiredCapabilities();
        cap1.setCapability("platformName", prop.getProperty("platformNameReceiver"));
        cap1.setCapability("appium:platformVersion", prop.getProperty("platformVersionSender");
        cap1.setCapability("appium:automationName", "uiautomator2");
        cap1.setCapability("appium:deviceName", prop.getProperty("deviceNameReceiver");
        cap1.setCapability("udid",prop.getProperty("udid2"));
        cap1.setCapability("appPackage", "com.khalti");
        cap1.setCapability("appActivity", "com.khalti.MainActivity");


        URL url = new URL("http://127.0.0.1:4723/");

//        URL url1=new URL("http://127.0.0.1:4724/wd/hub");

        driver = new AndroidDriver(url, cap);
        driver2 = new AndroidDriver(url, cap1);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Application started...");

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            prop = new Properties();
            try {
                prop.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    @AfterSuite
    public void tearDown() {
    }
}
