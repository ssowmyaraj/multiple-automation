package com.multiple.utils.base;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActions extends BaseTest {

	static Random random = new Random();

	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	static JavascriptExecutor js = (JavascriptExecutor) driver;

	public static String getPackageName(Method method) {
		Class<?> className = method.getDeclaringClass();
		String currentClassName = className.toString();
		int second = currentClassName.lastIndexOf(".");
		int first = currentClassName.substring(0, second).lastIndexOf(".") + 1;
		return currentClassName.substring(first, second);
	}

	public static void waitUntilPageContainsElement(WebDriver driver, By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public static void clickElement(WebDriver driver, By element) {
		try {
			driver.findElement(element).click();
		} catch (ElementClickInterceptedException ele) {
			driver.findElement(element).click();
		}
	}

	public static String generateUserName() {
		int randomNumber = random.nextInt(1000);
		return "Dummy" + randomNumber;
	}

	public static void enterText(WebDriver driver, By element, String text) {
		driver.findElement(element).sendKeys(text);
	}

	public static String generateEmailAddress(String username) {
		return username + "@yopmail.com";
	}

	public static String getText(WebDriver driver, By element) {
		String text = driver.findElement(element).getText();
		return text;
	}

	public static void pageShouldContainElement(WebDriver driver, By element) {
		driver.findElement(element).isDisplayed();
	}

	public static void switchToWindow(WebDriver driver) throws Exception {
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String currentWindow = it.next();
		String newlyOpenedWindow = it.next();
		driver.switchTo().window(newlyOpenedWindow);
	}

	public static void waitUntilPageContainsFrame(WebDriver driver, WebElement element) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public static void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

}
