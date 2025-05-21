package stepDefinitions;

import static io.restassured.RestAssured.given;

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
import resourses.TestDataBuild;

public class StepDefinitons {
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	@Given("Add place payload")
	public void add_place_payload() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		

		

		 RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		 
		 
		 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 res=given().spec(req)
		.body(data.addPlacePayload());
	}
	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
		 response =res.when().post("/maps/api/place/add/json").
				then().spec(resspec).extract().response();
	}
	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
	   
		assertEquals(response.getStatusCode(),200);
		
	  
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    String resp= response.asString();
	    JsonPath js= new JsonPath(resp);
	    assertEquals(js.get(key).toString(),value);
	    System.out.println(resp);
	}


}
