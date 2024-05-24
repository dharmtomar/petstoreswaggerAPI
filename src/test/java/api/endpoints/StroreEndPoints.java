package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.Store;
import api.utilities.GetProp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StroreEndPoints {

	public static Response createOrder(Store payload) {
		Response response= given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(GetProp.getURL().getString("store_post_url"));
		return response;
		
	}
	public static Response getOrderInfo(int id) {
		Response response= given()
		.pathParam("id", id)
		.accept(ContentType.JSON)
		
		.when()
		.get(GetProp.getURL().getString("store_get_url"));
		return response;
		
	}
	public static Response deleteOrder(int id) {
		Response response= given()
		.accept(ContentType.JSON)
		.pathParam("id", id)
		.when()
		.post(GetProp.getURL().getString("store_delete_url"));
		return response;
		
	}
	public void login(){

	System.out.println("login method from git user after merge conflict");
		System.out.println("login conflict retest");
	}

}
