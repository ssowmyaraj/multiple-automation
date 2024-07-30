package com.multiple.pages;

import static com.multiple.utils.base.CommonActions.clickElement;
import static com.multiple.utils.base.CommonActions.enterText;
import static com.multiple.utils.base.CommonActions.getText;
import static com.multiple.utils.base.CommonActions.waitUntilPageContainsElement;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.multiple.utils.base.BaseTest;

public class LoginPage extends BaseTest {
	
	public static By login = By.xpath("//span[contains(text(),'Login')]");
	public static By emailAddressOrUserNameField = By.xpath("//input[@id='email2']");
	public static By passwordField = By.xpath("//input[@id='password2']");
	public static By loginButton = By.xpath("//button[@type='submit' and contains(text(),'Log in')]");
	
	public void loginToApplication() {
		clickElement(driver, login);
		waitUntilPageContainsElement(driver, emailAddressOrUserNameField);
		enterText(driver, emailAddressOrUserNameField, userEmail);
		enterText(driver, passwordField, userPassword);
		clickElement(driver, loginButton);
		waitUntilPageContainsElement(driver, SignUpPage.userNameOnMenu);
		Assert.assertEquals(getText(driver, SignUpPage.userNameOnMenu), userName);
		
	}

}
