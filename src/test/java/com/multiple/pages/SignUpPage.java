package com.multiple.pages;

import static com.multiple.utils.base.CommonActions.*;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.multiple.utils.base.BaseTest;

public class SignUpPage extends BaseTest {

	public static By signUp = By.xpath("//span[text()='Sign up']");
	public static By userNameField = By.xpath("//input[@id='login']");
	public static By emailAddressField = By.xpath("//input[@id='email']");
	public static By passwordField = By.xpath("//input[@id='password']");
	public static By confirmPasswordField = By.xpath("//input[@id='confirm_password']");
	public static By agreeToTermsOfService = By.xpath("//label[@for='agreeToTermsOfService']/span[1]");
	public static By signUpButton = By.xpath("//div[@class='login-form-bottom']/div/button[@type='submit']");
	public static By emailAddressOnEmailConfirmationScreen = By.xpath("//h4[@class='mt-xl']");
	public static By okButton = By.xpath("//a[contains(text(),'OK')]");
	public static By emailConfirmationText = By.xpath("//h3[contains(text(),'E-mail confirmation')]");
	public static By toCompleteRegistrationText = By
			.xpath("//div[contains(text(),'To complete your registration, click the link in t')]");
	public static By verifyEmailCheckMark = By.xpath("//span[@class='icon verify-email-checkmark']");
	public static By changeEmail = By.xpath("//b[contains(text(),'(Change e-mail)')]");
	public static By linkWillExpireText = By
			.xpath("//p[contains(text(),'The link will expire in 72 hours. If you cannot fi')]");
	public static By resendEmail = By.xpath("(//button[contains(text(),'Resend e-mail')])[1]");
	public static By yopmailEnterEmailField = By.xpath("//input[@id='login']");
	public static By yopmailSubmitEmailButton = By.xpath("//div[@id='refreshbut']");
	public static By casinoGuruForumMail = By.xpath("//span[contains(text(),'Casino Guru Forum')]");
	public static By verifyEmailAddress = By.xpath("//a[contains(text(),'Verify my e-mail address')]");
	public static By userNameOnMenu = By.xpath("//span[@class='menu-user']/span/span");
	public static By logout = By.xpath("//a[contains(@href,'logout')]");
	public static By yopmailIFrame = By.id("ifinbox");
	public static By yopmailBodyIFrame = By.id("ifmail");

	public void createNewUser() {
		clickElement(driver, signUp);
		waitUntilPageContainsElement(driver, userNameField);
		enterText(driver, userNameField, userName);
		enterText(driver, emailAddressField, userEmail);
		enterText(driver, passwordField, userPassword);
		enterText(driver, confirmPasswordField, userPassword);
		clickElement(driver, agreeToTermsOfService);
		clickElement(driver, signUpButton);
		waitUntilPageContainsElement(driver, resendEmail);
		Assert.assertEquals(getText(driver, emailAddressOnEmailConfirmationScreen), userEmail);
		pageShouldContainElement(driver, emailConfirmationText);
		pageShouldContainElement(driver, toCompleteRegistrationText);
		pageShouldContainElement(driver, verifyEmailCheckMark);
		pageShouldContainElement(driver, changeEmail);
		pageShouldContainElement(driver, linkWillExpireText);
		pageShouldContainElement(driver, resendEmail);
		clickElement(driver, okButton);
		waitUntilPageContainsElement(driver, signUp);

	}

	public void verifyEmailAddress() throws Exception {
		driver.get(configLoader.getValue("yopmail_url"));
		waitUntilPageContainsElement(driver, yopmailEnterEmailField);
		enterText(driver, yopmailEnterEmailField, userEmail);
		clickElement(driver, yopmailSubmitEmailButton);
		waitUntilPageContainsFrame(driver, driver.findElement(yopmailIFrame));
		waitUntilPageContainsElement(driver, casinoGuruForumMail);
		clickElement(driver, casinoGuruForumMail);
		driver.switchTo().defaultContent();
		waitUntilPageContainsFrame(driver, driver.findElement(yopmailBodyIFrame));
		clickElement(driver, verifyEmailAddress);
		switchToWindow(driver);
		waitUntilPageContainsElement(driver, userNameOnMenu);
		Assert.assertEquals(getText(driver, userNameOnMenu), userName);

	}

	public void verifyLogout() {
		clickElement(driver, userNameOnMenu);
		clickElement(driver, logout);
		waitUntilPageContainsElement(driver, signUp);

	}
}
