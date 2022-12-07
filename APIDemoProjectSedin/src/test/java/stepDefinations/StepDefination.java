package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	

@Given("Add Place Payload with {string}{string}{string}{string}{string}{string}{string}")
public void add_Place_Payload_with(String name, String language, String address, String ph, String web, String type1, String type2) throws IOException {
	requestSpec=given().spec(requestSpecification())
		.body(data.addPlacePayLoad(name,language,address,ph,web,type1,type2));
	}

@When("user calls {string} with {string} http request")
public void user_calls_with_http_request(String resource, String method) throws IOException {
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());	
		
		String localDir = System.getProperty("user.dir");
		File file = new File(localDir + getGlobalValue("jsonSchema_File"));
		responseSpec =new ResponseSpecBuilder().expectBody(JsonSchemaValidator.matchesJsonSchema(file)).expectStatusCode(200).expectContentType(ContentType.JSON).build();
		//responseSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if(method.equalsIgnoreCase("POST"))
		 response =requestSpec.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			 response =requestSpec.when().get(resourceAPI.getResource());
		
}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		
	
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {	  
	 assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
		     place_id=getJsonPath(response,"place_id");		     
		     requestSpec=given().spec(requestSpecification()).queryParam("place_id",place_id);
		 user_calls_with_http_request(resource,"GET");
		  String actualName=getJsonPath(response,"name");
		  assertEquals(actualName,expectedName);	    
	}
	
@Given("DeletePlace Payload")
public void deleteplace_Payload() throws IOException {   	
	requestSpec =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	
}



}
