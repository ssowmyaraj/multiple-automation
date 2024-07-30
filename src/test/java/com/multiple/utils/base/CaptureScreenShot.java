package com.multiple.utils.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenShot {

	public static String getScreenShot(WebDriver driver) throws IOException {

		String screenshotCaptureTime = System.currentTimeMillis() + "";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Reports/" + screenshotCaptureTime + ".png";
		File destination = new File(path);
		FileUtils.copyFile(src, destination);
		return screenshotCaptureTime;
	}

}
