package com.griddemo.drivermanager;

import com.griddemo.propertyreader.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ManageDriver {

    public static WebDriver driver;
    public String baseUrl = PropertyReader.getInstance().getProperty("baseUrl");
    public String runOn = PropertyReader.getInstance().getProperty("runOn");
    public String gridUrl = PropertyReader.getInstance().getProperty("gridUrl");

    public void selectBrowser(String browser) {
        DesiredCapabilities dc = new DesiredCapabilities();
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (runOn.equalsIgnoreCase("remote")) {
                System.out.println("#########TEST RUNNING ON GRID ==> On the Browser is " + browser);
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                WebDriverManager.chromedriver().setup();
                System.out.println("#########TEST RUNNING ON LOCAL ==> On the Browser is " + browser);
                driver = new ChromeDriver();
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (runOn.equalsIgnoreCase("remote")) {
                System.out.println("#########TEST RUNNING ON GRID ==> On the Browser is " + browser);
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                WebDriverManager.firefoxdriver().setup();
                System.out.println("#########TEST RUNNING ON LOCAL ==> On the Browser is " + browser);
                driver = new FirefoxDriver();
            }
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (runOn.equalsIgnoreCase("remote")) {
                System.out.println("#########TEST RUNNING ON GRID ==> On the Browser is " + browser);
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                WebDriverManager.edgedriver().setup();
                System.out.println("#########TEST RUNNING ON LOCAL ==> On the Browser is " + browser);
                driver = new EdgeDriver();
            }
        } else {
            System.out.println("Wrong browser name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(PropertyReader.getInstance()
                .getProperty("implicitlyWait"))));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get(baseUrl);
    }

    public void closeBrowser() {
        driver.close();
        driver.quit();
    }

    public WebDriver getRemoteDriver(String url, DesiredCapabilities dc) {
        try {
            return new RemoteWebDriver(new URL(url), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
