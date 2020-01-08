package apiTest;

import static io.restassured.RestAssured.given;
import org.junit.runners.MethodSorters;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APITest extends APIConfigDetails {

	static String longitude;
	static String latitude;
	static String longitude2;
	static String latitude2;
	static JsonPath js;

	@Test
	public void ALookupAPostcode() {
		RestAssured.baseURI = BaseURL;
		Response res = given().when().get(EndPoint_GetPostCode).then().assertThat().statusCode(200).extract()
				.response();
		String response = res.asString();
		Assert.assertTrue(response.contains("region"));
		Assert.assertTrue(response.contains("nuts"));
		Assert.assertTrue(response.contains("codes"));

		js = new JsonPath(response);
		longitude = js.getString("result.longitude");
		latitude = js.getString("result.latitude");

		System.out.println("Longitude  is: " + longitude);
		System.out.println("Latitude  is: " + latitude);
	}

	@Test
	public void BFindPostcodeForGivenLT() {

		Response res1 = given().param("lon", longitude).param("lat", latitude).when().get(EndPoint_Get_Nearest_PostCode)
				.then().assertThat().statusCode(200).extract().response();

		String response2 = res1.asString();
		Assert.assertTrue(response2.contains("region"));
		js = new JsonPath(response2);
		longitude2 = js.getString("result.longitude");
		latitude2 = js.getString("result.latitude");

		// need to check below as reponse contains more than 1 record
		Assert.assertTrue(longitude2.contains(longitude));
		Assert.assertTrue(latitude2.contains(latitude));
	}

	@Test
	public void CFindNearestPostcodesForGivenPostcode() {
		RestAssured.baseURI = BaseURL;
		Response res2 = given().when().get(EndPoint_Get_Nearest_PostCode_ofPostCode).then().assertThat().statusCode(200)
				.extract().response();
		String response2 = res2.asString();
		JsonPath js1 = new JsonPath(response2);

		for (int i = 0; i < 10; i++) {
			String numberOfPostcode = js1.getString("result[" + i + "].postcode");
			String country = js1.getString("result[" + i + "].country");
			String numberOfadmin_ward = js1.getString("result[" + i + "].admin_ward");
			String latitudValue = js1.getString("result[" + i + "].latitude");

			if (!numberOfPostcode.isEmpty()) {
				Assert.assertTrue(!country.isEmpty());
				Assert.assertTrue(!numberOfadmin_ward.isEmpty());
				Assert.assertEquals(country, "England");
			} else {
				System.out.println("Test Failed: Country and Admin ward is not found in response");
			}

			// Extract Post code when latitude is equal to 51.51563
			if (latitudValue.equals("51.51563")) {
				String PostCode = js1.getString("result[" + i + "].postcode");
				System.out.println("** The Extracted post code is ::  " + PostCode + "** ");
			}
		}
	}
}