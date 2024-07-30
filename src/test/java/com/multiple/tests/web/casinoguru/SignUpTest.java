package com.multiple.tests.web.casinoguru;

import org.testng.annotations.Test;

import com.multiple.pages.SignUpPage;
import com.multiple.utils.base.BaseTest;
import com.multiple.utils.base.Groups;

public class SignUpTest extends BaseTest {

	SignUpPage signUpPage = new SignUpPage();

	@Test(groups = { Groups.MULTIPLEWEB })
	public void createNewUser() throws Throwable {
		signUpPage.createNewUser();
		signUpPage.verifyEmailAddress();
		signUpPage.verifyLogout();

	}

}
