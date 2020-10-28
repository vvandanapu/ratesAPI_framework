package com.ratesAPI.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import com.ratesAPI.logger.testLogger;


/**
 * Class Description - This class consists of methods that are required for
 * handling REST API request/response
 * 
 * @author VijayVandanapu
 * 
 */
public class restAPIRequest {

	// Property of request
	String _reqDesc; // for description of the request, e.g. "Describe key"
	String _reqURL; // for URL of request (Required)
	
	String _reqMethod; // for Method of request, e.g. POST, DELETE (Required)
	

	// Property of response
	String _responseStatusCode; // for response statuscode
	String _responseStatusLine; // for response statusline
	String _responseString; // for response string
	String currentDate;
	
	HashMap<String, String> _responseHeaders; // for response headers
	byte[] _responseBody;

	// Enable or disable the logging for the request
	protected Boolean _logFlag;

	/**
	 * This method is for constructing an empty <code>RestAPIRequest</code> with
	 * default settings
	 * 
	 */
	public restAPIRequest() {
		_reqDesc = "General REST API request";
		_reqURL = null;
		_reqMethod = null;
		_responseStatusCode = null;
		_responseStatusLine = null;
		_responseString = null;
		_responseHeaders = new HashMap<String, String>();
		
	}

	

	/**
	 * This method is for setting description for REST request
	 * 
	 * @param desc
	 *            Description <code>String</code> for REST request e.g. Describe
	 *            a key, Delete an IP etc. You can use it to show the purpose of
	 *            this request.
	 * 
	 */
	public void setDescription(String desc) {
		_reqDesc = desc.trim();
	}

	/**
	 * This method is for getting description from REST request
	 * 
	 * @return <code>String</code>
	 * 
	 */
	public String getDescription() {
		return _reqDesc;
	}

	/**
	 * This method is for setting URL for REST request
	 * 
	 * @param url
	 *            URL <code>String</code> for REST request e.g.
	 *            https://....../keys etc
	 * 
	 */
	public void setURL(String url) {
		_reqURL = url.trim();
	}

	/**
	 * This method is for getting URL from REST request
	 * 
	 * @return <code>String</code>
	 * 
	 */
	public String getURL() {
		return _reqURL;
	}

	/**
	 * This method is for setting Method for REST request
	 * 
	 * @param method
	 *            Method <code>String</code> for REST request e.g. GET, PUT,
	 *            POST, DELETE etc
	 * 
	 */
	public void setMethod(String method) {
		_reqMethod = method.trim();
	}

	/**
	 * This method is for getting Method from REST request
	 * 
	 * @return <code>String</code>
	 * 
	 */
	public String getMethod() {
		return _reqMethod;
	}
	

	/**
	 * This method is for submitting the REST request (Request/Response detail
	 * will be logged in report)
	 *
	 * 
	 */
	public void submit() {
		//Print the REST request details in the log report

			testLogger.logBlankLine();
			testLogger.logInfo("Request Description: " + _reqDesc);
			testLogger.logInfo("Submitting Rest API request with configurations below: ");
			testLogger.logInfo("URL: " + _reqURL);
			testLogger.logInfo("Method: " + _reqMethod);
			
			//Initialize the REST request instance via RestAssured API
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder(); 
		RequestSpecification requestSpec = reqBuilder.build();
		requestSpec.authentication().none();

			//Submit the request
			Response response = null;
			ResponseSpecification responseSpecification = RestAssured.given().spec(requestSpec).expect();

			if (_reqMethod.trim().equalsIgnoreCase("GET")) {
				response = responseSpecification.get(_reqURL);
			} else if (_reqMethod.trim().equalsIgnoreCase("POST")) {
				response = responseSpecification.post(_reqURL);
			} else if (_reqMethod.trim().equalsIgnoreCase("DELETE")) {
				response = responseSpecification.delete(_reqURL);
			} else if (_reqMethod.trim().equalsIgnoreCase("PUT")) {
				response = responseSpecification.put(_reqURL);
			}
			
			//Retrieve the response content
			if (response != null) {
				_responseString = response.asString();
				_responseStatusCode = Integer.toString(response.getStatusCode());
				_responseStatusLine = response.getStatusLine();
				
				
				_responseBody =response.asByteArray();
				Iterator<Header>  itr = response.getHeaders().iterator();
				while(itr.hasNext()){
					Header header = itr.next();
					_responseHeaders.put(header.getName(), header.getValue());
				}
				testLogger.logInfo("Request submitted successful, response detail as below:");
				testLogger.logInfo("StatusCode: " + _responseStatusCode);
				testLogger.logInfo("StatusLine: " + _responseStatusLine);
				testLogger.logInfo("ResponseHeaders: " + _responseHeaders);
				testLogger.logInfo("ResponseString: " + _responseString);
			
		}
		
}	/**
	 * This method is for getting response status code
	 * 
	 * @return <code>String</code>
	 */
	public String getResponseStatusCode() {
		return _responseStatusCode;
	}

