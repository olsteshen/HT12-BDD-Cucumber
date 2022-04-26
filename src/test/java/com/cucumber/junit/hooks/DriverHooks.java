package com.cucumber.junit.hooks;

import driver.SingletonDriver;
import org.junit.After;
import org.junit.Before;


public class DriverHooks {
    @Before
    public void setupDriver(){
        SingletonDriver.getInstance();
    }

    @After
    public void quitDriver(){
        SingletonDriver.quitDriver();
    }
}

