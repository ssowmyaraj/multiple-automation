package com.multiple.tests.api.freetogame;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.multiple.utils.base.BaseTest;
import com.multiple.utils.base.Groups;
import com.multiple.utils.controller.MultipleController;

public class GamesTest extends BaseTest {

	@Test(groups = { Groups.MULTIPLEAPI })
	public void getAllFreeToPlayGames() throws Throwable {
		Response getAllFreeToPlayGamesResponse = MultipleController.getRequest(freeToGameBaseUrl,
				MultipleController.GetFreeGamesEndPoint);
		Assert.assertEquals(getAllFreeToPlayGamesResponse.getStatusCode(), HttpStatus.SC_OK);
		assertThat(getAllFreeToPlayGamesResponse.getBody().asString(),
				matchesJsonSchemaInClasspath("GetAllFreeToPlayGamesResponse.json"));
	}

}
