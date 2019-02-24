package de.tomesoft.fuh.s01919;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.lang.StringBuffer;

public class PdfLoader {

   private WebDriver webBrowser;
   private String driverPath;
   private String servletUrl;

   public PdfLoader(String path) {
      driverPath = path;
            
      servletUrl = "<url>";
      webBrowser = null;
   }

   public void startDriver() {
      // set path to ie wrapper exe
      System.setProperty("webdriver.chrome.driver", this.driverPath);
      webBrowser = new ChromeDriver();
   }
   
   public void stopDriver() {
      if (webBrowser != null) {
         webBrowser.quit();
      }
   }
   
   public String readProfileId(String profileName) {
      String profileId = null;
      webBrowser.get("<profile-url>");

      // find profile id
      WebElement select = webBrowser.findElement(By.name("profiles"));
      WebElement optionProfile = select.findElement(By.xpath(".//option[text() = '" + profileName + "']"));
      profileId = optionProfile.getAttribute("value");
      
      return profileId;
   }
   
   public Long loadPdf(String profileId) throws InterruptedException, IOException {
      
      JavascriptExecutor webExec = (JavascriptExecutor) webBrowser;

      String parTest = "id="
            + profileId;
      String parCreate = "id="
            + profileId
            + "&mode=init";
      
      String scriptTestLoader = String.format(getPageLoaderScript(), servletUrl, parTest, parTest.length());
      String scriptPdfLoader = String.format(getPageLoaderScript(), servletUrl, parCreate, parCreate.length());

      // Lade Feedback page
      Long testLength = (Long) webExec.executeScript(scriptTestLoader);
      
      // Lade pdf
      Long pdfLength = (Long) webExec.executeScript(scriptPdfLoader);
      
      return pdfLength;
   }

   private String getPageLoaderScript() {
      StringBuffer scriptBuilder = new StringBuffer();

      scriptBuilder.append("var http = new XMLHttpRequest();\n");
      scriptBuilder.append("var url = '%s\';\n");
      scriptBuilder.append("var params = '%s\';\n");
      // synchrone behandlung
      scriptBuilder.append("http.open('POST', url, false);\n");
      scriptBuilder.append("http.responseType = 'blob';\n");
      scriptBuilder.append("http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');\n");
      scriptBuilder.append("http.setRequestHeader('Content-length', %d);\n");
      scriptBuilder.append("http.setRequestHeader('Connection', 'close');\n");
      scriptBuilder.append("http.send(params);\n");
      scriptBuilder.append("var data = new Blob([http.response]);\n");
      scriptBuilder.append("return data.size;\n");

      return scriptBuilder.toString();
   }
}
