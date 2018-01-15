#### To do list for this repository

* Upgrade Angular
* Upgrade Spring Boot
* Xvfb configurations
* UnitTests
* Gradle configurations

#### Key Highlights

* Browser Mob Proxy: Manipulating request headers with Chrome Webdriver
* Automatic capturing of full page screenshot on test failures
* Ignore SSL errors and test with self signed certificates
* Xvfb setup and configuration for headless CI servers

#### Xvfb vs Chrome headless

* Doesn't allow to ignore ssl errors for non localhost urls (e.g local.example.com)
* Verify difficult to test legacy internal websites using invalid certificates

#### Documentation

* Required Tools
    * git client any version
    * java 8 or higher
    * mvn 3 or higher
    * gradle 4 or higher
    * Google Chrome / Chromium Browser 61 or higher
    * chromedriver 2.32 or higher
    * IDE
        * Intellij Community Edition
        * Any Eclipse Edition
* Optional Tools
    * docker Community Edition 17 or higher
    * docker-compose 1.17 or higher
* Run full build
* Run java unit tests
* Run javascript unit tests
* Run integration tests
* Run application as jar
* Run the build in headless CI server
* Run the build in headless mode in local using Docker