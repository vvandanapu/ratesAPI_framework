package com.ratesAPI.logger;

import org.junit.Assert;

/**
 *  Class Description - This provides mechanism to perform verification check using Assert function.
 *  
 * @author Vijay Vandanapu
 *
 */
public class testVerification {

	public boolean testResult;

	public testVerification()
	{
		testResult = true;
	}
	
	public void testResultFinalize() 
	{
		if (!testResult) 
		{
			Assert.fail("Test failed due to some VP failure, please check error message in the detail log above.");
		}
	}

	/*
	 * In assert methods, if VP condition is false, the testcase will fail and
	 * stop at once. Exception is thrown to notify TestNG.
	 */
	public void assertTrue(boolean condition, String msg)
			throws Throwable {
		try {
			Assert.assertTrue(msg, condition);
			testLogger.logSuccess("[VP Pass] : " + msg);
		} catch (Throwable e) {
			testLogger.logError("[VP Fail] : " + "Test case failed");
			throw e;
		}
	}
	
	public void assertTrue(boolean condition)throws Throwable 
	{
     try {
            Assert.assertTrue(condition);
            testLogger.logSuccess("[VP Pass] : ");
     	  } 
     catch (Throwable e) 
     	  {
	 		  testLogger.logError("[VP Fail] : " + "Test case failed");
	     
	        throw e;
     	  }
	}

	public void assertFalse(boolean condition)throws Throwable 
	{
		try {
			Assert.assertFalse(condition);
			testLogger.logSuccess("[VP Pass] : ");
			} 
		catch (Throwable e) 
			{
				testLogger.logError("[VP Fail] : " + "Test case failed");
				throw e;
			}
	}

	public void assertFalse(boolean condition, String msg)throws Throwable 
	{
		try {
			Assert.assertFalse(msg, condition);
			testLogger.logSuccess("[VP Pass] : " + msg);
			} 
		catch (Throwable e) 
		   {
				testLogger.logError("[VP Fail] : "  + "Test case failed");
				throw e;
		    }
	}
	
	public void assertStringEquals(String actual, String expected, String msg)throws Throwable 
	{
		try 
		{
			Assert.assertEquals(expected, actual);
			testLogger.logSuccess("[VP Pass] : " + msg + " [ Expected: "+ expected + ", Actual: " + actual + "]");
		} 
		catch (Throwable e) 
		{
			if (actual == null)
				actual = "NULL";
			if (expected == null)
				expected = "NULL";
			testLogger.logError("[VP Fail] : "  + "Test case failed" + " [Expected: "
					+ expected + ", Actual: " + actual + "]");
			throw e;
		}
	}

	public void assertEquals(Object actual, Object expected, String msg)throws Throwable 
	{
		try 
		{
			Assert.assertEquals(msg, expected, actual);
			testLogger.logSuccess("[VP Pass] : " + msg);
		} 
		catch (Throwable e) {
			testLogger.logError("[VP Fail] : "  + "Test case failed");
			throw e;
		}
	}

	
}
