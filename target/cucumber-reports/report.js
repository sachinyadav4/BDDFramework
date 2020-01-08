$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Booking.feature");
formatter.feature({
  "line": 1,
  "name": "Flight booking in Spicejet",
  "description": "As a valid user\r\n  I should be able to select various options and passengers while booking a flight in Spicejet",
  "id": "flight-booking-in-spicejet",
  "keyword": "Feature"
});
formatter.before({
  "duration": 16813500101,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Verify hightest fair and no. of adults while booking a flight",
  "description": "",
  "id": "flight-booking-in-spicejet;verify-hightest-fair-and-no.-of-adults-while-booking-a-flight",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 5,
      "name": "@smoke"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "I am on home page",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "one way trip is selected by default and return date is disabled",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "I select roundtrip",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "Select the Departure City as \"HYD\" and Arrival city as \"BLR\"",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Select the adults as \"2\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Select senior citizen checkbox",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "Click on Search button",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "Flights availability table is displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "I select the costliest flights for both journey and assert if fare details are updated",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "Fare details are updated",
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "I accept senior citizen T\u0026C and click on continue",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "there is no change in fare price and no. of adults are \"2\"",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDefinition.i_am_on_home_page()"
});
formatter.result({
  "duration": 7237035600,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.one_way_trip_is_selected_by_default_and_return_date_is_disabled()"
});
formatter.result({
  "duration": 3091180500,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.i_select_roundtrip()"
});
formatter.result({
  "duration": 776753400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "HYD",
      "offset": 30
    },
    {
      "val": "BLR",
      "offset": 56
    }
  ],
  "location": "StepDefinition.select_the_Departure_City_as_and_Arrival_city_as(String,String)"
});
formatter.result({
  "duration": 1231761500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 22
    }
  ],
  "location": "StepDefinition.select_the_adults_as(String)"
});
formatter.result({
  "duration": 1000400701,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.select_senior_citizen_checkbox()"
});
formatter.result({
  "duration": 128324000,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.click_on_Search_button()"
});
formatter.result({
  "duration": 6509991300,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.flights_availability_table_is_displayed()"
});
formatter.result({
  "duration": 400083600,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.i_select_the_costliest_flights_for_both_journey_and_assert_if_fare_details_are_updated()"
});
formatter.result({
  "duration": 5796165600,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.fare_details_are_updated()"
});
formatter.result({
  "duration": 30523301,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.i_accept_senior_citizen_T_C_and_click_on_continue()"
});
formatter.result({
  "duration": 6948399400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 56
    }
  ],
  "location": "StepDefinition.there_is_no_change_in_fare_price_and_no_of_passengers(int)"
});
formatter.result({
  "duration": 342738899,
  "status": "passed"
});
});