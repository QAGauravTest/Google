package testPackage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Base.DriverInitializer;
import Base.NavigateToURL;
import pageObjects.HomePage;

public class TestNG {
	WebDriver driver ;
	 HomePage obj ;
	
	 @BeforeSuite
	  public void beforeSuite() throws IOException {
		 
		 
		 driver =  DriverInitializer.Initializer();
		 obj = new HomePage ( driver) ;
		 NavigateToURL.UrlNavigation();
		 
	  }
  @Test
  public void Test() {
	  obj.search_Google( "COVID-19");
	  obj.Navigate_to_News_Tab_and_Store_Links();
	
	  
	  
  }
 

  @AfterSuite
  public void afterSuite() {
	  System.out.println("After suite");
	  driver.quit();
  }

}
