package com.company.project.page.ios;

import com.company.project.constants.ios.FavoriteConstants;
import com.company.project.constants.ios.SearchConstants;
import com.company.project.utilities.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class FavoritePage {

  private Logger log = LogManager.getLogger();
  AppiumDriver driver;
  AppiumUtil appium = null;

  public FavoritePage(AppiumDriver driver) {
    this.driver = driver;
    appium = new AppiumUtil(driver);
  }

  public boolean isfavoriteTitleAvailable() {
    log.info("Trying to search for -" + FavoriteConstants.FAVORITE_TITLE_XPATH);
    return appium.ios_isElementPresentUsingXpath(FavoriteConstants.FAVORITE_TITLE_XPATH);
  }

  public void clickFavoritesButton() {
    appium
        .ios_returnMobileElementUsingXpath(String.format(SearchConstants.FAVOURITES_BUTTON_XPATH))
        .click();
  }

  public String getFavoriteTitle(int number) {
    return appium
        .ios_returnMobileElementUsingXpath(
            String.format(SearchConstants.SEARCH_RESULTS_TITLE_XPATH, String.valueOf(number)))
        .getText();
  }

  public String getFavoriteDescription(int number) {
    return appium
        .ios_returnMobileElementUsingXpath(
            String.format(SearchConstants.SEARCH_RESULTS_DESCRIPTION_XPATH, String.valueOf(number)))
        .getText();
  }

  public int getNumberOfFavorites() {
    try {
      return appium
          .wait_until_MobileElementsAre_Present(
              driver, MobileBy.xpath(SearchConstants.SEARCH_RESULTS_COUNT_XPATH))
          .size();
    } catch (Exception e) {
      return 0;
    }
  }

  public void verifyTitleAndDescriptionInfavoritesScreen(String title, String description) {
    Assert.assertEquals(
        appium
            .android_returnMobileElementPresentUsingXPath(
                FavoriteConstants.FAVORITEPAGE_TITLE_XPATH)
            .getText(),
        title);
    Assert.assertEquals(
        appium
            .android_returnMobileElementPresentUsingXPath(
                FavoriteConstants.FAVORITEPAGE_DESCRIPTION_XPATH)
            .getText(),
        description);
  }
}
