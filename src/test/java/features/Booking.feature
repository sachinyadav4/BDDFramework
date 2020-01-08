Feature: Flight booking in Spicejet 
	As a valid user
    I should be able to select various options and passengers while booking a flight in Spicejet
  
@smoke 
Scenario: Verify hightest fair and no. of adults while booking a flight 
	Given I am on home page 
	Then one way trip is selected by default and return date is disabled 
	When I select roundtrip 
	And Select the Departure City as "HYD" and Arrival city as "BLR" 
	And Select the adults as "2" 
	And Select senior citizen checkbox 
	And Click on Search button 
	Then Flights availability table is displayed 
	When I select the costliest flights for both journey and assert if fare details are updated 
	Then Fare details are updated 
	When I accept senior citizen T&C and click on continue 
	Then there is no change in fare price and no. of adults are "2"