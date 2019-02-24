package de.tomesoft.fuh.s01919;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class TestRunner2 {

    public static void main(String[] args) {
        List<String> suiteList = new ArrayList<>();
        suiteList.add("target/test-classes/api-run-tng.xml");
//        ITestNGListener testRunMonitor = new TestMonitorListener();
        TestNG runner = new TestNG();
        runner.setTestSuites(suiteList);
//        runner.addListener(testRunMonitor);
        runner.setUseDefaultListeners(false);
        runner.setVerbose(0);
        runner.run();
    }
}
