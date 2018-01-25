# Chrome Headless Testing

This repository is to demonstrate headless testing with Chrome in headless cloud CI servers. This source code demonstrates the following key features with Chrome / Chromium:

* Ability to toggle between headless mode (useful for CI) and non-headless mode (useful for development machine) using system property
* Ability to automatically capture screenshot on test completion (on success or failure), which helps to debug test failures on remote CI servers
* Ability to send additional http request headers (e.g X-Forwarded-For) to server from browser using Browser Mob Proxy
* Ability to test application on non-localhost domains (e.g local.example.com) on SSL with Self Signed certificate using headless mode

## Classification of headless testing
Headless testing with chrome can be classified into following categories, based on the category, test setup needs additional web driver configurations and tools (like proxies). This demo repository contains code that covers all these categories.

#### Simple
Testing the application on localhost on http or https (using self signed certificate)

#### Medium
Testing the application on localhost which needs additional http request headers (like X-Forwared-For) to extract additional information from headers injected by load balancer. These headers can setup using Browser Mob Proxy (open source library) which can act as an proxy for Chrome browser.

#### Complex
Testing the application on non-localhost domains like local.example.com with self-signed certificate and need to send additional http request headers. These are extreme type of tests for an application which:
* contains domain aware functionalities
* deals with secure cookies
* extracts information from request headers injected by load balancers



## Headless Travis CI Build status
[![Build Status](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting.svg?branch=master)](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting)

[Headless Travis CI configuration file](https://github.com/harishkannarao/ChromeHeadlessTesting/blob/master/.travis.yml)



## Required Software and Tools
* Java Version: Oracle Java 1.8.0_144
* Apache Maven Version: 3.3.9
* Gradle Version: 4.4.1
* Chrome (Windows & Mac OS) Browser / Chromium (Linux OS) Browser: 62
* chromeDriver: 2.32 (Execute: chromedriver --version)
* Git Client: Any latest version
* Integrated Development Environment: Any version IntelliJ Idea or Eclipse



## Running the build

#### Full Build (non headless mode)

The following command generates artifacts, executes java unit tests, javascript unit tests and integration tests.

chromedriver should be available in PATH of your Operation System.

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


    
## Run Java Script unit tests in standalone

To run the java script unit tests in standalone mode, start the JasmineSpec runner application using the following command

###### For Maven users

    mvn spring-boot:run -pl JavaScriptJasmineUnitTests 
    
###### For Gradle users
     
    ./gradlew :JavaScriptJasmineUnitTests:bootRun
    
And open the JasmineSpec runner url in a browser

**http://localhost:9090/JasmineSpecRunner.html** 


    
## Override location of browser and chromedriver location

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
    
#### Command to run non-localhost tests on Ssl

###### For Maven users

    mvn clean install -DrunNonLocalHostTests=true
    
###### For Gradle users

    ./gradlew clean build -DrunNonLocalHostTests=true