package com.swtestacademy.springbootselenium.cucumber;

import io.cucumber.junit.platform.engine.Constants;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.platform.suite.api.*;
import org.testng.annotations.DataProvider;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
//@SelectDirectories("src/test/java/com/swtestacademy/springbootselenium/cucumber/features")
@SelectClasspathResource("src/test/java/com/swtestacademy/springbootselenium/cucumber/features")
//@SelectPackages("com.swtestacademy.springbootselenium.cucumber")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.swtestacademy.springbootselenium.cucumber")
@ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@negative")
public class RunCucumberTest { /*extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }*/
}