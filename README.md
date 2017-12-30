# Chrome Headless Testing
This repository is to demonstrate headless testing with Chrome in headless cloud CI servers

### Headless Travis CI Build status
[![Build Status](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting.svg?branch=master)](https://travis-ci.org/harishkannarao/ChromeHeadlessTesting)

### Required Software and Tools
* Java Version: Oracle Java 1.8.0_144
* Apache Maven Version: 3.3.9
* Chrome (Windows & Mac OS) Browser / Chromium (Linux OS) Browser: 62
* ChromeDriver: 2.32
* Git Client: Any latest version
* Integrated Development Environment: Any version IntelliJ Idea or Eclipse

### Maven commands to run the tests

#### With chrome / chromium browser and chromedriver in the PATH

    mvn clean install
    
### Pass chrome / chromium browser and chromedriver location to the build

    mvn clean install -DchromeDriverBinary=/usr/lib/chromium-browser/chromedriver -DchromeBinary=/usr/bin/chromium-browser