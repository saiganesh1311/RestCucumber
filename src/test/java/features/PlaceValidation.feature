Feature: Validating place API
Scenario: Verify if place is successfully added using placeAPI

Given Add place payload
When user calls "AddPlaceAPI" with post http request
Then API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"


