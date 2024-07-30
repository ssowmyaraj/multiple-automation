package com.multiple.pages;

import static com.multiple.utils.base.CommonActions.*;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.multiple.utils.base.BaseTest;

public class GamePlayPage extends BaseTest {

	static JavascriptExecutor js = (JavascriptExecutor) driver;
	WebElement canvas;

	public static By fullScreenIcon = By.xpath("//div[@class='game-block-header-fullscreen']/span");
	public static By gamesContentIFrame = By.id("gamefileEmbed1");
	public static By spin = By.xpath("//div[@id='wheelofpatience']");

	public void launchGame() throws IOException, InterruptedException {
		driver.get(configLoader.getValue("samurai_code_slot_url"));
		driver.manage().window().maximize();
		waitUntilPageContainsElement(driver, fullScreenIcon);
		switchToFrame(driver, driver.findElement(gamesContentIFrame));
		canvas = driver.findElement(By.tagName("canvas"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", canvas);
		setClickPoints(12, 15);

	}

	//Pass the coordinates of the clickables here
	public void setClickPoints(int diffX, int diffY) {
		int canvasWidth = getCanvasWidth();
		int canvasHeight = getCanvasHeight();
		calculateCoordinates(canvasWidth, canvasHeight, diffX, diffY);
	}

	public int getCanvasHeight() {
		Dimension canvas_dimensions = canvas.getSize();
		int canvas_height = canvas_dimensions.getHeight();
		return canvas_height;
	}

	private int getCanvasWidth() {
		Dimension canvas_dimensions = canvas.getSize();
		int canvas_width = canvas_dimensions.getWidth();
		return canvas_width;
	}

	//Calculates the click points on the fly based on the values which are required to be adjusted
	public void calculateCoordinates(int canvasWidth, int canvasHeight, int adjustWidth, int adjustHeight) {
		int xCoord = (canvasWidth) - ((canvasWidth) * adjustWidth) / 100;
		int yCoord = (canvasHeight) - ((canvasHeight) * adjustHeight) / 100;
		Actions actions = new Actions(driver);
		actions.moveToElement(canvas, xCoord, yCoord).doubleClick().perform();

	}

	public void increaseBetAndPlayGame() throws IOException, InterruptedException {
		// Clicking on + icon with coordinates
		setClickPoints(12, 40);
		// Round 1 - Stake 1 - Clicking Bet + icon with coordinates to increase the bet
		// value
		setClickPoints(10, 10);
		// Clicking Play icon with coordinate
		setClickPoints(9, 20);
		setClickPoints(9, 20);
		// Round 2 - Stake 2 - Clicking Coin value + icon with coordinates to increase
		// the coin value
		setClickPoints(9, 8);
		// Clicking Play icon with coordinate
		setClickPoints(9, 20);
		setClickPoints(9, 20);
		setClickPoints(9, 20);
		// Round 3 - Stake 3 - Clicking Total bet + icon with coordinates to increase
		// the total bet value
		setClickPoints(9, 7);
		// Clicking Play icon with coordinate
		setClickPoints(9, 20);
		setClickPoints(9, 20);
		// Round 4 - Stake 4 - Clicking Bet Max with coordinates
		setClickPoints(8, 18);
		setClickPoints(9, 20);
		setClickPoints(9, 20);
		// Round 5 - Stake 5 - Play with higher chance to win feature turned on
		setClickPoints(6, 13);
		setClickPoints(9, 20);
		setClickPoints(9, 20);
	}

}
