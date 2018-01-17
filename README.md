# Chrome Headless Testing
This repository is to demonstrate headless testing with Chrome in headless cloud CI servers

### Headless Travis CI Build status
[![Build Status](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting.svg?branch=master)](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting)

### Required Software and Tools
* Java Version: Oracle Java 1.8.0_144
* Apache Maven Version: 3.3.9
* Chrome (Windows & Mac OS) Browser / Chromium (Linux OS) Browser: 62
* chromeDriver: 2.32 (Execute: chromedriver --version)
* Git Client: Any latest version
* Integrated Development Environment: Any version IntelliJ Idea or Eclipse

### Maven commands to run the tests

#### With chrome / chromium browser and chromedriver available as executables in the PATH of your operating system

    mvn clean install
    
#### Run tests in headless mode

    mvn clean install -DchromeHeadless=true
    
#### Pass chrome / chromium browser and chromedriver location to the build

    mvn clean install -DchromeDriverBinary=/usr/lib/chromium-browser/chromedriver -DchromeBinary=/usr/bin/chromium-browser
    
#### Run non-localhost tests

These are special type of tests which tests the application on https://local.example.com and https://local.example.org

These are special because we need extra dns mapping in the operating system and import the self signed certificate in the operating system in order to run these tests as headless mode in chrome
 
Add the following additional local dns mappings in the respective file
* For Linux: /etc/hosts
* For Mac: /private/etc/hosts
* For Windows: C:\Windows\System32\drivers\etc\hosts

Lines to be added

    127.0.0.1	local.example.com
    127.0.0.1	local.example.org
    
Command to run non-localhost tests on Ssl

    mvn clean install -DrunNonLocalHostTests=true