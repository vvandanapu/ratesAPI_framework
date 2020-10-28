package com.ratesAPI.core;

import com.ratesAPI.logger.testLogger;
import com.ratesAPI.logger.testVerification;




/**
 * Class Description - It is the framework base class. Every testcase must extend this class.
 * 
 * @author Vijay Vandanapu
 * 
 */
public class testBase {
	 
	public restAPIRequest restRequest = new restAPIRequest();
	public testVerification testVP = new testVerification();
	public testLogger testLogger = new testLogger();

	
}

