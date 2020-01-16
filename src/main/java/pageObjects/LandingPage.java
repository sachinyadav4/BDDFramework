package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import resources.base;

public class LandingPage extends base {

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	static String departureTable = "//*[@id='availabilityTable0']";
	static String arrivalTable = "//*[@id='availabilityTable1']";

	static String oldPrice;
	static String updatedPrice;
	
	static String selectedHighPriceDepart;
	static String selectedHighPriceArrival;
	
	static String duePriceDepart;
	static String duePriceArrival;

	static String adultFormStartXpath = "//*[@id='passengerInputContainer";
	static String adultFormEndXpath = "']/div/div/div[1]";

	@FindBy(id = "ctl00_mainContent_rbtnl_Trip_0")
	private WebElement oneWayRadioButton;

	@FindBy(id = "Div1")
	private WebElement returnDate;

	@FindBy(id = "ctl00_mainContent_rbtnl_Trip_1")
	private WebElement roundTripRadioButton;

	@FindBy(id = "ctl00_mainContent_ddl_originStation1_CTXT")
	private WebElement sourceInput;

	@FindBy(id = "ctl00_mainContent_ddl_destinationStation1_CTXT")
	private WebElement destinationInput;

	@FindBy(xpath = "//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='BLR']")
	private WebElement destLocSelection;

	@FindBy(xpath = "//td[contains(@class,'ui-datepicker-today')]/following-sibling::td[1]/a")
	private WebElement startDate;

	@FindBy(id = "divpaxinfo")
	private WebElement adultDropDown;

	@FindBy(id = "ctl00_mainContent_ddl_Adult")
	private WebElement adultListSelection;

	@FindBy(id = "ctl00_mainContent_chk_SeniorCitizenDiscount")
	private WebElement chkboxSeniorCitizen;

	@FindBy(id = "ctl00_mainContent_btn_FindFlights")
	private WebElement searchButton;

	@FindBy(id = "ControlGroupSelectView_AvailabilityInputSelectView_SeniorCitizen")
	private WebElement chkbxTermsAndCondtions;

	@FindBy(id = "spanamnt")
	private WebElement totalAmount;

	@FindBy(xpath = "//*[@id='continue-to-contact-page']/div/span[1]")
	private WebElement continueButton;

	@FindBy(xpath = "//div[@classname = 'guest-heading']")
	private WebElement adultHeading;
	
	@FindBy(xpath = "//*[@id='totalDue']")
	private WebElement totalDue;
	
	@FindBy(xpath = "//*[@id='taxAndFeeInclusiveDivBody']/div[3]/h4[1]/span")
	private WebElement totalDepartureAmt;
	
	@FindBy(xpath = "//*[@id='taxAndFeeInclusiveDivBody']/div[3]/h4[2]/span")
	private WebElement totalArrivalAmt;
	
	@FindBy(xpath = "//div[@class='priceSummaryContainer'][1] //*[@id='PriceTable']/tbody/tr[3]/td[1]/h4/span")
	private WebElement totalDueDepartureAmt;
	
	@FindBy(xpath = "//div[@class='priceSummaryContainer'][2] //*[@id='PriceTable']/tbody/tr[3]/td[1]/h4/span")
	private WebElement totalDueArrivalAmt;

	public boolean verifyOneWayOptionSelection() {
		return oneWayRadioButton.isSelected();
	}

	public boolean verifyReturnDateStatus() {
		if (returnDate.getAttribute("style").contains("0.5"))
			return false;
		else
			return true;
	}

	public void clickRoundTripOption() {
		roundTripRadioButton.click();
	}

	public void selectDepartureCity(String depatureCity) {
		sourceInput.click();
		driver.findElement(By.xpath("//a[contains(text(),'" + depatureCity + "')]")).click();
	}

	public void selectArrivalCity(String arrivalCity) {
		driver.findElement(
				By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='" + arrivalCity + "']"))
				.click();
	}

	public void dateSelection() {
		startDate.click();
	}

	public void selectNoOfAdults(String noOfAdults) {
		adultDropDown.click();
		Select adultList = new Select(adultListSelection);
		adultList.selectByValue(noOfAdults);
	}

	public void selectSeniorCitizens() {
		chkboxSeniorCitizen.click();
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public void selectDepartureFlighHightestPrice() {
		findFare(departureTable);
	}

	public void selectArrivalFlighHightestPrice() {
		findFare(arrivalTable);
	}

	public void setOldAmount() {
		oldPrice = totalAmount.getText();
	}

	public void setUpdatedAmount() {
		updatedPrice = totalAmount.getText();
	}

	public String getUpdatedAmount() {
		return updatedPrice;
	}

	public String getOldAmount() {
		return oldPrice;
	}

	public String getTotalAmount() {
		return totalAmount.getText();
	}
	
	public void setSelectedHighPriceDepart(){
		selectedHighPriceDepart = totalDepartureAmt.getText();
	}
	
	public String getSelectedHighPriceDepart(){
		return selectedHighPriceDepart;
	}
	
	public void setSelectedHighPriceArrival(){
		selectedHighPriceArrival = totalArrivalAmt.getText();
	}
	
	public String getSelectedHighPriceArrival(){
		return selectedHighPriceArrival;
	}
	
	public void clickSeniorCitizenTC() {
		chkbxTermsAndCondtions.click();
	}

	public void clickContinueButton() {
		continueButton.click();
	}
	
	public String getTotalDueDepartureAmt(){
		return totalDueDepartureAmt.getText();
	}
	
	public String getTotalDueArrivalAmt(){
		return totalDueArrivalAmt.getText();
	}
	
	
    /*This method finds the total number of adult forms displayed in last page by creating the xpath 
     * by finding "Adult <no.>" */
	public int verifyAdultPassengerInFormPresent(int noOfAdults) {

		int adultFormCount = 0;
		for (int i = noOfAdults; i > 0; i--) {
			if (driver.findElement(By.xpath(adultFormStartXpath + (i - 1) + adultFormEndXpath)).isDisplayed()) {
				adultFormCount++;
			}
		}
		return adultFormCount;
	}

	/*
	 * This method finds the max price form the departure and arrival table and then click on that price.
	 * For loop hard coded starting from 4 as price started from 4th row in both departure and arrival table
	 */
	public void findFare(String tableID) {

		String startXPath = tableID;
		String midXPath = "/tbody/tr[";
		String endXPath = "]/td[3]/p/label/span[1]";
		String str;
		List<Integer> fairList = new ArrayList<Integer>();

		List<WebElement> rowCount = driver.findElements(By.xpath(startXPath + "/tbody/tr"));

		for (int i = 4; i <= rowCount.size(); i++) {
			str = driver.findElement(By.xpath(startXPath + midXPath + i + endXPath)).getText();
			str = str.replace(",", "");
			str = str.replace(" INR", "");
			fairList.add(Integer.parseInt(str));
		}
		driver.findElement(
				By.xpath(startXPath + midXPath + (fairList.indexOf(Collections.max(fairList)) + 4) + endXPath)).click();
	}
}
