#Author: Vijay Vandanapu
@GET_Latest_Exchange_Rates
Feature: GET latest Exchange rates
As an individual, He/she must be able to fetch the latest Exchange rates for EUR currency


Scenario: GET_Exchange_Rates_Date_01 : Verification of the http response code when user requests for GET latest Exchange rates
			Given User has the GET latest Exchange rates API endpoint
			When User invokes GET latest Exchange rates API endpoint
			Then Verify that Http response code is 200 OK

Scenario: GET_Exchange_Rates_Date_02 : Verification of the Date value in the response which should match with the requested date value when user requests for GET latest Exchange rates
			Given User has the GET latest Exchange rates API endpoint
			When User invokes GET latest Exchange rates API endpoint
			Then Verify Date value in the Exchange rates API response matches current date
			
Scenario: GET_Exchange_Rates_Date_03 : To verify exchange rate is available for few major countries as per the requirement when user requests for GET latest Exchange rates
			Given User has the GET latest Exchange rates API endpoint
			When User invokes GET latest Exchange rates API endpoint
			Then Verify that exchange rates are available for major countries as per the requirement in the API response
			|GBP|INR|SGD|USD|AUD|ZAR|		 

Scenario: GET_Exchange_Rates_Date_04 : Verification of the http response code when user requests for GET latest Exchange rates using invalid endpoint
			Given User has the invalid GET latest Exchange rates API endpoint
			When User invokes invalid GET latest Exchange rates API endpoint
			Then Verify that Http response code is 404 Not Found

		

			
	