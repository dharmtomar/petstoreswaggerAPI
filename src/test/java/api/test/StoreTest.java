package api.test;

import java.util.Date;

import org.joda.time.Years;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

import api.endpoints.StroreEndPoints;
import api.logger.Log;
import api.payloads.Store;
import api.utilities.GetProp;
import io.restassured.response.Response;

public class StoreTest {
	Store storePayload;
	Faker faker;

	@BeforeClass
	public void setup() {
		storePayload = new Store();
		faker = new Faker();
		storePayload.setId(faker.idNumber().hashCode());
		storePayload.setPetId(faker.idNumber().hashCode());
		storePayload.setQuantity("2");
		storePayload.setShipDate("2024-05-22T16:02:44.400Z");
		storePayload.setStatus("placed");

	}

	@Test(priority = 1)
	public void createOrderTest() {
		Log.debug("create order test started");
		Response response = StroreEndPoints.createOrder(storePayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		Log.debug("create order test finished");
	}

	@Test(priority = 2)
	public void getOrderinfoTest() {
		Log.debug("get order info test started");
		Response response = StroreEndPoints.getOrderInfo(this.storePayload.id);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		Log.debug("get order info test finished");
	}

	@Test(priority = 3)
	public void deleteOderTest() {
		Log.info("delete order test started");
		Response response = StroreEndPoints.deleteOrder(5);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 405);
		Log.info("delete order test finished");
	}

}
