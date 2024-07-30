package com.multiple.tests.web.casinoguru;

import java.io.IOException;

import org.testng.annotations.Test;

import com.multiple.pages.GamePlayPage;
import com.multiple.utils.base.BaseTest;
import com.multiple.utils.base.Groups;

public class GamePlayTest extends BaseTest {

	GamePlayPage gamePlayPage = new GamePlayPage();

	@Test(dependsOnMethods = { "com.multiple.tests.casinoguru.LoginTest.verifyLoginFlow" }, groups = {
			Groups.MULTIPLEWEB })
	public void verifyGamePlay() throws IOException, InterruptedException {
		gamePlayPage.launchGame();
		gamePlayPage.increaseBetAndPlayGame();

	}

}
