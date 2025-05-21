package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException
	{
		if(StepDefinitions.place_id==null)
		{
		StepDefinitions sd= new StepDefinitions();
		sd.add_place_payload_with("rakshi", "hindi", "blr");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_with("rakshi", "GetPlaceAPI");
		}
	}

}
