package com.company.project.utilities;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

/** Created by pavankovurru on 1/29/17. */
public class AppiumUtil {

  private static Logger log = LogManager.getLogger();
  private AppiumDriver driver;
  private String parentHandle;
  private Alert alert;
  private WebDriverWait wait;

  public AppiumUtil(AppiumDriver driver) {
    this.driver = driver;
  }

  // ******* EXPLICIT WAITS ON SINGLE ELEMENT ******************//
  // ************************************************************

  // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS CLICKABLE - DISPLAYED AND ENABLED
  public WebElement wait_until_MobileElementIs_Clickable(WebDriver driver, By locator) {
    log.info("15 secs - Waiting for element using -" + locator);
    wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.elementToBeClickable(locator));
  }

  // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS VISIBLE
  public WebElement wait_until_MobileElementIs_Visible(WebDriver driver, By locator) {
    log.info("15 secs - Waiting for element using -" + locator);
    wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  // WAIT FOR MAX TIME 15 SECS TILL THE ELEMENT IS PRESENT
  public WebElement wait_until_MobileElementIs_Present(WebDriver driver, By locator) {
    log.info("15 secs - Waiting for element using -" + locator);
    wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  // ************* EXPLICIT WAITS ON MULTIPLE ELEMENTS ***************//
  // ************************************************************

  // WAIT FOR MAX TIME 5 SECS TILL THE ELEMENT IS PRESENT
  public List<WebElement> wait_until_MobileElementsAre_Present(WebDriver driver, By locator) {
    log.info("5 secs - Waiting for elements using -" + locator);
    wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
  }

  // WAIT FOR MAX TIME 5 SECS TILL THE ELEMENT IS VISIBLE
  public List<WebElement> wait_until_MobileElementsAre_Visible(WebDriver driver, By locator) {
    log.info("5 secs - Waiting for elements using -" + locator);
    wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }

  public List<WebElement> wait_until_MobileElementsAre_Visible(
      WebDriver driver, By locator, int timeInSeconds) {
    log.info("5 secs - Waiting for elements using -" + locator);
    wait = new WebDriverWait(driver, timeInSeconds);
    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }

  // ******** EXPLICIT WAITS ON PAGE TITLE,URL AND ELEMENT_NOT_PRESENT ************//

  public boolean is_MobileElement_NotPresent(WebDriver driver, By locator) {
    log.info("5 secs - checking for element presence using -" + locator);
    wait = new WebDriverWait(driver, 15);
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  // **** RETURNING MOBILE ELEMENT ANDROID *****//
  // ************************************************************

  public WebElement android_returnMobileElementPresentUsingText(String text) {
    log.info("Trying to find element with text - " + text);
    return wait_until_MobileElementIs_Visible(
        driver, MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")"));
  }

  public WebElement android_returnMobileElementPresentUsingID(String id) {
    log.info("Trying to find element with ID - " + id);
    return wait_until_MobileElementIs_Visible(
        driver, MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")"));
  }

  public WebElement android_returnMobileElementPresentUsingContentDesc(String contentDesc) {
    log.info("Trying to locate element using content-desc - " + contentDesc);
    return wait_until_MobileElementIs_Visible(
        driver,
        MobileBy.AndroidUIAutomator("new UiSelector().description(\"" + contentDesc + "\")"));
  }

  public WebElement android_returnMobileElementPresentUsingXPath(String xpath) {
    log.info("Trying to locate element using x-path - " + xpath);
    return wait_until_MobileElementIs_Visible(driver, MobileBy.xpath(xpath));
  }

  public WebElement android_returnMobileElementPresentUsingClassName(String className) {
    log.info("Trying to locate elements using className - " + className);
    return wait_until_MobileElementIs_Visible(driver, MobileBy.className(className));
  }

  // **** RETURNING MOBILE ELEMENTS ANDROID *****//
  // ************************************************************

  public List<WebElement> android_returnMobileElementsPresentUsingXPath(String xpath) {
    log.info("Trying to locate elements using x-path - " + xpath);
    return wait_until_MobileElementsAre_Visible(driver, MobileBy.xpath(xpath));
  }

  public List<WebElement> android_returnMobileElementsPresentUsingClassName(String className) {
    log.info("Trying to locate elements using className - " + className);
    return wait_until_MobileElementsAre_Visible(driver, MobileBy.className(className));
  }

  // open notifications Android
  // ************************************************************

  public void android_openNotifications() {
    log.info("Opening notifications page");
    ((AndroidDriver) driver).openNotifications();
  }

  // rotate screen
  // ************************************************************

  public void rotateScreenPotrait() {
    log.info("rotating screen -  PORTRAIT mode");
    driver.rotate(ScreenOrientation.PORTRAIT);
  }

  public void rotateScreenlandscape() {
    log.info("rotating screen -  LANDSCAPE mode");
    driver.rotate(ScreenOrientation.LANDSCAPE);
  }

  // clear notifications
  // ************************************************************

  public void android_clearNotifications() {

    // google pixel related
    if (android_isMobileElementPresentUsingText("CLEAR ALL")) {
      wait_until_MobileElementIs_Clickable(
              driver, MobileBy.AndroidUIAutomator("new UiSelector().text(\"CLEAR ALL\")"))
          .click();
      log.info("Existing notifications Cleared");

    } else {
      log.info("Could not find clear existing notifications option in the notifications page");
    }

    // TODO update clear notifications present
  }

  // Set location Android
  // ************************************************************

  public void android_SetLocation(double latitude, double longitude) {
    log.info("Trying to mock location --> Lat:" + latitude + " Long: " + longitude);
    Location loc = new Location(latitude, longitude, 10); // latitude, longitude, altitude
    driver.setLocation(loc);
    log.info("Location set to --> Latitude: " + latitude + " Longitude: " + longitude);
  }

  // **** CONTEXT SWITCHING *****//
  // ************************************************************

  public Set<String> switchToWebViewAndReturnAllContextHandles() {
    log.info("Trying to switch to webview");
    Set<String> availableContexts = driver.getContextHandles();
    log.info("No of Contexts Found  = " + availableContexts.size());
    for (String context : availableContexts) {
      log.info("Context - " + context);
      if (context.matches(".*?WEBVIEW.*?")) {
        log.info("Switching to context " + context);
        driver.context(context);
        log.info("Context switched to -" + driver.getContext());
        break;
      }
    }
    return availableContexts;
  }

  public void switchToNativeContext(Set<String> availableContexts) {
    for (String context : availableContexts) {
      if (context.contains("NATIVE")) {
        log.info("Trying to switch to native context");
        driver.context(context);
        log.info("Switched to Context" + context);
      }
    }
  }

  // **** SCROLL FUNCTIONS (SCROLL'SearchConstants ON ENTIRE PAGE) *****//
  // ************************************************************

  public MobileElement android_ScrollToText(String text) {
    log.info("Trying to scroll to element with text  - " + text);

    MobileElement el =
        (MobileElement)
            wait_until_MobileElementIs_Visible(
                driver,
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    return el;
  }

  public MobileElement android_ScrollToContentDesc(String contentDesc) {
    log.info("Trying to scroll to element with content desc - " + contentDesc);
    MobileElement el =
        (MobileElement)
            wait_until_MobileElementIs_Visible(
                driver,
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(description(\""
                        + contentDesc
                        + "\"));"));
    return el;
  }

  public MobileElement android_scrollToID(String id) {
    log.info("Trying to scroll to android element with ID - " + id);

    MobileElement el =
        (MobileElement)
            wait_until_MobileElementIs_Visible(
                driver,
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\""
                        + id
                        + "\"));"));
    return el;
  }

  // **** SCROLL FUNCTIONS (SCROLL'SearchConstants INSIDE PARTICULAR ELEMENT) *****//
  // ************************************************************
  // Other Supported Functions available here -
  // https://developer.android.com/reference/android/support/test/uiautomator/UiSelector

  public MobileElement android_scrollToTextInsideElementWithResourceID(
      String resourceID, String text) {
    log.info("Trying to scroll to android element with text - " + text);
    // make sure u give the resouce ID of the complete list of elements here as parameter

    MobileElement el =
        (MobileElement)
            wait_until_MobileElementIs_Visible(
                driver,
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()"
                        + ".resourceId(\""
                        + resourceID
                        + "\")).scrollIntoView("
                        + "new UiSelector().text(\""
                        + text
                        + "\"));"));
    return el;
  }

  public MobileElement android_scrollToTextInsideElementWithContentDesc(
      String contentDesc, String text) {
    log.info("Trying to scroll to android element with contentDesc - " + contentDesc);
    // make sure u give the resouce ID of the complete list of elements here as parameter

    MobileElement el =
        (MobileElement)
            wait_until_MobileElementIs_Visible(
                driver,
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()"
                        + ".description(\""
                        + contentDesc
                        + "\")).scrollIntoView("
                        + "new UiSelector().text(\""
                        + text
                        + "\"));"));
    return el;
  }

  public MobileElement android_scrollToTextInsideElementWithID(String id, String text) {
    log.info("Trying to scroll to android element with ID - " + id);
    // make sure u give the resouce ID of the complete list of elements here as parameter

    MobileElement el =
        (MobileElement)
            wait_until_MobileElementIs_Visible(
                driver,
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()"
                        + ".resourceId(\""
                        + id
                        + "\")).scrollIntoView("
                        + "new UiSelector().text(\""
                        + text
                        + "\"));"));
    return el;
  }

  // **** ANDROID KEY EVENT FUNCTIONS *****//
  // ************************************************************

  public void android_HomeKeyEvent() {
    log.info("android Home event");
    ((AndroidDriver) driver)
        .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.HOME));
    sleep(2);
  }

  public void android_BackKeyEvent() {
    log.info("android back event");
    ((AndroidDriver) driver)
        .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.BACK));
    sleep(2);
  }

  public void android_Enter() {
    log.info("android Enter event");
    ((AndroidDriver) driver)
        .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.ENTER));
    sleep(2);
  }

  public void android_Tab() {
    log.info("android Tab event");
    ((AndroidDriver) driver)
        .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.TAB));
    sleep(2);
  }

  public void android_MultiTaskingKeyEvent() {
    ((AndroidDriver) driver)
        .pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.APP_SWITCH));
    sleep(2);
  }

  // ********** CHECK FOR PRESENCE OF MOBILE ELEMENT ANDROID **************//
  // ************************************************************

  public boolean android_isMobileElementPresentUsingText(String text) {

    try {
      log.info("Trying to find element with text - " + text);
      if (wait_until_MobileElementsAre_Visible(
                  driver,
                  (MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + text + "\")")),
                  5)
              .size()
          >= 1) {
        log.info("element found");
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return true;
  }

  public boolean android_isMobileElementPresentUsingID(String id) {
    log.info("Trying to find element with ID - " + id);

    try {
      if (wait_until_MobileElementsAre_Visible(
                  driver,
                  (MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")")),
                  5)
              .size()
          >= 1) {
        log.info("Found element having id -" + id);
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return true;
  }

  public boolean android_isMobileElementPresentUsingContentDesc(String contentDesc) {
    try {
      log.info("Trying to locate element using content-desc - " + contentDesc);
      if (wait_until_MobileElementsAre_Visible(
                  driver,
                  (MobileBy.AndroidUIAutomator(
                      "new UiSelector().description(\"" + contentDesc + "\")")),
                  5)
              .size()
          >= 1) {
        log.info("Found element having content desc - " + contentDesc);
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return true;
  }

  public boolean android_isMobileElementPresentUsingXpath(String xPath) {
    try {
      log.info("Trying to locate element using content-desc - " + xPath);
      if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), 5).size() >= 1) {
        log.info("Found element having content desc - " + xPath);
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  // ********** SCROLL AND CHECK FOR PRESENCE OF MOBILE ELEMENT ANDROID **************//
  // ************************************************************

  public boolean android_isMobileElementPresentUsingText_Scroll(String text) {
    log.info("Trying to find element with text - " + text);
    if (wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));")))
            .size()
        >= 1) {
      log.info("element found");
      return true;
    } else {
      log.info("element not found");
      return false;
    }
  }

  public boolean android_isMobileElementPresentUsingID_Scroll(String id) {
    log.info("Trying to find element with ID - " + id);
    if (wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\""
                        + id
                        + "\"));")))
            .size()
        >= 1) return true;
    else return false;
  }

  public boolean android_isMobileElementPresentUsingContentDesc_Scroll(String contentDesc) {
    log.info("Trying to locate element using content-desc - " + contentDesc);
    if (wait_until_MobileElementsAre_Present(
                driver,
                (MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(description(\""
                        + contentDesc
                        + "\"));")))
            .size()
        >= 1) {
      log.info("Found element having content desc -" + contentDesc);
      return true;
    } else return false;
  }

  // ********** TOUCH ACTIONS **************//
  // ************************************************************

  public void pressAndHold(WebElement element1, int timeInSeconds) {
    Rectangle rect1 = element1.getRect();

    TouchAction touch = new TouchAction(driver);
    touch
        .longPress(
            PointOption.point(
                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight() / 2))
        .waitAction(waitOptions(Duration.ofSeconds(timeInSeconds)))
        .release()
        .perform();
    log.info("Press and Hold Action performed");
  }

  public void pressElement(MobileElement element, long seconds) {
    new TouchAction(driver)
        .press(element(element))
        .waitAction(waitOptions(Duration.ofSeconds(seconds)))
        .release()
        .perform();
  }

  public void tap(WebElement element) {
    new TouchAction(driver).tap(TapOptions.tapOptions().withElement(element(element))).perform();
  }

  public void tap(WebElement element, int milliseconds) {
    new TouchAction(driver)
        .tap(TapOptions.tapOptions().withElement(element(element)))
        .waitAction(waitOptions(Duration.ofMillis(milliseconds)))
        .perform();
  }

  public void tapByCoordinates(int x, int y) {
    new TouchAction(driver)
        .tap(PointOption.point(x, y))
        .waitAction(waitOptions(Duration.ofMillis(250)))
        .perform();
  }

  public void basicSwipe(WebElement element1, WebElement element2) {
    Rectangle rect1 = element1.getRect();
    Rectangle rect2 = element2.getRect();

    TouchAction touch = new TouchAction(driver);
    touch
        .press(
            PointOption.point(
                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight()))
        .moveTo(
            PointOption.point(
                rect2.getX() + rect2.getWidth() / 2, rect2.getY() + rect2.getHeight()))
        .release()
        .perform();
    log.info("Swipe Action performed");
  }

  public void dragDrop(WebElement element1, WebElement element2) {
    Rectangle rect1 = element1.getRect();
    Rectangle rect2 = element2.getRect();

    TouchAction touch = new TouchAction(driver);
    touch
        .longPress(
            PointOption.point(
                rect1.getX() + rect1.getWidth() / 2, rect1.getY() + rect1.getHeight() / 2))
        .moveTo(
            PointOption.point(
                rect2.getX() + rect2.getWidth() / 2, rect2.getY() + rect2.getHeight() / 2))
        .release()
        .perform();
    log.info("Drag and drop Action performed");
  }

  // ******************************     IOS FUNCTIONS      ******************************* //
  // ************************************************************

  // ***** RETURN MOBILE ELEMENT ****** //

  public WebElement ios_returnMobileElementUsingAccessibilityId(String accessibilityId) {
    log.info("Trying to find element with id - " + accessibilityId);
    return wait_until_MobileElementIs_Visible(driver, MobileBy.AccessibilityId(accessibilityId));
  }

  public WebElement ios_returnMobileElementUsingName(String name) {
    log.info("Trying to find element with name - " + name);
    return wait_until_MobileElementIs_Visible(driver, MobileBy.name(name));
  }

  public WebElement ios_returnMobileElementUsingClassName(String className) {
    log.info("Trying to find element with class name - " + className);
    return wait_until_MobileElementIs_Visible(driver, MobileBy.className(className));
  }

  public WebElement ios_returnMobileElementUsingXpath(String xPath) {
    log.info("Trying to find element with xPath - " + xPath);
    return wait_until_MobileElementIs_Visible(driver, MobileBy.xpath(xPath));
  }

  public WebElement ios_returnMobileElementUsingPredicateString(String predicateString) {
    log.info("Trying to find element with predicateString - " + predicateString);
    return wait_until_MobileElementIs_Visible(
        driver, MobileBy.iOSNsPredicateString(predicateString));
  }

  public WebElement ios_returnMobileElementUsingClassChain(String classChain) {
    log.info("Trying to find element with classChain - " + classChain);
    return wait_until_MobileElementIs_Visible(driver, MobileBy.iOSClassChain(classChain));
  }

  // ***** PRESENCE OF MOBILE ELEMENT ****** //

  public boolean ios_isElementPresentUsingAccessibilityId(String acessibilityId) {

    try {
      log.info("Trying to find element with AcessibilityId - " + acessibilityId);
      if (wait_until_MobileElementsAre_Visible(
                  driver, (MobileBy.AccessibilityId(acessibilityId)), 5)
              .size()
          >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  public boolean ios_isElementPresentUsingName(String name) {

    try {
      log.info("Trying to find element with name - " + name);
      if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.name(name)), 5).size() >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  public boolean ios_isElementPresentUsingXpath(String xPath) {

    try {
      log.info("Trying to find element with xPath - " + xPath);
      if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), 5).size() >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  public boolean ios_isElementPresentUsingPredicateString(String predicateString) {

    try {
      log.info("Trying to find element with predicateString - " + predicateString);
      if (wait_until_MobileElementsAre_Visible(
                  driver, (MobileBy.iOSNsPredicateString(predicateString)), 5)
              .size()
          >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  public boolean ios_isElementPresentUsingClassChain(String classChain) {

    try {
      log.info("Trying to find element with classChain - " + classChain);
      if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.iOSClassChain(classChain)), 5)
              .size()
          >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  // ***** PRESENCE OF MOBILE ELEMENT - WAIT UNTIL TIME SPECIFIED ****** //

  public boolean ios_isElementPresentUsingAccessibilityId(
      String acessibilityId, int timeinSeconds) {

    try {
      log.info("Trying to find element with AcessibilityId - " + acessibilityId);
      if (wait_until_MobileElementsAre_Visible(
                  driver, (MobileBy.AccessibilityId(acessibilityId)), timeinSeconds)
              .size()
          >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  public boolean ios_isElementPresentUsingName(String name, int timeinSeconds) {

    try {
      log.info("Trying to find element with name - " + name);
      if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.name(name)), timeinSeconds).size()
          >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  public boolean ios_isElementPresentUsingXpath(String xPath, int timeinSeconds) {

    try {
      log.info("Trying to find element with xPath - " + xPath);
      if (wait_until_MobileElementsAre_Visible(driver, (MobileBy.xpath(xPath)), timeinSeconds)
              .size()
          >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  public boolean ios_isElementPresentUsingPredicateString(
      String predicateString, int timeinSeconds) {

    try {
      log.info("Trying to find element with predicateString - " + predicateString);
      if (wait_until_MobileElementsAre_Visible(
                  driver, (MobileBy.iOSNsPredicateString(predicateString)), timeinSeconds)
              .size()
          >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  public boolean ios_isElementPresentUsingClassChain(String classChain, int timeinSeconds) {

    try {
      log.info("Trying to find element with classChain - " + classChain);
      if (wait_until_MobileElementsAre_Visible(
                  driver, (MobileBy.iOSClassChain(classChain)), timeinSeconds)
              .size()
          >= 1) {
        log.info("element found");
        return true;
      }
    } catch (Exception e) {
      log.info("element not found");
      return false;
    }
    return false;
  }

  // ***** IOS SCROLL FUNCTIONS ****** //

  public void ios_scroll(int starty, int endy) {
    log.info("Trying to scroll down on screen from " + starty + " to " + endy);
    Dimension size = driver.manage().window().getSize();
    int x = size.getWidth() / 2;
    int start_y = (int) (size.getHeight() * starty / 100);
    int end_y = (int) (size.getHeight() * endy / 100);
    new TouchAction(driver)
        .longPress(PointOption.point(x, start_y))
        .moveTo(PointOption.point(x, end_y))
        .release()
        .perform();
    log.info("scroll done");
  }

  public void ios_scrollUntilLabelIsFound(String label, int starty, int endy, int maxScrollTimes) {
    int retry = 0;
    while (!ios_isElementPresentUsingPredicateString("label =='" + label + "'", 1)) {
      if (retry > maxScrollTimes) {
        break;
      }
      ios_scroll(starty, endy);
      retry++;
    }
  }

  public void ios_scrollDown_UsingParentElementToLabel(RemoteWebElement parent, String label) {

    log.info("Trying to scroll down in a container to element with label -" + label);
    String parentID = parent.getId();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Map<String, String> params = new HashMap<>();
    params.put("direction", "down");
    params.put("element", parentID);
    params.put("predicateString", "label == " + label + "");
    js.executeScript("mobile: scroll", params);
    log.info("scrolled to element with label -" + label);
  }

  public void ios_scrollUp_UsingParentElementToLabel(RemoteWebElement parent, String label) {
    log.info("Trying to scroll down to element with label -" + label);
    String parentID = parent.getId();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    HashMap params = new HashMap();
    params.put("direction", "up");
    params.put("element", parentID);
    params.put("predicateString", "label == '" + label + "'");
    js.executeScript("mobile: scroll", params);
    log.info("scrolled to element with label -" + label);
  }

  public boolean ios_swipeToDirection(MobileElement el, String direction) {
    try {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      HashMap<String, String> swipeObject = new HashMap<String, String>();
      if (direction.equals("down")) {
        swipeObject.put("direction", "down");
      } else if (direction.equals("up")) {
        swipeObject.put("direction", "up");
      } else if (direction.equals("left")) {
        swipeObject.put("direction", "left");
      } else if (direction.equals("right")) {
        swipeObject.put("direction", "right");
      }
      swipeObject.put("element", el.getId());
      js.executeScript("mobile:swipe", swipeObject);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // Swiping element

  public void ios_swipeElementToTheLeft(WebElement el) {

    log.info("Trying to swipe element to the left");
    int x = el.getLocation().getX();
    int y = el.getLocation().getY();
    // elements x location is the beginning point of the element , multiplying with 4 to get to the
    // middle part of the element
    new TouchAction(driver)
        .press(PointOption.point(x * 4, y))
        .waitAction(waitOptions(Duration.ofSeconds(2)))
        .moveTo(PointOption.point(x, y))
        .release()
        .perform();
    log.info("Element swipe to the left performed");
  }

  public void ios_swipeElementToTheRight(WebElement el) {

    log.info("Trying to swipe element to the left");
    int x = el.getLocation().getX();
    int y = el.getLocation().getY();
    // elements x location is the beginning point of the element , multiplying with 4 to get to the
    // middle part of the element
    new TouchAction(driver)
        .press(PointOption.point(x, y))
        .waitAction(waitOptions(Duration.ofSeconds(2)))
        .moveTo(PointOption.point(x * 4, y))
        .release()
        .perform();
    log.info("Element flicked to the left");
  }

  // ********* MULTI TOUCH ACTIONS ***************************//

  public void zoom_Using_MultiTouchActions() {

    int width = driver.manage().window().getSize().getWidth();
    int height = driver.manage().window().getSize().getHeight();

    int halfWidth = width / 2;
    int halfHeight = height / 2;

    MultiTouchAction multiTouch = new MultiTouchAction(driver);
    TouchAction touch1 = new TouchAction(driver);
    TouchAction touch2 = new TouchAction(driver);

    touch1
        .press(PointOption.point(halfHeight, halfHeight))
        .waitAction(waitOptions(Duration.ofSeconds(1)))
        .moveTo(PointOption.point(0, 60))
        .release();
    touch2
        .press(PointOption.point(halfHeight, halfHeight + 40))
        .waitAction(waitOptions(Duration.ofSeconds(1)))
        .moveTo(PointOption.point(0, 80))
        .release();

    multiTouch.add(touch1).add(touch2);
    multiTouch.perform();
  }

  // ********* EXTRAS ***************************//

  // Sleep
  public void sleep(int s) {
    try {
      Thread.sleep(s * 1000);
    } catch (InterruptedException ex) {
    } catch (IllegalArgumentException ex) {
    }
  }


}
