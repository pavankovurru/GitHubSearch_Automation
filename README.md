# GIT HUB SEARCH AUTOMATION

`https://github.com/Karambirov/GitHubSearch`

## TEST CASES 

```
1)Verify that upon opening the app user lands in Search page, verify elements in search and favourite pages.  
2)Verify Search functionality, make sure that every search result contains the search keyword (case - insensitive). Clear Search and make sure that user is shown no search results.  
3)Verify Search results(Title and description) in search and more info pages.  
4)Verify favourite/Unfavourite functionality.
```


## RUNNING PROJECT

### Pre-requisites

```
1)Install XCODE.  
2)Install appium desktop or APPIUM 1.6 or higher using npm install -g appium. make sure appium is running.  
3)Install [home brew] (https://brew.sh/)  
4)brew install libimobiledevice - open source package which is able to communicate with iOS devices.  
5)brew install ios-deploy - for transferring iOS apps onto your device.  
6)brew install carthage.  
```

`Run project by running src/test/resources/testNG_IOS.xml`  

Note : tests are configured to run on ios simulator , testNG_IOS.xml can be configured to run on real devices and emulators too.





```
Compatible With Android Oreo and IOS 12
Uses explicit waits internally in all of the `src/main/java/com/company/project/utilities/AppiumUtil.java` functions that return mobile elements.  
  
Supports Parallel runs via testNG xml configuration  
Note : an appium server supports onl one device, make sure multiple appium server instances are run to support parallel execution
`open -n /Applications/Appium.app` - should do the trick on a mac to initialize a new instance of appium server

Works With Native, Web & Hybrid Apps.
Works on Emulators, Simulators & Real Devices.
```

##  FRAME WORK STACK 
```
1. Appium Java Client 6.1.0
2. Appium Desktop 1.13.0
3. Selenium 3.6
4. LOG4J 2  
5. TestNG 6.11
6. Gradle
```


##  IMPORTANT FILES  

1. `src/main/resources` -- This folder contains Android,IOS apps that will be tested locally.   
 
2. `src/main/java/com/company/project/utilities/AppiumUtil.java` -- Has Utility functions that can be used to simulate mobile actions.  

3. `src/test/resources` --  This folder contains testNG xml's which are parameterized with `AppName` and `runOn` details so that these XML's.
can be modified to target different apps, environments and target devices instead of making changes to test files.

4. A screen shot gets saved in screenshots folder with test class & test case name when there is a failure. 


