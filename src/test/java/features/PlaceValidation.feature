Feature: Validating place API
@AddPlace
Scenario Outline: Verify if place is successfully added using placeAPI
Given Add place payload with "<name>", "<address>", "<language>"
When user calls "AddPlaceAPI" with "POST" http request
Then API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"

#=======
And Verify place_id created maps to "<name>" with "GetPlaceAPI"
Examples:
|name       |address |language |
|SaiGanesh  |Banglore|Telugu   |
|Hithaishini|KGF     |Kannada  |
|Rakshi     |BEML    |Hindi    |


@DeletePlace
Scenario:

Given Delete place with payload
When user calls "DeletePlaceAPI" with "POST" http request
Then API call got success with status code 200
And "status" in response body is "OK"

#>>>>>>> branch 'master' of https://github.com/saiganesh1311/RestCucumber


