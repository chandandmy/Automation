Feature: Validating Place API's


@mapApiTest
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>""<language>""<address>""<phone>""<website>""<type1>""<type2>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name 	           | language  |address		                                  | phone              | website                                              |   type1             | type2   |
	|chandan        |  English    |SriLaxmi pg Bangalore     |  9856734526 |https://www.chandan.com        |   pg                   |home    |
	|Ayyamuthu  | Tamil         |mahabalipuram Chennai  | 9790221002  |https://www.ayyamuthu.com  |  own-house   | home   |
	|Srinivasan    | Telegu       |Hyderabad                              | 9790221002  |https://www.srinivasan.com    |  shoe park      | shop   |


@mapApiTest
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	 


	

	
	
	
	
	
	

	
	
	