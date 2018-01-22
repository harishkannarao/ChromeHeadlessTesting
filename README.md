# Chrome Headless Testing
This repository is to demonstrate headless testing with Chrome in headless cloud CI servers

## Headless Travis CI Build status
[![Build Status](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting.svg?branch=master)](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting)

## Required Software and Tools
* Java Version: Oracle Java 1.8.0_144
* Apache Maven Version: 3.3.9
* Gradle Version: 4.4.1
* Chrome (Windows & Mac OS) Browser / Chromium (Linux OS) Browser: 62
* chromeDriver: 2.32 (Execute: chromedriver --version)
* Git Client: Any latest version
* Integrated Development Environment: Any version IntelliJ Idea or Eclipse

## Running the build

### Full Build (non headless mode)

The following command generates artifacts, executes java unit tests, javascript unit tests and integration tests.

chromedriver should be available in PATH of your Operation System.

#### For Maven users

    mvn clean install
    
#### For Gradle users

    ./gradlew clean build
    
### Full Build (headless mode)

The following command executes the same stuffs as above command with Chrome / Chromium browser in headless mode
    
#### For Maven users

    mvn clean install -DchromeHeadless=true
    
#### For Gradle users
     
    ./gradlew clean build -DchromeHeadless=true
    
### Override location of browser and chromedriver location

The following command allows you to specify the installation location if the browser and / or chromedriver is not availabe in PATH of your Operating System. This is particularly convenient in CI environments
    
#### For Maven users

    mvn clean install -DchromeDriverBinary=/usr/lib/chromium-browser/chromedriver -DchromeBinary=/usr/bin/chromium-browser 
       
#### For Gradle users

    ./gradlew clean build -DchromeDriverBinary=/usr/lib/chromium-browser/chromedriver -DchromeBinary=/usr/bin/chromium-browser
    
### Run non-localhost tests

These are special type of tests which tests the application on https://local.example.com and https://local.example.org using additional dns entries in the operating system.

Configurations to add additional dns entries for Travis CI is captured in the '.travis.yml' file. This file is located in the top directory of this repository and it is used to configure and run CI build in a headless server on the cloud.
 
#### For development machines:

Add the following lines in the respective file
* For Linux: /etc/hosts (use sudo)
* For Mac: /private/etc/hosts (use sudo)
* For Windows: C:\Windows\System32\drivers\etc\hosts

Lines to be added:

    127.0.0.1	local.example.com
    127.0.0.1	local.example.org
    
#### Command to run non-localhost tests on Ssl

For Maven users

    mvn clean install -DrunNonLocalHostTests=true
    
For Gradle users

    ./gradlew clean build -DrunNonLocalHostTests=true