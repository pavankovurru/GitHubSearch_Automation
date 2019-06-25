package com.company.project.page.ios;

import com.company.project.constants.ios.SearchConstants;
import com.company.project.utilities.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Rectangle;
import org.testng.Assert;

import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class SearchPage {

  private Logger log = LogManager.getLogger();
  AppiumDriver driver;
  AppiumUtil appium = null;

  public SearchPage(AppiumDriver driver) {
    this.driver = driver;
    appium = new AppiumUtil(driver);
  }

  // Landing Page elements availability

  public boolean isSearchTitleAvailable() {
    log.info("Trying to search for -" + SearchConstants.SEARCH_TITLE_XPATH);
    return appium.ios_isElementPresentUsingXpath(SearchConstants.SEARCH_TITLE_XPATH);
  }

  public boolean isSearchTextBoxAvailable() {
    log.info("Trying to search for -" + SearchConstants.SEARCH_TEXT_BOX_XPATH);
    return appium.ios_isElementPresentUsingXpath(SearchConstants.SEARCH_TEXT_BOX_XPATH);
  }

  public boolean isSearchButtonAvailable() {
    log.info("Trying to search for -" + SearchConstants.SEARCH_BUTTON_XPATH);
    return appium.ios_isElementPresentUsingXpath(SearchConstants.SEARCH_BUTTON_XPATH);
  }

  public boolean isFavouritesButtonsAvailable() {
    log.info("Trying to search for -" + SearchConstants.FAVOURITES_BUTTON_XPATH);
    return appium.ios_isElementPresentUsingXpath(SearchConstants.FAVOURITES_BUTTON_XPATH);
  }

  public void clickSearchButton() {
    appium
        .ios_returnMobileElementUsingXpath(String.format(SearchConstants.SEARCH_BUTTON_XPATH))
        .click();
  }

  // Send Data

  public void sendDataToSearch(String data) {

    appium
        .ios_returnMobileElementUsingXpath(SearchConstants.SEARCH_TEXT_BOX_XPATH)
        .sendKeys(data + Keys.ENTER);
    log.info("Sent data to search box - " + data);
  }

  public void clickClearSearchText() {
    appium
        .ios_returnMobileElementUsingAccessibilityId(
            String.format(SearchConstants.CANCEL_SEARCH_BUTTON_ID))
        .click();
  }

  // Search results methods

  public int getNumberOfSearchResults() {
    try {
      return appium
          .wait_until_MobileElementsAre_Present(
              driver, MobileBy.xpath(SearchConstants.SEARCH_RESULTS_COUNT_XPATH))
          .size();
    } catch (Exception e) {
      return 0;
    }
  }

  public void clickSearchResult(int number) {
    appium
        .ios_returnMobileElementUsingXpath(
            String.format(SearchConstants.SEARCH_RESULTS_TITLE_XPATH, String.valueOf(number)))
        .click();
  }

  public void clickSearchResultMoreInfo(int number) {
    appium
        .ios_returnMobileElementUsingXpath(
            String.format(SearchConstants.SEARCH_RESULTS_MORE_INFO_XPATH, String.valueOf(number)))
        .click();
  }

  public String getSearchResultTitle(int number) {
    log.info("Trying to get search result title");
    return appium
        .ios_returnMobileElementUsingXpath(
            String.format(SearchConstants.SEARCH_RESULTS_TITLE_XPATH, String.valueOf(number)))
        .getText();
  }

  public String getSearchResultDescription(int number) {
    log.info("Trying to get search result description");
    return appium
        .ios_returnMobileElementUsingXpath(
            String.format(SearchConstants.SEARCH_RESULTS_DESCRIPTION_XPATH, String.valueOf(number)))
        .getText();
  }

  // More Info Screen

  public boolean isFavouriteButtonAvailable() {
    log.info("Trying to search for -" + SearchConstants.FAVOURITE_BUTTON_XPATH);
    return appium.ios_isElementPresentUsingXpath(SearchConstants.FAVOURITE_BUTTON_XPATH);
  }

  public boolean isUnFavouriteButtonAvailable() {
    log.info("Trying to search for -" + SearchConstants.UNFAVOURITE_BUTTON_XPATH);
    return appium.ios_isElementPresentUsingXpath(SearchConstants.UNFAVOURITE_BUTTON_XPATH);
  }

  public void verifyTitleAndDescriptionInMoreInfoScreen(String title, String description) {
    log.info("Trying to verify title " + title);
    Assert.assertEquals(
        appium
            .android_returnMobileElementPresentUsingXPath(SearchConstants.MORE_INFO_TITLE_XPATH)
            .getText(),
        title);
    log.info("Trying to verify title description - " + description);
    Assert.assertEquals(
        appium
            .android_returnMobileElementPresentUsingXPath(
                SearchConstants.MORE_INFO_DESCRIPTION_XPATH)
            .getText(),
        description);
  }

  public boolean verifyPresenceOfOwnersInformationInMoreInfoScreen() {
    log.info("Trying to validate Owners info");
    if (appium.ios_isElementPresentUsingXpath(SearchConstants.MORE_INFO_OWNERSINFO_XPATH)) {
      log.info("Found Owners info");
      if (appium.ios_isElementPresentUsingXpath(SearchConstants.MORE_INFO_OWNERS_USERNAME_XPATH)) {
        log.info("Found Owners Username");
        if (appium.ios_isElementPresentUsingXpath(SearchConstants.MORE_INFO_OWNERS_EMAIL_XPATH)) {
          log.info("Found Owners Email");
          return true;
        }
      }
    }
    return false;
  }

  public void clickFavouriteButton() {
    appium
        .ios_returnMobileElementUsingXpath(String.format(SearchConstants.FAVOURITE_BUTTON_XPATH))
        .click();
  }

  public void clickUnFavouriteButton() {
    appium
        .ios_returnMobileElementUsingXpath(String.format(SearchConstants.UNFAVOURITE_BUTTON_XPATH))
        .click();
  }

  public void navigateToSearchScreenFromMoreInfoScreen() {
    appium
        .ios_returnMobileElementUsingXpath(String.format(SearchConstants.SEARCH_BACK_BUTTON_XPATH))
        .click();
  }
}
