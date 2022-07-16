package com.cucumber.junit.hooks;

import driver.SingletonDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHooks {
//    @Before
//    public void setupDriver(){
//        SingletonDriver.getInstance();
//    }

    @After
    public void quitDriver(){
        SingletonDriver.quitDriver();
    }
}

