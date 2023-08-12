package StepDefinations;

import Resources.APIResources;
import Resources.TestData;
import static org.junit.Assert.*;
import Resources.Utils;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StepDefination extends Utils{
	RequestSpecification request;
	TestData data=new TestData();
	ResponseSpecification responsespc;
	 Response reponse;
		static String placeid;
	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		request=given().spec(requestSpecfication())
				.body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		//from APIResource class create on enum class of read all resources
		APIResources resourcesAPI= APIResources.valueOf(resource);
		System.out.println("resourcesAPI:"+resourcesAPI.getResource());
		 responsespc = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		if(method.equalsIgnoreCase("POST"))
		 reponse=request.when().post(resourcesAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			 reponse=request.when().post(resourcesAPI.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(reponse.getStatusCode(),200);
		System.out.println("Statuscode verify...!!");
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String Expectedvalue) throws IOException {
		assertEquals(getJsonpath(reponse,keyvalue), Expectedvalue);
		System.out.println("Response matched..!!");
		
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	 placeid = getJsonpath(reponse, "place_id");
	 request.given().spec(requestSpecfication()).queryParam("place_id", placeid);
	 user_calls_with_http_request(resource, "GET");
	String actualname = getJsonpath(reponse, "name");
	System.out.println(actualname+"-->"+expectedName);
	assertEquals(actualname, expectedName);
	 
	 
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		request=given().spec(requestSpecfication()).body(data.deletePlacePayload(placeid));
		System.out.println("SuccessFul deleted...!!");
	}

}
