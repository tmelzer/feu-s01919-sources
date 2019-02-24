package de.tomesoft.fuh.s01919;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.InvalidArgumentException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

@Listeners(AverageReport.class)
public class PdfLoaderTest {
  
  private PdfLoader loader = null;
   
  @DataProvider(name="profileIdProvider")
  public Object[][] getProfileIds(ITestContext context) {
    HashMap<String, String> profileIdMap = (HashMap<String, String>) context.getAttribute("profiles");
    Object[][] paramSets = new Object[profileIdMap.size()*10][];
    
    int i = 0;
    for (String testProfileId : profileIdMap.keySet()) {
        for (int j = i * 10; j < (i+1) * 10; j++) {
          paramSets[j] = new Object[1];
          paramSets[j][0] = testProfileId;
       }
       i++;
    }
    
    return paramSets;
  } 
  
  @DataProvider(name="profileNameProvider")
  public Object[][] getProfileNames(ITestContext context) {
    String profilesParam = context.getCurrentXmlTest().getParameter("profiles");
    if (profilesParam == null || profilesParam.isEmpty()) {
       throw new InvalidArgumentException("'profiles' parameter not defined or empty");
    }
    
    String[] profileNames = profilesParam.split(";");
    Object[][] data = new Object[profileNames.length][];
    for (int i = 0; i < profileNames.length; i++) {
       data[i] = new Object[1];
       data[i][0] = profileNames[i];
    }
       
    return data;
  }

  @BeforeTest
  public void initTest(ITestContext context) {
    HashMap<String, String> profileIdMap = new HashMap<>();
    HashMap<String, Long> proposalLengthMap = new HashMap<>();
    context.setAttribute("profiles", profileIdMap);
    context.setAttribute("proposalLength", proposalLengthMap);
  }

  @BeforeClass
  @Parameters("driver-path")
  public void init(String driverPath) {
     loader = new PdfLoader(driverPath);
     loader.startDriver();
  }

  @AfterClass
  public void shutDown() {
      loader.stopDriver();
  }
  
  @Test(dataProvider = "profileNameProvider")
  public void resolveProfile(ITestContext context, String profileName) {
     String profileId = loader.readProfileId(profileName);
     assertTrue(profileId != null && !profileId.isEmpty(), "profile '" + profileName + "' cannot be resolved");
     HashMap<String, String> profileIdMap = (HashMap<String, String>) context.getAttribute("profiles");
     profileIdMap.put(profileId, profileName);
  }

  @Test(dependsOnMethods="resolveProfile", dataProvider = "profileIdProvider")
  public void loadPdf(ITestContext context, String profileId) throws IOException, InterruptedException {
     Long pdfLen = loader.loadPdf(profileId);
     assertTrue(pdfLen != null && pdfLen > 0, "pdf empty");
     HashMap<String, Long> proposalLengthMap = (HashMap<String, Long>) context.getAttribute("proposalLength");
     Long lastLen = proposalLengthMap.computeIfAbsent(profileId, (id) -> pdfLen);
     assertEquals(pdfLen, lastLen, "proposal " + profileId + " length mismatch");
  }
}
