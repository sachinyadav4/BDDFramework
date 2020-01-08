package stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.LandingPage;
import resources.base;

public class StepDefinition extends base {

	LandingPage landingPage;

	@Before
	public void setUp() throws IOException {
		initializeDriver();
	}

	@Given("^I am on home page$")
	public void i_am_on_home_page() {
		openApplication();
	}

	@Then("^one way trip is selected by default and return date is disabled$")
	public void one_way_trip_is_selected_by_default_and_return_date_is_disabled() {

		landingPage = new LandingPage(driver);
		Assert.assertTrue(landingPage.verifyOneWayOptionSelection());
		Assert.assertFalse(landingPage.verifyReturnDateStatus());
	}

	@When("^I select roundtrip$")
	public void i_select_roundtrip() {
		landingPage.clickRoundTripOption();

	}

	@When("^Select the Departure City as \"([^\"]*)\" and Arrival city as \"([^\"]*)\"$")
	public void select_the_Departure_City_as_and_Arrival_city_as(String depatureCity, String arrivalCity) {
		landingPage.selectDepartureCity(depatureCity);
		landingPage.selectArrivalCity(arrivalCity);
		landingPage.dateSelection();
	}

	@When("^Select the adults as \"([^\"]*)\"$")
	public void select_the_adults_as(String noOfAdults) {
		landingPage.selectNoOfAdults(noOfAdults);
	}

	@When("^Select senior citizen checkbox$")
	public void select_senior_citizen_checkbox() {
		landingPage.selectSeniorCitizens();
	}

	@When("^Click on Search button$")
	public void click_on_Search_button() {
		landingPage.clickOnSearchButton();
	}

	@Then("^Flights availability table is displayed$")
	public void flights_availability_table_is_displayed() {
		landingPage.setOldAmount();
	}

	@When("^I select the costliest flights for both journey and assert if fare details are updated$")
	public void i_select_the_costliest_flights_for_both_journey_and_assert_if_fare_details_are_updated() throws InterruptedException {
		
		landingPage.selectDepartureFlighHightestPrice();
		landingPage.selectArrivalFlighHightestPrice();
		Thread.sleep(5000L);
		landingPage.setSelectedHighPriceDepart();
		landingPage.setSelectedHighPriceArrival();
	}

	@Then("^Fare details are updated$")
	public void fare_details_are_updated() {
		landingPage.setUpdatedAmount();
		Assert.assertFalse(landingPage.getOldAmount().equalsIgnoreCase(landingPage.getUpdatedAmount()));
	}

	@When("^I accept senior citizen T&C and click on continue$")
	public void i_accept_senior_citizen_T_C_and_click_on_continue() {
		landingPage.clickSeniorCitizenTC();
		landingPage.clickContinueButton();
	}

	@Then("^there is no change in fare price and no\\. of adults are \"([^\"]*)\"$")
	public void there_is_no_change_in_fare_price_and_no_of_passengers(int noOfAdults) {
		Assert.assertEquals(noOfAdults, landingPage.verifyAdultPassengerInFormPresent(noOfAdults));
		Assert.assertTrue(landingPage.getSelectedHighPriceDepart().equalsIgnoreCase(landingPage.getTotalDueDepartureAmt()));
		Assert.assertTrue(landingPage.getSelectedHighPriceArrival().equalsIgnoreCase(landingPage.getTotalDueArrivalAmt()));
	}
}