	/**
	 * This method is for getting response status line
	 * 
	 * @return <code>String</code>
	 */
	public String getResponseStatusLine() {
		return _responseStatusLine;
	}

	/**
	 * This method is for getting response string
	 * 
	 * @return <code>String</code>
	 */
	public String getResponseString() {
		return _responseString;
	}

	/**
	 * This method is for getting response header value by providing the
	 * response header name
	 * 
	 * @param headerName
	 *            It is the name of the header that needs to be retrieved
	 * 
	 * @return <code>String</code>
	 */
	public String getResponseHeader(String headerName) {
		return _responseHeaders.get(headerName);
	}

	/**
	 * This method is for getting all the response headers as key-value pair
	 * 
	 * @return <code>HashMap<String, String></code>
	 */
	public HashMap<String, String> getResponseHeaders() {
		return _responseHeaders;
	}

	/**
	 * This method is for getting response(json/xml) value by specifying
	 * customized dataPath
	 * 
	 * @param dataPath
	 *            <code>String</code> of datapath e.g.
	 *            "addreses.price.currencyCode" ,
	 *            "addresses.find {addresses -> addresses.id == '10000957'}.location"
	 *            etc.
	 * @see reference
	 *      http://rest-assured.googlecode.com/svn/tags/1.7.2/apidocs/com
	 *      /jayway/restassured/path/json/JsonPath.html
	 * @see reference
	 *      http://rest-assured.googlecode.com/svn/tags/1.7.2/apidocs/com
	 *      /jayway/restassured/path/xml/XmlPath.html
	 * 
	 * @return <code>Object</code> The return type is <code>Object</code>,
	 *         because it may return <code>String</code>, <code>Integer</code>,
	 *         <code>List<*></code>... based on query result for specified
	 *         datapath. User need to cast it into actual types
	 * 
	 */
	public Object getResponseValueByPath(String dataPath) {

		if (_responseString == null || _responseString.trim().equals("")) {
			testLogger.logError("Response String is null");
			return null;
		}
		if (dataPath == null || dataPath.trim().equals("")) {
			testLogger.logError("Provided dataPath String is null");
			return null;
		}

		try {
			if (!(_responseHeaders.get("Content-Type") == null)) {
				if (_responseHeaders.get("Content-Type").trim()
						.equalsIgnoreCase("application/json")) {
					JsonPath jsonPath = new JsonPath(_responseString);
					return jsonPath.get(dataPath);
				} 
				else {
					testLogger.logError("Accept type for the request is unknown or incorrect, hence it is processed as application/xml by default");
					XmlPath xmlPath = new XmlPath(_responseString);
					return xmlPath.get(dataPath);
				}
			} else {
				
				return "Content-Type header is null";
			}

		} catch (Exception e) {
			testLogger.logError("Failed to get response value for specified datapath : "
					+ dataPath);
			testLogger.logError("Error happens: " + e.toString());
			return null;
		}
	}

	
	/**
	 * This method is for getting response(json/xml) node string value by
	 * specifying customized dataPath
	 * 
	 * @param dataPath
	 *            <code>String</code> of datapath e.g.
	 *            "addreses.price.currencyCode" etc. User should ensure the
	 *            dataPath only return <code>String</code> result, or will get
	 *            error exception
	 * 
	 * @see reference 
	 *      http://rest-assured.googlecode.com/svn/tags/1.7.2/apidocs/com
	 *      /jayway/restassured/path/json/JsonPath.html
	 * 
	 * @return <code>String</code>
	 * 
	 */
	public String getResponseNodeStringByPath(String dataPath) {
		Object result = getResponseValueByPath(dataPath);
		String returnString = null;
		if (result == null)
			return returnString;
		if (result instanceof Boolean) {
			if ((Boolean) result)
				returnString = "true";
			else
				returnString = "false";
		} else
			returnString = result.toString();
		return returnString;
	}


     public String currentDate() {
    	 
    	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	 Date date = new Date();
    	 currentDate=formatter.format(date);
    	 return  currentDate;
     }
    
}
