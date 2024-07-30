package com.multiple.utils.controller;

import static com.multiple.utils.base.BaseRequests.get;

import com.jayway.restassured.response.Response;

public class MultipleController {

	public static final String GetFreeGamesEndPoint = "/games";

	public static Response getRequest(String url, String endPoint) {
		String apiEndPoint = url + endPoint;
		Response response = get(apiEndPoint);
		return response;
	}

}
