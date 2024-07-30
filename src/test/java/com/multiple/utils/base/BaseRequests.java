package com.multiple.utils.base;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.response.Response;

public class BaseRequests {

	public static Response get(String url) {
		Response response = given().urlEncodingEnabled(true).when().get(url).then().extract().response();
		return response;
	}

}
