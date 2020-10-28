package com.ratesAPI.stepDefinition;
import java.util.List;

import com.ratesAPI.core.testBase;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ratesAPI extends testBase 

{
	public static String ratesAPI_baseurl="https://api.ratesapi.io/api/";
	public static String request_date=null;
	public static String ratesAPI_Invalid_baseurl="https://api.ratesapi.io/ap/";
	
	
	
	
	@Given("^User has the GET Exchange rates API endpoint$")
	public void user_has_the_GET_Exchange_rates_API_endpoint() throws Throwable {
	    testLogger.logInfo("User has the valid rates API endpoint which is "+ratesAPI_baseurl);
	}

	@When("^User invokes GET Exchange rates API endpoint with appropriate date$")
	public void user_invokes_GET_Exchange_rates_API_endpoint_with_appropriate_date(DataTable arg1) throws Throwable {
		  List<List<String>> data=arg1.raw();
		  request_date= data.get(1).get(0);
		testLogger.logStep("Get Exchange rates using date");	
		restRequest.setURL(ratesAPI_baseurl+request_date);
		restRequest.setMethod("GET");
		restRequest.submit();
	}

	@Then("^Verify that Http response code is 200 OK$")
	public void verify_that_Http_response_code_is_OK() throws Throwable {
		testVP.assertStringEquals(String.valueOf(restRequest.getResponseStatusCode()),"200", 
				"Response Code is 200 OK for this request");
	}

	@Then("^Verify Date value in the Exchange rates API response matches the date value in the request$")
	public void verify_Date_value_in_the_Exchange_rates_API_response_matches_the_date_value_in_the_request() throws Throwable {
		testVP.assertStringEquals(restRequest.getResponseNodeStringByPath("date"),request_date, 
				"Date value in the response is matching with the requested date");
	}

	@Then("^Verify that exchange rates are available for major countries as per the requirement in the API response$")
	public void verify_that_exchange_rates_are_available_for_major_countries_as_per_the_requirement_in_the_API_response(DataTable arg1) throws Throwable {
		List<List<String>> data=arg1.raw();
		String uk_Code=data.get(0).get(0);
		String india_Code=data.get(0).get(1);
		String singapore_Code=data.get(0).get(1);
		String us_Code=data.get(0).get(3);
		String australia_Code=data.get(0).get(4);
		String southAfrica_Code=data.get(0).get(5);
		testVP.assertTrue(!restRequest.getResponseNodeStringByPath("rates."+uk_Code).isEmpty(), "Exchange rate is available for UK currency in the response");
		testVP.assertTrue(!restRequest.getResponseNodeStringByPath("rates."+india_Code).isEmpty(), "Exchange rate is available for India currency in the response");
		testVP.assertTrue(!restRequest.getResponseNodeStringByPath("rates."+singapore_Code).isEmpty(), "Exchange rate is available for Singapore currency in the response");
		testVP.assertTrue(!restRequest.getResponseNodeStringByPath("rates."+us_Code).isEmpty(), "Exchange rate is available for US currency in the response");
		testVP.assertTrue(!restRequest.getResponseNodeStringByPath("rates."+australia_Code).isEmpty(), "Exchange rate is available for Australia currency in the response");
		testVP.assertTrue(!restRequest.getResponseNodeStringByPath("rates."+southAfrica_Code).isEmpty(), "Exchange rate is available for South Africa currency in the response");
	}

	@Given("^User has the invalid GET Exchange rates API endpoint$")
	public void user_has_the_invalid_GET_Exchange_rates_API_endpoint() throws Throwable {
		testLogger.logInfo("User has the invalid rates API endpoint which is "+ratesAPI_Invalid_baseurl);
	}

	@When("^User invokes invalid GET Exchange rates API endpoint with appropriate date$")
	public void user_invokes_invalid_GET_Exchange_rates_API_endpoint_with_appropriate_date(DataTable arg1) throws Throwable {
		List<List<String>> data=arg1.raw();
		request_date= data.get(1).get(0);
		testLogger.logStep("Get Exchange rates using date");	
		restRequest.setURL(ratesAPI_Invalid_baseurl+request_date);
		restRequest.setMethod("GET");
		restRequest.submit();
	}

	@Then("^Verify that Http response code is 400 Bad Request$")
	public void verify_that_Http_response_code_is_Bad_Request() throws Throwable {
		testVP.assertStringEquals(String.valueOf(restRequest.getResponseStatusCode()),"400", 
				"Response Code is 400 Bad Request for this request");
	}

	
	@Then("^Verify that Http response code is 404 Not Found$")
	public void verify_that_Http_response_code_is_Not_Found() throws Throwable {
		testVP.assertStringEquals(String.valueOf(restRequest.getResponseStatusCode()),"404", 
				"Response Code is 404 Not Found for this request");
	}

	@Then("^Verify the error message in the response$")
	public void verify_the_error_message_in_the_response() throws Throwable {
		testVP.assertTrue(!restRequest.getResponseNodeStringByPath("error").isEmpty(), "Exchange rate is available for South Africa currency in the response");
	}

	@Given("^User has the GET latest Exchange rates API endpoint$")
	public void user_has_the_GET_latest_Exchange_rates_API_endpoint() throws Throwable {
		testLogger.logInfo("User has the valid rates API endpoint which is "+ratesAPI_baseurl+"latest");
	}

	@When("^User invokes GET latest Exchange rates API endpoint$")
	public void user_invokes_GET_latest_Exchange_rates_API_endpoint() throws Throwable {
		testLogger.logStep("Get latest Exchange rates");	
		restRequest.setURL(ratesAPI_baseurl+"latest");
		restRequest.setMethod("GET");
		restRequest.submit();
	}

	@Then("^Verify Date value in the Exchange rates API response matches current date$")
	public void verify_Date_value_in_the_Exchange_rates_API_response_matches_current_date() throws Throwable {
		testVP.assertStringEquals(restRequest.getResponseNodeStringByPath("date"),restRequest.currentDate(), 
				"Date value in the response is matching with the requested date");
	}

	@Given("^User has the invalid GET latest Exchange rates API endpoint$")
	public void user_has_the_invalid_GET_latest_Exchange_rates_API_endpoint() throws Throwable {
		testLogger.logInfo("User has the invalid rates API endpoint which is "+ratesAPI_Invalid_baseurl);
	}

	@When("^User invokes invalid GET latest Exchange rates API endpoint$")
	public void user_invokes_invalid_GET_latest_Exchange_rates_API_endpoint() throws Throwable {
		testLogger.logStep("Get latest Exchange rates");	
		restRequest.setURL(ratesAPI_Invalid_baseurl+"latest");
		restRequest.setMethod("GET");
		restRequest.submit();
	}



}
