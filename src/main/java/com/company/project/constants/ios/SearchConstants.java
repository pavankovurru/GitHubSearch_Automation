package com.company.project.constants.ios;

public class SearchConstants {

  // Test Data
  public static final String SEARCH_DATA = "Appium";

  // Element locators
  public static final String SEARCH_TITLE_XPATH = "//XCUIElementTypeNavigationBar[@name='Search']";
  public static final String SEARCH_TEXT_BOX_XPATH = "//XCUIElementTypeSearchField[@name='Search']";
  public static final String CANCEL_SEARCH_BUTTON_ID = "Cancel";

  public static final String SEARCH_BUTTON_XPATH = "//XCUIElementTypeButton[@name='Search']";
  public static final String FAVOURITES_BUTTON_XPATH = "//XCUIElementTypeButton[@name='Favorites']";

  public static final String SEARCH_RESULTS_TABLE_EMPTYLIST_XPATH = "//XCUIElementTypeTable[@name='Empty list']";
  public static final String SEARCH_RESULTS_COUNT_XPATH = "//XCUIElementTypeTable/XCUIElementTypeCell";

  public static final String SEARCH_RESULTS_TITLE_XPATH = "//XCUIElementTypeTable/XCUIElementTypeCell[%s]/XCUIElementTypeStaticText[2]";
  public static final String SEARCH_RESULTS_DESCRIPTION_XPATH = "//XCUIElementTypeTable/XCUIElementTypeCell[%s]/XCUIElementTypeStaticText[1]";
  public static final String SEARCH_RESULTS_MORE_INFO_XPATH = "//XCUIElementTypeTable/XCUIElementTypeCell[%s]/XCUIElementTypeButton[@name='More Info']";

  // MORE INFO SCREEN
  public static final String FAVOURITE_BUTTON_XPATH = "//XCUIElementTypeButton[@name='Favorite']";
  public static final String UNFAVOURITE_BUTTON_XPATH = "//XCUIElementTypeButton[@name='Unfavorite']";

  public static final String SEARCH_BACK_BUTTON_XPATH = "//XCUIElementTypeButton[@name='Search']";

  public static final String MORE_INFO_TITLE_XPATH = "//XCUIElementTypeOther/XCUIElementTypeStaticText[1]";
  public static final String MORE_INFO_DESCRIPTION_XPATH = "//XCUIElementTypeOther/XCUIElementTypeStaticText[2]";

  public static final String MORE_INFO_OWNERSINFO_XPATH = "//XCUIElementTypeStaticText[contains(@name,'Owner')]";
  public static final String MORE_INFO_OWNERS_USERNAME_XPATH = "//XCUIElementTypeStaticText[contains(@name,'Username')]";
  public static final String MORE_INFO_OWNERS_EMAIL_XPATH = "//XCUIElementTypeStaticText[contains(@name,'Email')]";
}
