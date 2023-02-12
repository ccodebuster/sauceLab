package com.griddemo.testbase;

import com.griddemo.drivermanager.ManageDriver;
import com.griddemo.propertyreader.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by bhavesh
 */
public class TestBase extends ManageDriver {

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("optional") String browser){
        if (browser.equals("optional")){
            selectBrowser(PropertyReader.getInstance().getProperty("browser"));
        }else {
            selectBrowser(browser);
        }
    }

    @AfterMethod
    public void tearDown(){
        closeBrowser();
    }
}
