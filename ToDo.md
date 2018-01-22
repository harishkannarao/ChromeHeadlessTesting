#### Key Highlights

* Headless testing in cloud ci servers
* Browser Mob Proxy: Manipulating request headers with Chrome Webdriver
* Automatic capturing of screenshot on test completion
* Import self-signed certificate to fix SSL errors

#### Xvfb problems

* Not reliable
* Occasional crashing when running large number of tests

#### Documentation

* Run full build
* Run javascript unit tests as standalone
* Run application as jar
* Run the build in headless CI server

* Running non localhost ssl tests in headless mode

Testing the application with non-localhost domains (e.g local.nature.com) with self-signed certificate or invalid certificate is tricky with Chrome / Chromium browser in headless mode (i.e using '--headless' flag during browser startup).  The reason is, while running in headless mode, chrome will not ignore SSL errors and hence the certificate should be imported and trusted in the Operating System.

Commands to import a self signed certificate for Ubuntu OS is captured in the '.travis.yml' file. This file is located in the top directory of this repository and it is used to configure and run CI build in a headless server on the cloud.

#### Import the self-signed certificate

Import the following self-signed certificate 'SampleApplication/src/main/resources/keystore.p12' in your Operating System

* For Linux: Follow steps in .travis.yml file
* For Mac: Use 'Keychain Access' application to import
* For Windows: Need to find out the tool / command to import a .p12 file
    


