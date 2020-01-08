package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class TestClass {
	
	static WebDriver driver;
	static String departureTable = "//*[@id='availabilityTable0']";
	static String arrivalTable = "//*[@id='availabilityTable1']";

	public static void main(String[] args) throws InterruptedException {
	
	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	
	driver = new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
	driver.get("https://www.spicejet.com/");
	//clicking round trip radio button
	driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();

	//click on source input                               
	driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
	//click on hyd
	driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='DEL']")).click();
	driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='BLR']")).click();

	//start date picker 
	driver.findElement(By.xpath("//*[@id='ui-datepicker-div'] //a[@class='ui-state-default ui-state-highlight ui-state-active ui-state-hover']")).click();
	
	//select adults
	driver.findElement(By.id("divpaxinfo")).click();
	Select adultList = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Adult")));
	adultList.selectByValue("2");
	
	//select senior citizen checkbox
	driver.findElement(By.id("ctl00_mainContent_chk_SeniorCitizenDiscount")).click();
	
	//click search
	driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
	
	findFare(departureTable);
	
	findFare(arrivalTable);
	
	}
	
	public static void findFare(String tableID){
		
		String startXPath = tableID;
		String midXPath = "/tbody/tr[";
		String endXPath = "]/td[3]/p/label/span[1]";
		String str;
		List<Integer> fairList = new ArrayList<Integer>();
		
		List<WebElement> rowCount = driver.findElements(By.xpath(startXPath + "/tbody/tr" ));
		
		for(int i=4; i<=rowCount.size(); i++){
			str = driver.findElement(By.xpath(startXPath + midXPath + i + endXPath )).getText();
			str = str.replace(",","" );
			str = str.replace(" INR", "");
			fairList.add(Integer.parseInt(str));
		}
		
		driver.findElement(By.xpath(startXPath + midXPath + (fairList.indexOf(Collections.max(fairList))+4) + endXPath)).click();
	}

}
