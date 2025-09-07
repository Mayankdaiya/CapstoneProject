package com.demoblaze;

import org.testng.TestNG;

import java.util.Collections;

public class AppTest {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList("testng.xml"));
        testng.setOutputDirectory("test-output"); // force output directory
        testng.run();
    }
}
