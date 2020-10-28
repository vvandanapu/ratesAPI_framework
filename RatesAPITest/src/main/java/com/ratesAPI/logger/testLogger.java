package com.ratesAPI.logger;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;



/**
 *  Class Description - This provides logging methods 
 * @author Vijay Vandanapu
 *
 */

public class testLogger 
{
	private static Logger logger;
	static{
			logger = Logger.getLogger("RatesAPI");
			DOMConfigurator.configure(new File("log4j.xml").getAbsolutePath());
		 }	
	static final Logger LOGGER = Logger.getLogger(testLogger.class);
	
	public static void logInfo(String message){
		logger.info(message);
		com.cucumber.listener.Reporter.addStepLog(message);
	}
	
	public static void logSuccess(String message){
		logger.info(message);
		com.cucumber.listener.Reporter.addStepLog(message);
	}
	
	public static void logError(String message){
		logger.error(message);
		com.cucumber.listener.Reporter.addStepLog(message);
				
	}
	public static void logVariable(String message){
		logger.info(message);
		com.cucumber.listener.Reporter.addStepLog(message);
	}
	
	public static void logStep(String message){
		logger.info(message);
		com.cucumber.listener.Reporter.addStepLog(message);
	}	
	
	public static void logBlankLine(){
		com.cucumber.listener.Reporter.addStepLog("\n");
	}
	
	
}
