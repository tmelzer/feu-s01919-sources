package de.tomesoft.fuh.s01919;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class AverageReport implements ISuiteListener, ITestListener {
   HashSet<String> skippedMethods = new HashSet<>();

   @Override
   public void onFinish(ISuite suite) {
      reportTestAverageRuntimes(suite, "minPages");
      reportTestAverageRuntimes(suite, "maxPages");
   }

   @Override
   public void onStart(ISuite suite) {
   }

   @Override
   public void onTestStart(ITestResult result) {
   }

   @Override
   public void onTestSuccess(ITestResult result) {
   }

   @Override
   public void onTestFailure(ITestResult result) {
   }

   @Override
   public void onTestSkipped(ITestResult result) {
      // TODO Auto-generated method stub
      Throwable ex = result.getThrowable();
      ITestNGMethod m = result.getMethod();

      if (ex != null) {
         // capture skipped test method that has thrown unexpected exception
         if (!skippedMethods.contains(m.getMethodName())) {
            skippedMethods.add(m.getMethodName());
         }
      }
   }

   @Override
   public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
   }

   @Override
   public void onStart(ITestContext context) {
   }

   @Override
   public void onFinish(ITestContext context) {
   }
   
   private void reportTestAverageRuntimes(ISuite suite, String testName) {
      if (skippedMethods.size() == 0) {
         HashMap<String, Long> lengths = new HashMap<>();
         HashMap<String, List<Double>> runtimes = new HashMap<>();
   
         for (IInvokedMethod method : suite.getAllInvokedMethods()) {
            if (method.isTestMethod()) {
               ITestNGMethod m = method.getTestMethod();
               ITestResult result = method.getTestResult();
               if (result != null && result.getTestContext().getCurrentXmlTest().getName().equals(testName)) {
                  if (m.getMethodName().equals("loadPdf")) {
                     HashMap<String, String> profileIdMap = (HashMap<String, String>) result.getTestContext().getAttribute("profiles");
                     HashMap<String, Long> proposalLengthMap = (HashMap<String, Long>) result.getTestContext().getAttribute("proposalLength");
                     //proposalLength
                     String profileName = profileIdMap.get((String) result.getParameters()[1]);
                     Long proposalLength = proposalLengthMap.get((String) result.getParameters()[1]);
                     long runtime = result.getEndMillis() - result.getStartMillis();
                     runtimes.computeIfAbsent(profileName, (name) -> new ArrayList<>()).add((double) runtime);
                     lengths.computeIfAbsent(profileName, (name) -> proposalLength);
                  }
               }
            }
         }
   
         System.err.println("Test: " + testName);
         runtimes.forEach((profile, value) -> value.stream().mapToDouble(a -> a).average()
               .ifPresent(avg -> System.err.println(profile + ": " + lengths.get(profile) + " bytes in " + avg + " ms")));
      }
   }
}
