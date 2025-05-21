package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.GetLocation;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	ResponseSpecification resspec;
	RequestSpecification reqspec;
	Response response;
	TestDataBuild data= new TestDataBuild();
	static String place_id;
	
	@Given("Add place payload with {string}, {string}, {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
		reqspec=given().spec(requestSpecifications())
				.body(data.addPlacePayload(name,address,language));
	}
		 
		 
		
		 
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		 APIResources resAPI=APIResources.valueOf(resource);
		 System.out.println(resAPI.getResource());
		 
		 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 if(method.equalsIgnoreCase("POST"))
		 {
		 response =reqspec.when().post(resAPI.getResource());
		 }
		 
		 else if(method.equalsIgnoreCase("GET"))
		 {
			 response =reqspec.when().get(resAPI.getResource());
		 }
				
	}
	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
	   
		assertEquals(response.getStatusCode(),200);
		
	  
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    
	    assertEquals(getJsonPath(response,key),value);
	}
	
	@Then("Verify place_id created maps to {string} with {string}")
	public void verify_place_id_created_maps_to_with(String expectedName, String resource) throws IOException {
	    
		place_id= getJsonPath(response,"place_id");
		reqspec=given().spec(requestSpecifications()).queryParam("place_id",place_id);
		
		user_calls_with_http_request(resource,"GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}
	
	@Given("Delete place with payload")
	public void delete_place_with_payload() throws IOException {
	    
		reqspec= given().spec(requestSpecifications()).body(data.deletePlacePayload(place_id));
		
	}



}
