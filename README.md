# selenium-springboot
Selenium and Spring Boot test automation example.

https://www.swtestacademy.com/selenium-spring-boot-cucumber-junit5-project/



## RemoteWebDriverConfig Class
@Profile(“grid”) annotation is for Selenium Grid and remotewebdriver. 
When we run the tests with “spring.profiles.active=grid” environment variable, 
the tests will use application-grid.properties file under the resources folder as the main configuration file.

## BrowserOps Class
This class is for preparing and getting the browser-specific options.


## ElementContainsText Class
This custom-expected condition class waits until the element contains a specific text.

## WindowSwitchUtil Class
This class is for switching windows.

## CUCUMBER 
After implementing this class, we can run the cucumber test with the command below.

```
mvn -Dsurefire.failIfNoSpecifiedTests=false -Dtest="com.swtestacademy.springbootselenium.cucumber.RunCucumberTest" test

or we can use the command below as well.

mvn clean install -Dcucumber.glue="com.swtestacademy.springbootselenium.cucumber.steps" -Dcucumber.plugin="com/swtestacademy/springbootselenium/cucumber/features"
```

## How to Run Tests
We can run the test in the command line with the maven command below. 


```
The below command is for the zhs terminal.
mvn -Dtest="com.swtestacademy.springbootselenium.tests.**" test

The command below is for the bash terminal.
mvn -Dtest=com.swtestacademy.springbootselenium.tests.** test

If we want to select a specific profile, we have to specify this as shown below.
mvn -Dtest="com.swtestacademy.springbootselenium.tests.**" -Dspring.profiles.active=grid test  
```

> For selenium grid execution, we should activate the selenium grid by running the Selenium Docker compose file.

```
docker-compose -f docker-compose-v3.yml up

# To execute this docker-compose yml file use `docker-compose -f docker-compose-v3.yml up`
# Add the `-d` flag at the end for detached execution
# To stop the execution, hit Ctrl+C, and then `docker-compose -f docker-compose-v3.yml down`
version: "3"
services:
  chrome:
    image: selenium/node-chrome:4.1.2-20220131
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
      - SE_NODE_MAX_SESSIONS=5
  edge:
    image: selenium/node-edge:4.1.2-20220131
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
      - SE_NODE_MAX_SESSIONS=5
  firefox:
    image: selenium/node-firefox:4.1.2-20220131
    shm_size: 2gb
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
      - SE_NODE_MAX_SESSIONS=5
  selenium-hub:
    image: selenium/hub:4.1.2-20220131
    container_name: selenium-hub
    ports:
      - "4442:4442"
      - "4443:4443"
      - "4444:4444"

```
## Via maven you should use the command below:

```
mvn clean package -DskipTests

mvn -Dtest="com.swtestacademy.springbootselenium.tests.**" test

mvn -Dtest="com.swtestacademy.springbootselenium.tests.**" -Dspring.profiles.active=grid test

If you want to run the cucumber tests, you can use the command below:
mvn -Dtest="com.swtestacademy.springbootselenium.cucumber.RunCucumberTest" test

or all
mvn test -Dsurefire.includeJUnit5Engines=cucumber -Dcucumber.plugin="pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json, junit:target/cucumber-reports/cucumber.xml" -Dcucumber.features="com/swtestacademy/springbootselenium/cucumber/features"
mvn test -Dcucumber.plugin="pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json, junit:target/cucumber-reports/cucumber.xml" -Dcucumber.glue="com.swtestacademy.springbootselenium.cucumber.steps" -Dcucumber.features="com/swtestacademy/springbootselenium/cucumber/features"

mvn test -Dsurefire.includeJUnit5Engines=cucumber -Dcucumber.plugin="pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json, junit:target/cucumber-reports/cucumber.xml" -Dcucumber.features="com/swtestacademy/springbootselenium/cucumber/features"
mvn test -Dcucumber.plugin="pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json, junit:target/cucumber-reports/cucumber.xml" -Dcucumber.features="com/swtestacademy/springbootselenium/cucumber/features"
```