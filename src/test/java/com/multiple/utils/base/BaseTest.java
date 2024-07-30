package com.multiple.utils.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import static com.multiple.utils.base.CommonActions.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	public Logger log = Logger.getLogger(getClass().getSimpleName());
	public static ConfigLoader configLoader = new ConfigLoader();
	protected ExtentTest parent = null;
	protected ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	protected ThreadLocal<ExtentTest> childTest = new ThreadLocal<ExtentTest>();
	protected ExtentTest logger = null;
	public static String baseUrl;
	public static String userName;
	public static String userPassword;
	public static String userEmail;
	public static String freeToGameBaseUrl = "";
	protected static ObjectMapper mapper = new ObjectMapper();

	//Initial setup to launch the browser
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "password", "platform" })
	public void setUp(String password, String platform) throws Throwable {
		report = ReportBuilder.createInstance(configLoader.getValue("reports_path"), "multiple");
		if (platform.equals("web")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			userName = generateUserName();
			userEmail = generateEmailAddress(userName);
			userPassword = password;
			baseUrl = configLoader.getValue("multiple_url");
			driver.get(baseUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if (platform.equals("api")) {
			freeToGameBaseUrl = ConfigLoader.getValue("free_to_game_url");
		}

	}

	@BeforeClass(alwaysRun = true)
	public synchronized void beforeClass() {
		parent = report.createTest(getClass().getName());
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method m) {
		parentTest.set(parent);
		logger = ((ExtentTest) parentTest.get()).createNode(m.getName());
		childTest.set(logger);
		logger.assignCategory(getPackageName(m));
		log.info("EXECUTING TEST ********** " + getClass().getSimpleName() + " >> " + m.getName());
	}

	//Generate report based on the test result
	@AfterMethod(alwaysRun = true)
	public synchronized void generateReport(ITestResult result, Method m) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotCaptureTime = CaptureScreenShot.getScreenShot(driver);
			String temp = screenshotCaptureTime + ".png";
			((ExtentTest) childTest.get()).fail(MarkupHelper.createLabel("Failed Test", ExtentColor.RED));
			((ExtentTest) childTest.get()).fail(result.getThrowable());
			((ExtentTest) childTest.get()).fail("Click here to open the Failed test --->>>>> " + m.getName(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			String screenshotCaptureTime = CaptureScreenShot.getScreenShot(driver);
			String temp = screenshotCaptureTime + ".png";
			((ExtentTest) childTest.get()).skip(result.getThrowable());
			((ExtentTest) childTest.get()).skip(MarkupHelper.createLabel("Test skipped", ExtentColor.LIME));
			((ExtentTest) childTest.get()).fail(result.getThrowable());
			((ExtentTest) childTest.get()).fail("Click here to open the skipped test --->>>>> " + m.getName(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			((ExtentTest) childTest.get()).pass(m.getName() + " ---> Test passed");
		} else if (result.getStatus() == ITestResult.CREATED) {
			String screenshotCaptureTime = CaptureScreenShot.getScreenShot(driver);
			String temp = screenshotCaptureTime + ".png";
			((ExtentTest) childTest.get()).error(result.getTestName());
			((ExtentTest) childTest.get()).error(MarkupHelper.createLabel("Test skipped", ExtentColor.BLUE));
			((ExtentTest) childTest.get()).fail(result.getThrowable());
			((ExtentTest) childTest.get()).fail("Click here to open the skipped test --->>>>> " + m.getName(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		report.flush();
		driver.manage().deleteAllCookies();
	}

	@AfterSuite(alwaysRun = true)
	public void finish() {
		driver.quit();
	}

}
