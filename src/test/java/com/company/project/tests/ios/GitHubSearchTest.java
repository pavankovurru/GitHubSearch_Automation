package com.company.project.tests.ios;

import com.company.project.constants.ios.SearchConstants;
import com.company.project.page.ios.FavoritePage;
import com.company.project.page.ios.SearchPage;
import com.company.project.utilities.AppiumUtil;
import com.company.project.utilities.RunOn;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.regex.Pattern;

public class GitHubSearchTest {

  private Logger log = LogManager.getLogger();
  AppiumDriver driver = null;
  AppiumUtil appium = null;
  RunOn run_on = new RunOn();
  SearchPage searchPage = null;
  FavoritePage favouritePage = null;

  @BeforeMethod(alwaysRun = true)
  @Parameters({"runOn", "appName", "deviceName", "udid", "bundleId"})
  public void invokeApp(
      String runOn, String appName, String deviceName, String udid, String bundleId) {
    driver = run_on.run(runOn, appName, deviceName, udid, bundleId);
    log.info("--------------------------------------------------------------------------");
    log.info("Appium driver created for - " + runOn);
    log.info("Targeting app - " + appName);
    log.info("--------------------------------------------------------------------------");
    appium = new AppiumUtil(driver);
    searchPage = new SearchPage(driver);
    favouritePage = new FavoritePage(driver);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test(priority = 1)
  public void validateSearchAndFavouritePage() {

    log.info("Testing a - " + driver.getContext());

    Assert.assertTrue(
        searchPage.isSearchTitleAvailable(), "Search button is not available in search page.");
    Assert.assertTrue(
        searchPage.isSearchTextBoxAvailable(), "Search text box is not available in search page.");
    Assert.assertTrue(
        searchPage.isSearchButtonAvailable(), "Search button is not available in search page.");
    Assert.assertTrue(
        searchPage.isFavouritesButtonsAvailable(),
        "favourite button is not available in search page.");

    favouritePage.clickFavoritesButton();

    Assert.assertTrue(
        favouritePage.isfavoriteTitleAvailable(),
        "Favorite button is not available in Favorite page.");
    Assert.assertFalse(
        searchPage.isSearchTitleAvailable(), "Search button is available in favourite page.");
    Assert.assertFalse(
        searchPage.isSearchTextBoxAvailable(), "Search text box is available in Favorite page.");
    Assert.assertTrue(
        searchPage.isSearchButtonAvailable(), "Search button is not available in search page.");
    Assert.assertTrue(
        searchPage.isFavouritesButtonsAvailable(),
        "favourite button is not available in search page.");

    searchPage.clickSearchButton();

    Assert.assertTrue(
        searchPage.isSearchTitleAvailable(), "Search button is not available in search page.");
    Assert.assertTrue(
        searchPage.isSearchTextBoxAvailable(), "Search text box is not available in search page.");
    Assert.assertTrue(
        searchPage.isSearchButtonAvailable(), "Search button is not available in search page.");
    Assert.assertTrue(
        searchPage.isFavouritesButtonsAvailable(),
        "favourite button is not available in search page.");
  }

  @Test(priority = 2)
  public void validateSearchFunctionality() {

    // Send search data
    searchPage.sendDataToSearch(SearchConstants.SEARCH_DATA);
    log.info("Searching for -" + SearchConstants.SEARCH_DATA);

    int totalResults = searchPage.getNumberOfSearchResults();

    log.info(
        "Number Of Search results found for - '"
            + SearchConstants.SEARCH_DATA
            + "' = "
            + totalResults);

    // Asserting that there is atleast one search result.
    Assert.assertNotEquals(searchPage.getNumberOfSearchResults(), 0);

    // Validating that search result contains the text that was searched for
    for (int i = 1; i <= totalResults / 10; i++) { // intentionally checking first 10% of results
      String currentTitle = searchPage.getSearchResultTitle(i);
      log.info("Current Title -" + currentTitle);
      log.info("Current Position -" + i);
      Assert.assertTrue(
          Pattern.compile(".*?" + SearchConstants.SEARCH_DATA + ".*?", Pattern.CASE_INSENSITIVE)
              .matcher(currentTitle)
              .find(),
          "Search result - "
              + i
              + " does not contain the search word -"
              + SearchConstants.SEARCH_DATA);
    }

    // Clear search Results
    searchPage.clickClearSearchText();
    Assert.assertEquals(searchPage.getNumberOfSearchResults(), 0);
  }

  @Test(priority = 3)
  public void validateSearchResults() {

    String resultTitle;
    String resultDescription;

    // Send search data
    searchPage.sendDataToSearch(SearchConstants.SEARCH_DATA);

    int totalResults = searchPage.getNumberOfSearchResults();

    // Search Result Validations
    for (int i = 1; i <= totalResults / 10; i++) { // intentionally checking first 10% of results

      resultTitle = searchPage.getSearchResultTitle(i);
      resultDescription = searchPage.getSearchResultDescription(i);

      log.info("Title -" + resultTitle + " & Description -" + resultDescription);

      // Assert on result Title
      Assert.assertTrue(
          Pattern.compile(".*?" + SearchConstants.SEARCH_DATA + ".*?", Pattern.CASE_INSENSITIVE)
              .matcher(resultTitle)
              .find(),
          "Search result - "
              + i
              + " does not contain the search word -"
              + SearchConstants.SEARCH_DATA);

      // click on more info arrow
      searchPage.clickSearchResultMoreInfo(i);

      // More info Page assertions
      searchPage.verifyTitleAndDescriptionInMoreInfoScreen(resultTitle, resultDescription);
      searchPage.verifyPresenceOfOwnersInformationInMoreInfoScreen();

      // navigate back and verify screen name
      searchPage.navigateToSearchScreenFromMoreInfoScreen();
      Assert.assertTrue(
          searchPage.isSearchTitleAvailable(), "Search button is not available in search page.");

      // click title and validate more info screen
      searchPage.clickSearchResult(i);
      searchPage.verifyTitleAndDescriptionInMoreInfoScreen(resultTitle, resultDescription);

      // Navigate back to search screen
      searchPage.clickSearchButton();
    }
  }

  @Test(priority = 4)
  public void validateFavouriteFunctionality() {

    String favoriteTitle;
    String favoriteDescription;
    int numberOfExistingFavorites;

    favouritePage.clickFavoritesButton();
    numberOfExistingFavorites = favouritePage.getNumberOfFavorites();

    // Send search data
    searchPage.clickSearchButton();
    searchPage.sendDataToSearch(SearchConstants.SEARCH_DATA);

    int totalResults = searchPage.getNumberOfSearchResults();

    for (int i = 1; i <= totalResults; i++) {

      favoriteTitle = searchPage.getSearchResultTitle(i);
      favoriteDescription = searchPage.getSearchResultDescription(i);

      searchPage.clickSearchResult(i);

      // Add and verify favorite
      searchPage.clickFavouriteButton();
      favouritePage.clickFavoritesButton(); // navigating to favourites screen
      Assert.assertEquals(favouritePage.getNumberOfFavorites(), numberOfExistingFavorites + 1);
      favouritePage.verifyTitleAndDescriptionInfavoritesScreen(favoriteTitle, favoriteDescription);

      // unFavorite and verify
      searchPage.clickSearchButton();
      searchPage.clickUnFavouriteButton();
      favouritePage.clickFavoritesButton(); // navigating to favourites screen
      Assert.assertEquals(favouritePage.getNumberOfFavorites(), numberOfExistingFavorites);

      //Break loop after checking favorite/un-favorite options with first search result.
      //Remove break if favorite functionality has to be checked for all search results.
      break;
    }
  }
}
