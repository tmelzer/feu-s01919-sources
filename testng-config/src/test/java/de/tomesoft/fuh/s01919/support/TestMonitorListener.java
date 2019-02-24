package de.tomesoft.fuh.s01919.support;

import org.testng.*;

public class TestMonitorListener implements IExecutionListener, ISuiteListener, IConfigurationListener2, ITestListener {
    @Override
    public void onExecutionStart() {
        System.out.println("::on execution start");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("::on execution finish");
    }

    @Override
    public void beforeConfiguration(ITestResult iTestResult) {
        System.out.println(String.format("::before configuration %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onConfigurationSuccess(ITestResult iTestResult) {
        System.out.println(String.format("::on configuration success %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onConfigurationFailure(ITestResult iTestResult) {
        System.out.println(String.format("::on configuration failure %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onConfigurationSkip(ITestResult iTestResult) {
        System.out.println(String.format("::on configuration skip %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println(String.format("::on suite start - %s", iSuite.getName()));
    }

    @Override
    public void onFinish(ISuite iSuite) {
        System.out.println(String.format("::on suite finish - %s", iSuite.getName()));
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(String.format("::on test start %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("::on test success %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(String.format("::on test failure %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(String.format("::on test skipped %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println(String.format("::on test within success percent %s-%s-%s", iTestResult.getName(), iTestResult.getInstanceName(), iTestResult.getTestName()));
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println(String.format("::on test start before config %s", iTestContext.getName()));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(String.format("::on all test and config run %s", iTestContext.getName()));
    }
}
