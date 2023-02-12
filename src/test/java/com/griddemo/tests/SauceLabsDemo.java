package com.griddemo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhavesh
 */
public class SauceLabsDemo {
    //https://wiki.saucelabs.com/display/DOCS/Example+Selenium+Scripts+for+Automated+Website+Tests
    //https://wiki.saucelabs.com/display/DOCS/Java+Test+Setup+Example
    public WebDriver driver;
    public static final String USERNAME = "oauth-ccodebuster-12791";
    public static final String ACCESS_KEY = "c2184584-0fbb-4793-97e8-7056f96c5c16";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setPlatformName("Windows 11");
        options.setBrowserVersion("109");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", USERNAME);
        sauceOptions.put("accessKey", ACCESS_KEY);

        options.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://oauth-ccodebuster-12791:c2184584-0fbb-4793-97e8-7056f96c5c16@ondemand.eu-central-1.saucelabs.com:443/wd/hub");

        driver = new RemoteWebDriver(url, options);
    }

    @Test
    public void sauceLabTest() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        System.out.println("title of page is: " + driver.getTitle());
        Thread.sleep(3000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
