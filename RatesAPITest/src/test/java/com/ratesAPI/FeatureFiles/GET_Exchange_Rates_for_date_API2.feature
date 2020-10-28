#Author: Vijay Vandanapu
@GET_Exchange_rates_for_date
Feature: GET Exchange rates for particular date
As an individual, He/she must be able to fetch the Exchange rates for EUR currency using appropriate date


Scenario: GET_Exchange_Rates_Date_01 : Verification of the http response code when user requests for GET Exchange rates for appropriate date
			Given User has the GET Exchange rates API endpoint
			When User invokes GET Exchange rates API endpoint with appropriate date
			|Date|
			|2019-09-20|
			Then Verify that Http response code is 200 OK

Scenario: GET_Exchange_Rates_Date_02 : Verification of the Date value in the response which should match with the requested date value when user requests for GET Exchange rates for appropriate date
			Given User has the GET Exchange rates API endpoint
			When User invokes GET Exchange rates API endpoint with appropriate date
			|Date|
			|2019-09-20|
			Then Verify Date value in the Exchange rates API response matches the date value in the request
			
Scenario: GET_Exchange_Rates_Date_03 : To verify exchange rate is available for few major countries as per the requirement when user requests for GET Exchange rates for appropriate date
			Given User has the GET Exchange rates API endpoint
			When User invokes GET Exchange rates API endpoint with appropriate date
			|Date|
			|2019-09-20|
			Then Verify that exchange rates are available for major countries as per the requirement in the API response
			|GBP|INR|SGD|USD|AUD|ZAR|		 

Scenario: GET_Exchange_Rates_Date_04 : Verification of the http response code when user requests for GET Exchange rates for appropriate date using invalid endpoint
			Given User has the invalid GET Exchange rates API endpoint
			When User invokes invalid GET Exchange rates API endpoint with appropriate date
			|Date|
			|2019-09-20|
			Then Verify that Http response code is 404 Not Found

Scenario: GET_Exchange_Rates_Date_05 : Verification of the http response code when user requests for GET Exchange rates for appropriate date using invalid date format
			Given User has the GET Exchange rates API endpoint
			When User invokes GET Exchange rates API endpoint with appropriate date
			|Date|
			|22-09-2020|
			Then Verify that Http response code is 400 Bad Request
			And Verify the error message in the response		

Scenario: GET_Exchange_Rates_Date_06 : Verification of the http response code when user requests for GET Exchange rates without a date in the request
			Given User has the GET Exchange rates API endpoint
			When User invokes GET Exchange rates API endpoint with appropriate date
			|Date|
			||
			Then Verify that Http response code is 400 Bad Request
			And Verify the error message in the response			
	