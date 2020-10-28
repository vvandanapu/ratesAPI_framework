package com.ratesAPI.runners;

import com.cucumber.listener.Reporter;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
 
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/ratesAPI/FeatureFiles"
				     ,glue={"com.ratesAPI.stepDefinition"}
				 ,plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/Report.html",
						    "html:target/cucumber-reports/Html Report"}
				 ,dryRun = false
				 ,monochrome = true
				 ,tags={"@GET_Latest_Exchange_Rates, @GET_Exchange_rates_for_date"}
				 )
 
public class TestRunner {
	@AfterClass
	public static void writeExtentReport() {
		Reporter.setSystemInfo("User Name", "Vijay Vandanapu");
	    Reporter.setSystemInfo("Time Zone", "INDIA");
	    Reporter.setSystemInfo("Machine", 	"Windows 10 " + "64 Bit");
	    Reporter.setSystemInfo("Selenium", "3.14");
	    Reporter.setSystemInfo("Java" + " Version", "1.8.0_191");
	   	Reporter.getExtentHtmlReport();
	   	Reporter.getExtentReport();
	   
	}
 
} 
