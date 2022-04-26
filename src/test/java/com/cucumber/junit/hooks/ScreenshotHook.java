package com.cucumber.junit.hooks;

import com.cucumber.junit.driver.DriverManager;

import io.cucumber.java.Scenario;
import org.junit.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



public class ScreenshotHook {
    @After
    public void takeScreenshot(Scenario scenario){
        byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}
