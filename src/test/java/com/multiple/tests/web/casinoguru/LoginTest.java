package com.multiple.tests.web.casinoguru;

import org.testng.annotations.Test;

import com.multiple.pages.LoginPage;
import com.multiple.utils.base.BaseTest;
import com.multiple.utils.base.Groups;

public class LoginTest extends BaseTest {

	LoginPage loginPage = new LoginPage();

	@Test(dependsOnMethods = { "com.multiple.tests.casinoguru.SignUpTest.createNewUser" }, groups = {
			Groups.MULTIPLEWEB })
	public void verifyLoginFlow() {
		loginPage.loginToApplication();

	}

}
