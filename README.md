# Chrome Headless Testing

This repository is to demonstrate headless testing with Chrome in headless cloud CI servers. This source code demonstrates the following key features with Chrome / Chromium:

* Ability to toggle between headless mode (useful for CI) and non-headless mode (useful for development machine) using system property
* Ability to automatically capture screenshot on test completion (on success or failure), which helps to debug test failures on remote CI servers
* Ability to send additional http request headers (e.g X-Forwarded-For) to server from browser using Browser Mob Proxy
* Ability to test application on non-localhost domains (e.g local.example.com) on SSL with Self Signed certificate using headless mode

## Headless Travis CI Build status
[![Build Status](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting.svg?branch=master)](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting)

[Headless Travis CI configuration file](https://github.com/harishkannarao/ChromeHeadlessTesting/blob/master/.travis.yml)



## Avoiding Xvfb

In lot of examples, Xvfb (Virtual Frame Buffer) was used as a solution for web driver based tests running in headless CI server. However, when running large number of tests (typically 600+) for an enterprise application, Xvfb crashed during the test run. This happened frequently, even after setting DBUS_SESSION_BUS_ADDRESS=/dev/null. As a result the build was red due to single failure and the build become flaky. After using the Chrome in native headless mode (--headless), the build became reliable and never failed for wrong reasons.


## Classification of setup needed for headless testing
Headless testing with chrome can be classified into following categories as listed below. Based on the test category, test setup needs additional web driver configurations and tools (like proxies). This demo repository contains code that covers all these categories.

#### Simple setup
Testing the application on localhost on http or https (using self signed certificate). This just needs `--allow-insecure-localhost` command line argument in chrome driver configuration

#### Medium setup
Testing the application on localhost which needs additional http request headers (like X-Forwared-For) to extract additional information from headers injected by load balancer. These headers can setup using Browser Mob Proxy (open source library) which can act as an proxy for Chrome browser. This needs `--allow-insecure-localhost` command line argument and setting the proxy in chrome driver configuration

#### Complex setup
Testing the application on non-localhost domains like local.example.com with self-signed certificate and need to send additional http request headers. These are extreme type of tests for an application which:
* contains domain aware functionalities
* deals with secure cookies
* extracts information from request headers injected by load balancers

This needs all of the following:
* set up browser mob proxy with the same self-signed certificate as the application uses
* set up browser mob proxy as proxy in chrome driver configuration
* import the self signed certificate in the operating system certificate store, so that Chrome browser will trust the certificate


## Required Softwares, Tools and Version
* Java JDK Version: Oracle Java 1.8.0_144
* Apache Maven Version: 3.3.9
* Gradle Version: 4.4.1
* Chrome (Windows & Mac OS) Browser / Chromium (Linux OS) Browser: 62
* chromedriver: 2.32 [chromedriver installation steps](http://harish-kannarao.blogspot.co.uk/2018/01/installing-chromedriver-for-selenium.html)
* Git Client: Any latest version
* Integrated Development Environment: Any version of IntelliJ Idea or Eclipse



## Running the build

Note: For gradle users on Windows, please use **gradlew.bat** instead of **./gradlew** in the following commands

#### Full Build (non headless mode)

The following command generates artifacts, executes java unit tests, javascript unit tests and integration tests.

###### For Maven users

    mvn clean install
    
###### For Gradle users

    ./gradlew clean build
   
   
   
#### Full Build (headless mode)

The following command executes the same stuffs as above command with Chrome / Chromium browser in headless mode
    
###### For Maven users

    mvn clean install -DchromeHeadless=true
    
###### For Gradle users
     
    ./gradlew clean build -DchromeHeadless=true


    
## Run Java Script unit tests as standalone

To run the java script unit tests in standalone mode, start the JasmineSpec runner application using the following command

###### For Maven users

    mvn spring-boot:run -pl JavaScriptJasmineUnitTests 
    
###### For Gradle users
     
    ./gradlew :JavaScriptJasmineUnitTests:bootRun
    
And open the JasmineSpec runner url in a browser

**http://localhost:9090/JasmineSpecRunner.html** 


    
## Pass the path of browser and chromedriver

The following command allows you to specify the installation location if the browser and / or chromedriver is not availabe in PATH of your Operating System. This is particularly convenient in CI environments
    
###### For Maven users

    mvn clean install -DchromeDriverBinary=/usr/lib/chromium-browser/chromedriver -DchromeBinary=/usr/bin/chromium-browser 
       
###### For Gradle users

    ./gradlew clean build -DchromeDriverBinary=/usr/lib/chromium-browser/chromedriver -DchromeBinary=/usr/bin/chromium-browser


    
## Run the sample application

###### For Maven users

    mvn spring-boot:run -pl SampleApplication
    
###### For Gradle users
    
    ./gradlew :SampleApplication:bootRun
    
###### Urls

    https://localhost:8080/app/index.html
    https://localhost:8080/thymeleafAngularDemo
    https://localhost:8080/showCustomHeader
    https://localhost:8080/viewCookie


   
## Run the sample application as jar

Generate the jar file and start the application

###### For Maven users

    mvn clean install -DskipTests=true
    java -jar SampleApplication/target/sample-application-1.0-SNAPSHOT-exec.jar
    
###### For Gradle users
    
    ./gradlew clean assemble
    java -jar SampleApplication/build/libs/sample-application-1.0-SNAPSHOT-exec.jar 


 
## Run non-localhost tests

These are special type of tests which tests the application on https://local.example.com and https://local.example.org using additional dns entries in the operating system.

Configurations to add additional dns entries for Travis CI is captured in the '.travis.yml' file. This file is located in the top directory of this repository and it is used to configure and run CI build in a headless server on the cloud.
 
#### For development machines:

Add the following lines in the respective file
* For Linux: /etc/hosts (use sudo)
* For Mac: /private/etc/hosts (use sudo)
* For Windows: C:\Windows\System32\drivers\etc\hosts

###### Lines to be added:

    127.0.0.1   local.example.com
    127.0.0.1   local.example.org

Please refer to the following link for more information about additional dns entries in your host file

[Add additional dns host entry](https://harish-kannarao.blogspot.co.uk/2018/01/how-to-add-additional-routes-mapping-to.html)

    
#### Command to run non-localhost tests

###### For Maven users

    mvn clean install -DrunNonLocalHostTests=true
    
###### For Gradle users

    ./gradlew clean build -DrunNonLocalHostTests=true
    

#### Command to run non-localhost tests in headless mode

To run non-localhost tests in headless mode, we need to import the self signed certificate and mark it as trusted certificate in the Operating System's Trust Store.

Otherwise Chrome / Chromium will not load the pages under headless mode.

Commands to import the self signed certificate used by the SampleApplication has been captured in the travis-ci.yml file. Hence the build is green in Travis CI Build.

For development machine, please import **SampleApplication/src/main/resources/keystore.p12** with password **my_keystore_password**.

The following links explains how to import .p12 file in various operating systems:
* For Mac: [Import .p12 file in Mac](https://harish-kannarao.blogspot.co.uk/2018/01/import-personal-information-exchange.html)
* For Windows: [Import .p12 file in Windows](https://support.quovadisglobal.com/kb/a66/how-do-i-install-a-digital-certificate-onto-windows-7.aspx)
* For Linux: [Import .p12 file in Linux](https://harish-kannarao.blogspot.co.uk/2017/12/import-self-signed-in-linux-for-chrome.html) 

###### For Maven users

    mvn clean install -DrunNonLocalHostTests=true -DchromeHeadless=true
    
###### For Gradle users

    ./gradlew clean build -DrunNonLocalHostTests=true -DchromeHeadless=true